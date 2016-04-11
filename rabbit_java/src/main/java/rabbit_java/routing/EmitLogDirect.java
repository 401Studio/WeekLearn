package rabbit_java.routing;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import utils.MQConfig;
import utils.RabbitMQConnectionFactory;

public class EmitLogDirect {
	
	//private static final String EXCHANGE_NAME = "ex_logs_direct";
	
	private static final String EXCHANGE_NAME = MQConfig.getDirectExchange(0);
	
	private static final String[] SEVERITIES = { "info", "warning", "error" };

	public static void main(String[] argv) throws java.io.IOException, TimeoutException {
		// 创建连接和频道
		ConnectionFactory factory = RabbitMQConnectionFactory.getFactory();
		
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		// 声明交换机的类型
		channel.exchangeDeclare(EXCHANGE_NAME, "direct");

		// 发送6条消息
		for (int i = 0; i < 6; i++) {
			String severity = getSeverity();
			String message = severity + "_log :" + UUID.randomUUID().toString();
			// 发布消息至转发器，指定routingkey
			channel.basicPublish(EXCHANGE_NAME, severity, null, message.getBytes());
			System.out.println(" [x] Sent '" + message + "'");
		}

		channel.close();
		connection.close();
	}

	/**
	 * 随机产生一种日志类型
	 * 
	 * @return
	 */
	private static String getSeverity() {
		Random random = new Random();
		int ranVal = random.nextInt(3);
		return SEVERITIES[ranVal];
	}
}