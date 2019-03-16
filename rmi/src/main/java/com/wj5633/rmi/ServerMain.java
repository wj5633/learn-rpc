package com.wj5633.rmi;

import com.wj5633.rmi.impl.HelloServieImpl;

import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.RMISocketFactory;

/**
 * Created with IntelliJ IDEA.
 *
 * @author wangjie
 * @version 1.0.0
 * @create 2019/2/28 0:11
 * @description
 */

public class ServerMain {

    public static void main(String[] args) throws IOException, AlreadyBoundException {
        // 注册服务
        LocateRegistry.createRegistry(8801);

        // 指定通信端口，防止被防火墙拦截
        RMISocketFactory.setSocketFactory(new CustomerSocketFactory());

        // 创建服务
        IHelloService helloService = new HelloServieImpl();

        Naming.bind("rmi://localhost:8801/helloService", helloService);

        System.out.println("ServerMain provide RPC controller now.");
    }
}
