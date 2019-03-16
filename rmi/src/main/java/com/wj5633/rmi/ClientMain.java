package com.wj5633.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Created with IntelliJ IDEA.
 *
 * @author wangjie
 * @version 1.0.0
 * @create 2019/2/28 0:15
 * @description
 */

public class ClientMain {

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        // 服务引入
        IHelloService helloService = (IHelloService) Naming.lookup("rmi://localhost:8801/helloService");

        System.out.println("RMI 服务器返回结果：" + helloService.sayHello("wang"));
    }
}
