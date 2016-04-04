package com.rabbitmq.test.Receiver;

import com.rabbitmq.test.model.Teacher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by wuhuachuan on 16/3/28.
 */
@Slf4j
@Component
@RabbitListener(containerFactory = "helloRabbitListenerContainer",queues = "spring-boot")
public class Receiver {
    @RabbitHandler
    public void receiveTeacher(Teacher teacher) {

        log.info("##### = {}",teacher);
    }

}
