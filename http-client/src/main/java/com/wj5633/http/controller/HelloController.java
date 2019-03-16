package com.wj5633.http.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Random;


@RestController
public class HelloController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public ApiResponse sayHello(@RequestParam(value = "name") String name, HttpServletRequest request) {

        HttpSession session = request.getSession();

        String logined = (String) session.getAttribute(name);

        if (logined != null) {
            return ApiResponse.success(logined + " is already logined!");
        }
        session.setAttribute(name, name);

        try {
            Thread.sleep(new Random().nextInt(10));
        } catch (Exception e) {
        }

        return ApiResponse.success("hello," + name);
    }


}
