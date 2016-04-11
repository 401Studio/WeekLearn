package rabbit_java.workqueue;

import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import utils.MQConfig;
import utils.RabbitMQConnectionFactory;

public class NewTask {

	//private static final String QUEUE_NAME = "workqueue";
	private final static String QUEUE_NAME =MQConfig.getQueueName(1);
	
	public static void main(String[] args) throws java.io.IOException, TimeoutException {
		// 创建连接和频道
		ConnectionFactory factory = RabbitMQConnectionFactory.getFactory();
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		// 声明队列
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		// 发送10条消息，依次在消息后面附加1-10个点
		for (int i = 0; i < 10; i++) {
			String dots = "";
			for (int j = 0; j <= i; j++) {
				dots += ".";
			}
			String message = "helloworld" + dots + dots.length();
			channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
			System.out.println(" [x] Sent '" + message + "'");
		}
		channel.close();
		connection.close();
	}

}
