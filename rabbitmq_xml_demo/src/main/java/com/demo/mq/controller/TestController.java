package com.demo.mq.controller;

import com.demo.mq.producer.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/demo/")
public class TestController {
    private final static Logger logger = LoggerFactory.getLogger(TestController.class);
    @Resource
    private Producer producer;


    @RequestMapping("/test/{msg}")
    public String send(@PathVariable("msg") String msg){
        logger.info("#TestController.send#abc={msg}", msg);
        System.out.println("msg="+msg);
        producer.sendDataToQueue("test_queue_key",msg);
        return "index";
    }
}
