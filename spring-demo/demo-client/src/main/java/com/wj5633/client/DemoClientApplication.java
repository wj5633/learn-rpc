package com.wj5633.client;

import com.wj5633.common.model.User;
import com.wj5633.common.service.IUserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DemoClientApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(DemoClientApplication.class, args);

        IUserService userService = (IUserService) context.getBean("userService");
        User user = userService.findByName("wang");

        System.out.println(user);
    }

}
