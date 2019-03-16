package com.wj5633.demo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * Created with IntelliJ IDEA.
 *
 * @author wangjie
 * @version 1.0.0
 * @create 2019/3/7 3:24
 * @description
 */

@Slf4j
public class EchoClientHandler extends SimpleChannelInboundHandler {

    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {

        ByteBuf buf = (ByteBuf) o;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);

        String body = new String(req);
        System.out.println("receive data from server :" + body);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.info("Unexpected exception from downstream :" + cause.getMessage());
        ctx.close();
    }
}
