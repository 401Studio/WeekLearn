package utils;

import com.rabbitmq.client.ConnectionFactory;
/**
 * 公共 ConnectionFactory 方法，用于连接rabbitmq
 * 
 * 创建rabbitmq 用户： rabbitmqctl add_user username  password
 * 将用户设置为管理员：rabbitmqctl set_user_tags username administrator
 * 设置用户权限：rabbitmqctl set_permissions -p / username '.*' '.*' '.*'   
 *     
 * @author hexuan
 *
 */
public class RabbitMQConnectionFactory {

	private final static String host = "192.168.170.135";
	private final static int port = 5672;
	private final static String username = "shine";
	private final static String password = "123456";

	private static ConnectionFactory factory;

	public static ConnectionFactory getFactory() {

		if (factory == null) {
			synchronized (RabbitMQConnectionFactory.class) {
				if (factory == null) {
					factory = new ConnectionFactory();
					initFactory(factory);
				}
			}
		}

		return factory;
	}

	private static void initFactory(ConnectionFactory factory) {
		factory.setHost(host);
		factory.setPort(port);
		factory.setUsername(username);
		factory.setPassword(password);
	}

}
