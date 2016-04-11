package rabbit_java.workqueue;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import utils.MQConfig;
import utils.RabbitMQConnectionFactory;

public class Worker {

	//private static final String TASK_QUEUE_NAME = "workqueue";
	private final static String QUEUE_NAME =MQConfig.getQueueName(1);

	public static void main(String[] argv)
			throws java.io.IOException, java.lang.InterruptedException, TimeoutException {

		  // 创建连接和频道  
    	ConnectionFactory factory =RabbitMQConnectionFactory.getFactory();

		final Connection connection = factory.newConnection();
		final Channel channel = connection.createChannel();

		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

		//channel.basicQos(1);
		
		final Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
					byte[] body) throws IOException {
				String message = new String(body, "UTF-8");

				System.out.println(" [x] Received '" + message + "'");
				try {
					doWork(message);
				} finally {
					System.out.println(" [x] Done");
					//channel.basicAck(envelope.getDeliveryTag(), false);//channel.basicConsume指定autoAck=true时就不用在这里声明了，否则要声明
				}
			}
		};
		channel.basicConsume(QUEUE_NAME, true, consumer);
	}

	private static void doWork(String task) {
		for (char ch : task.toCharArray()) {
			if (ch == '.') {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException _ignored) {
					Thread.currentThread().interrupt();
				}
			}
		}
	}
}