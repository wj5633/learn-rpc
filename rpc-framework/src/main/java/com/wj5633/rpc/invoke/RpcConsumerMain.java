package com.wj5633.rpc.invoke;

import com.wj5633.rpc.framework.ConsumerProxy;
import com.wj5633.rpc.service.HelloService;

/**
 * Created with IntelliJ IDEA.
 *
 * @author wangjie
 * @version 1.0.0
 * @create 2019/2/28 3:00
 * @description
 */

public class RpcConsumerMain {

    public static void main(String[] args) throws InterruptedException {
        HelloService service = ConsumerProxy.consume(HelloService.class, "127.0.0.1", 8083);

        for (int i = 0; i < 1000; i++) {
            String hello = service.sayHello("wang_" + i);
            System.out.println(hello);
            Thread.sleep(1000);
        }
    }
}
