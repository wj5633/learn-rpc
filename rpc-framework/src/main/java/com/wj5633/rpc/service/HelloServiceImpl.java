package com.wj5633.rpc.service;

/**
 * Created with IntelliJ IDEA.
 *
 * @author wangjie
 * @version 1.0.0
 * @create 2019/2/28 2:41
 * @description
 */

public class HelloServiceImpl implements HelloService {
    public String sayHello(String content) {
        return "hello, " + content;
    }
}
