package rabbit_java.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import utils.MQConfig;
import utils.RabbitMQConnectionFactory;

public class ReceiveLogsTopicForCritical  
{  
  
   // private static final String EXCHANGE_NAME = "topic_logs";  
	 private static final String EXCHANGE_NAME = MQConfig.getTopicExchange(0);
	 
    public static void main(String[] argv) throws Exception  
    {  
        // 创建连接和频道  
    	ConnectionFactory factory =RabbitMQConnectionFactory.getFactory();
       
        Connection connection = factory.newConnection();  
        Channel channel = connection.createChannel();  
        // 声明转发器  
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");  
        // 随机生成一个队列  
        String queueName = channel.queueDeclare().getQueue();  
  
        // 接收所有与kernel相关的消息  
        channel.queueBind(queueName, EXCHANGE_NAME, "*.critical");  
  
        System.out  
                .println(" [*] Waiting for critical messages. To exit press CTRL+C");  
  
        QueueingConsumer consumer = new QueueingConsumer(channel);  
        channel.basicConsume(queueName, true, consumer);  
  
        while (true)  
        {  
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();  
            String message = new String(delivery.getBody());  
            String routingKey = delivery.getEnvelope().getRoutingKey();  
  
            System.out.println(" [x] Received routingKey = " + routingKey  
                    + ",msg = " + message + ".");  
        }  
    }  
}  
