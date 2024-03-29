package com.wj5633.rpc.framework;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 *
 * @author wangjie
 * @version 1.0.0
 * @create 2019/2/28 2:42
 * @description
 */

public class ConsumerProxy {

    public static <T> T consume(final Class<T> interfaceClass, final String host, final int port) {
        return (T) Proxy.newProxyInstance(
                interfaceClass.getClassLoader(),
                new Class[]{interfaceClass},
                new InvocationHandler() {
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Socket socket = new Socket(host, port);
                        try {
                            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                            try {
                                outputStream.writeUTF(method.getName());
                                outputStream.writeObject(args);
                                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                                try {
                                    Object result = inputStream.readObject();
                                    if (result instanceof Throwable) {
                                        throw (Throwable) result;
                                    }
                                    return result;
                                } finally {
                                    inputStream.close();
                                }
                            } finally {
                                outputStream.close();
                            }
                        } finally {
                            socket.close();
                        }
                    }
                });
    }
}
