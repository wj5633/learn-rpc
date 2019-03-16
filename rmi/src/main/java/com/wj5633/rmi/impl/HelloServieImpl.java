package com.wj5633.rmi.impl;

import com.wj5633.rmi.IHelloService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created with IntelliJ IDEA.
 *
 * @author wangjie
 * @version 1.0.0
 * @create 2019/2/28 0:09
 * @description
 */
public class HelloServieImpl extends UnicastRemoteObject implements IHelloService {

    public HelloServieImpl() throws RemoteException {
        super();
    }

    public String sayHello(String str) throws RemoteException {
        return "Hello, " + str;
    }
}
