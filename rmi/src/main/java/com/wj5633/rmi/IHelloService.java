package com.wj5633.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created with IntelliJ IDEA.
 *
 * @author wangjie
 * @version 1.0.0
 * @create 2019/2/28 0:08
 * @description
 */
public interface IHelloService extends Remote {
    String sayHello(String str) throws RemoteException;
}
