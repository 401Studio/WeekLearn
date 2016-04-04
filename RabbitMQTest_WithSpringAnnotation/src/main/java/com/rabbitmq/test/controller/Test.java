package com.rabbitmq.test.controller;

import com.rabbitmq.test.model.Teacher;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wuhuachuan on 16/3/28.
 */

@RestController
public class Test {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @RequestMapping(value = "/testTeacher",method = RequestMethod.GET)
    public String testTeacher(){
        rabbitTemplate.convertAndSend("spring-boot", new Teacher("teacherName"));
        return  "teacherok";
    }
}
