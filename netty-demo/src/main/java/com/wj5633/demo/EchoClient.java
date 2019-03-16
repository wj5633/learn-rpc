package com.wj5633.demo;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created with IntelliJ IDEA.
 *
 * @author wangjie
 * @version 1.0.0
 * @create 2019/3/7 1:35
 * @description
 */

public class EchoClient {
    public static void main(String[] args) {
        int port = 8080;
        String host = "127.0.0.1";
        new EchoClient().connect(host, port);
    }

    private void connect(String host, int port) {
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<NioSocketChannel>() {
                        protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                            nioSocketChannel.pipeline().addLast(new EchoClientHandler());
                        }
                    });

            ChannelFuture cf = bootstrap.connect(host, port).sync();

            byte[] req = "Hello world!".getBytes();
            ByteBuf byteBuf = Unpooled.buffer(req.length);
            byteBuf.writeBytes(req);

            ChannelFuture channelFuture = cf.channel().writeAndFlush(byteBuf);
            channelFuture.syncUninterruptibly();
            cf.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }
}
