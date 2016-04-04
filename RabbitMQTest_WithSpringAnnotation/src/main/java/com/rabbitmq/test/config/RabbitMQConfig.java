package com.rabbitmq.test.config;

import lombok.Data;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


/**
 * Created by wuhuachuan on 16/3/28.
 */
@Data
@Configuration
@EnableRabbit
@ComponentScan(basePackages = "com.rabbitmq.test.Receiver")
public class RabbitMQConfig {

    final static String queueName = "spring-boot";

    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange("spring-boot-exchange");
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(queueName);
    }

    @Bean(name = "helloRabbitListenerContainer")
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory,MessageConverter messageConverter) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setMessageConverter(messageConverter);
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }

    @Bean
    public MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory,MessageConverter messageConverter){
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setMessageConverter(messageConverter);
        rabbitTemplate.setConnectionFactory(connectionFactory);
        return rabbitTemplate;
    }
}
