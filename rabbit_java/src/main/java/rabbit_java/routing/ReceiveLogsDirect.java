package rabbit_java.routing;

import com.rabbitmq.client.*;

import utils.MQConfig;
import utils.RabbitMQConnectionFactory;

import java.io.IOException;
import java.util.Random;

public class ReceiveLogsDirect {

	//private static final String EXCHANGE_NAME = "ex_logs_direct";
	private static final String EXCHANGE_NAME = MQConfig.getDirectExchange(0);
	
	private static final String[] SEVERITIES = { "info", "warning", "error" };

	public static void main(String[] argv) throws Exception {

		// 创建连接和频道
		ConnectionFactory factory = RabbitMQConnectionFactory.getFactory();
		
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		// 声明direct类型交换机
		channel.exchangeDeclare(EXCHANGE_NAME, "direct");

		String queueName = channel.queueDeclare().getQueue();
		String severity = getSeverity();
		// 指定binding_key
		channel.queueBind(queueName, EXCHANGE_NAME, severity);
		System.out.println(" [*] Waiting for " + severity + " logs. To exit press CTRL+C");

		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
					byte[] body) throws IOException {
				String message = new String(body, "UTF-8");
				System.out.println(" [x] Received '" + envelope.getRoutingKey() + "':'" + message + "'");
			}
		};
		channel.basicConsume(queueName, true, consumer);
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