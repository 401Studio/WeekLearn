package rabbit_java.publish_describe;

import com.rabbitmq.client.*;

import utils.MQConfig;
import utils.RabbitMQConnectionFactory;

import java.io.IOException;

public class ReceiveLogs {
	//private static final String EXCHANGE_NAME = "logs";
	private static final String EXCHANGE_NAME = MQConfig.getFanoutExchange(0);
	
	
	public static void main(String[] argv) throws Exception {
		ConnectionFactory factory = RabbitMQConnectionFactory.getFactory();
		
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		
		//声明交换机，防止消息接收者先运行此程序，交换机还不存在时报错。  
		channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
		
		// 创建一个唯一的、自动删除的、非持久化的的队列  
		String queueName = channel.queueDeclare().getQueue();
		
		channel.queueBind(queueName, EXCHANGE_NAME, "");

		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
					byte[] body) throws IOException {
				String message = new String(body, "UTF-8");
				System.out.println(" [x] Received '" + message + "'");
				System.out.println("exchange type==fanout");
			}
		};
		channel.basicConsume(queueName, true, consumer);
	}
}