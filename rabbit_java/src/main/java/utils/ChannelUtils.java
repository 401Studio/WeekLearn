package utils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ChannelUtils {

	private static ConnectionFactory factory = RabbitMQConnectionFactory.getFactory();

	private static Connection conn;

	static {
		try {
			conn = factory.newConnection();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
	}

	public static Channel getChannel() {

		try {
			return conn.createChannel();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
