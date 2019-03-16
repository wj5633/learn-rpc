package com.wj5633.test;

import com.wj5633.test.service.HelloService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DemoClientApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(DemoClientApplication.class, args);

        HelloService helloService = (HelloService) context.getBean("helloService");
        helloService.sayHello("wang");

    }

}
