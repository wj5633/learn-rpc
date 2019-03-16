package com.wj5633.demo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created with IntelliJ IDEA.
 *
 * @author wangjie
 * @version 1.0.0
 * @create 2019/3/7 1:31
 * @description
 */

public class EchoServerhandler extends SimpleChannelInboundHandler {

    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        ByteBuf buf = (ByteBuf) o;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);

        String body = new String(req);
        System.out.println("receove data from client:" + body);

        ByteBuf resp = Unpooled.copiedBuffer(body.getBytes());
        channelHandlerContext.write(resp);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
}
