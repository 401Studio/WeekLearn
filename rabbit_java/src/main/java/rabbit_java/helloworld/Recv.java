package rabbit_java.helloworld;

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

public class Recv {
	//private final static String QUEUE_NAME = "hello";
	
	private final static String QUEUE_NAME =MQConfig.getQueueName(0);
	
	
	public static void main(String[] argv)
			throws java.io.IOException, java.lang.InterruptedException, TimeoutException {

		ConnectionFactory factory =RabbitMQConnectionFactory.getFactory();
	
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		
		 //声明队列，主要为了防止消息接收者先运行此程序，队列还不存在时报错。  
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

		 Consumer consumer = new DefaultConsumer(channel) {
		      @Override
		      public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
		          throws IOException {
		        String message = new String(body, "UTF-8");
		        System.out.println(" [x] Received '" + message + "'");
		      }
		    };
		    channel.basicConsume(QUEUE_NAME, true, consumer);
		
	}
}
