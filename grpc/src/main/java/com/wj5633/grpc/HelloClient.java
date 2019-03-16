package com.wj5633.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 *
 * @author wangjie
 * @version 1.0.0
 * @create 2019/2/28 1:15
 * @description
 */

public class HelloClient {
    // 远程连接管理器
    private final ManagedChannel channel;
    // 远程服务 stub
    private final HelloServiceGrpc.HelloServiceBlockingStub blockingStub;

    public HelloClient(String host, int port) {
        channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext(true)
                .build();

        blockingStub = HelloServiceGrpc.newBlockingStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    public String sayHello(String name) {
        HelloRequest request = HelloRequest.newBuilder().setName(name).build();

        HelloResponse response = blockingStub.sayHello(request);
        return response.getMessage();
    }

    public static void main(String[] args) throws InterruptedException {
        HelloClient client = new HelloClient("127.0.0.1", 50051);
        // 服务调用
        String content = client.sayHello("wang");

        System.out.println(content);
        client.shutdown();
    }
}
