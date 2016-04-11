package rabbit_java.publish_describe;

import com.rabbitmq.client.ConnectionFactory;

import utils.MQConfig;
import utils.RabbitMQConnectionFactory;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

public class EmitLog {

   // private static final String EXCHANGE_NAME = "logs";
    private static final String EXCHANGE_NAME = MQConfig.getFanoutExchange(0);
    
	public static void main(String[] argv) throws Exception {
		ConnectionFactory factory =RabbitMQConnectionFactory.getFactory();
		
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //声明交换机和类型
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        String message = "fanout exchanges example";

        //往交换机上发消息
        channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");

        channel.close();
        connection.close();
    }
    

    
}