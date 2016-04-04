package com.rabbitmq.test.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by wuhuachuan on 16/3/3.
 */

@EnableAutoConfiguration
@ComponentScan(basePackages = { "com.rabbitmq.*" })
@EntityScan(basePackages = { "com.rabbitmq.*" })
@EnableJpaRepositories(basePackages = { "com.rabbitmq.*" })
public class Boot {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Boot.class, args);
    }
}
