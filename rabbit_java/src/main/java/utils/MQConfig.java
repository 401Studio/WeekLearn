package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MQConfig {

	private static volatile Properties prop;
	private static volatile String[] queueNames;
	private static volatile String[] directExchanges;
	private static volatile String[] fanoutExchanges;
	private static volatile String[] topicExchanges;
	private static volatile String[] routingKeys;

	static {
		loadConfig();
	}

	public static String getQueueName(int index) {
		return queueNames[index];
	}

	public static String[] getQueueNames() {
		return queueNames;
	}

	public static String getDirectExchange(int index) {
		return directExchanges[index];
	}

	public static String[] getDirectExchanges() {
		return directExchanges;
	}

	public static String getFanoutExchange(int index) {
		return fanoutExchanges[index];
	}

	public static String[] getFanoutExchanges() {
		return fanoutExchanges;
	}

	public static String getTopicExchange(int index) {
		return topicExchanges[index];
	}

	public static String[] getTopicExchanges() {
		return topicExchanges;
	}

	public static String getRoutingKey(int index) {
		return routingKeys[index];
	}

	public static String[] getRoutingKeys() {
		return routingKeys;
	}
	
	
	public static void loadConfig(){
		
		prop = new Properties();
		try {
			InputStream is = new FileInputStream(MQConfig.class.getResource("/").getPath() + "mq.properties");
			prop.load(is);
			queueNames = prop.getProperty("queueNames").split(",");
			directExchanges = prop.getProperty("directExchanges").split(",");
			fanoutExchanges = prop.getProperty("fanoutExchanges").split(",");
			topicExchanges = prop.getProperty("topicExchanges").split(",");
			routingKeys = prop.getProperty("routingKeys").split(",");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void main(String[] args) {
		
		System.out.println(prop.getProperty("queueNames"));
		System.out.println( prop.getProperty("directExchanges"));
		System.out.println( prop.getProperty("fanoutExchanges"));
		System.out.println( prop.getProperty("topicExchanges"));
		System.out.println( prop.getProperty("routingKeys"));
		
	}
}
