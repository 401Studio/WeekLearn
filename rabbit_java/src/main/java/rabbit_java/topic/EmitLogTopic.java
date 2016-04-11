package rabbit_java.topic;

import java.util.UUID;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;

import utils.MQConfig;
import utils.RabbitMQConnectionFactory;

public class EmitLogTopic {

   // private static final String EXCHANGE_NAME = "topic_logs";
    
    private static final String EXCHANGE_NAME = MQConfig.getTopicExchange(0);
    
    public static void main(String[] argv)
                  throws Exception {

    	ConnectionFactory factory =RabbitMQConnectionFactory.getFactory();
    	
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        String[] routing_keys = new String[] { "kernal.info", "cron.warning",  
                "auth.info", "kernel.critical" };  
        for (String routing_key : routing_keys)  
        {  
            String msg = UUID.randomUUID().toString();  
            channel.basicPublish(EXCHANGE_NAME, routing_key, null, msg  
                    .getBytes());  
            System.out.println(" [x] Sent routingKey = "+routing_key+" ,msg = " + msg + ".");  
        }  
  
        channel.close();  
        connection.close();  
    }
    //...
}