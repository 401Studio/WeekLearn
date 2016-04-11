package rabbit_java.helloworld;

import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import utils.MQConfig;
import utils.RabbitMQConnectionFactory;

public class Send {

	//private final static String QUEUE_NAME = "hello";

	private final static String QUEUE_NAME =MQConfig.getQueueName(0);
	
	public static void main(String[] args) throws java.io.IOException, TimeoutException {
		ConnectionFactory factory =RabbitMQConnectionFactory.getFactory();
		
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		String message = "Hello World!";
		channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
		System.out.println(" [x] Sent '" + message + "'");
		
		channel.close();
		connection.close();
	}

}
