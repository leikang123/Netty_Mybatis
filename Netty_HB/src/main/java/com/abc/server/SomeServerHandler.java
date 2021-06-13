package com.abc.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class SomeServerHandler extends SimpleChannelInboundHandler {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("接收到客户端发送的心跳：" + msg);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        // 若发生了读空闲超时，则将连接断开
        if(evt instanceof IdleStateEvent) {
            IdleState state = ((IdleStateEvent) evt).state();
            if(state == IdleState.READER_IDLE) {
                System.out.println("将连接断开");
                // 断开连接
                ctx.disconnect();
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
