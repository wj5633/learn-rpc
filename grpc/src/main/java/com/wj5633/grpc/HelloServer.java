package com.wj5633.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

/**
 * Created with IntelliJ IDEA.
 *
 * @author wangjie
 * @version 1.0.0
 * @create 2019/2/28 1:06
 * @description
 */

public class HelloServer {

    private int port = 50051;

    private Server server;

    private void start() throws Exception {
        server = ServerBuilder.forPort(port)
                .addService(new HelloServiceImpl())
                .build()
                .start();

        // 注册钩子，JVM 退出时停止服务
        Runtime.getRuntime().addShutdownHook(new Thread(HelloServer.this::stop));
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    private class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {

        public void sayHello(HelloRequest req, StreamObserver<HelloResponse> responseObserver) {
            HelloResponse response = HelloResponse.newBuilder().setMessage("hello, " + req.getName()).build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }

    public static void main(String[] args) throws Exception {
        final HelloServer server = new HelloServer();
        server.start();
        server.blockUntilShutdown();
    }
}
