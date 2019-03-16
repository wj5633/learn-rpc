package com.wj5633.rpc.framework;

import org.apache.commons.lang3.reflect.MethodUtils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with IntelliJ IDEA.
 *
 * @author wangjie
 * @version 1.0.0
 * @create 2019/2/28 2:50
 * @description
 */

public class ProviderReflect {

    private static final ExecutorService executorService = Executors.newCachedThreadPool();

    public static void provider(final Object service, int port) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            final Socket socket = serverSocket.accept();
            executorService.execute(new Runnable() {
                public void run() {
                    try {
                        ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                        try {
                            try {
                                String methodName = inputStream.readUTF();
                                Object[] args = (Object[]) inputStream.readObject();
                                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                                try {
                                    Object result = MethodUtils.invokeExactMethod(service, methodName, args);
                                    outputStream.writeObject(result);
                                } catch (Throwable t) {
                                    outputStream.writeObject(t);
                                } finally {
                                    outputStream.close();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            } finally {
                                inputStream.close();
                            }
                        } finally {
                            socket.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

}
