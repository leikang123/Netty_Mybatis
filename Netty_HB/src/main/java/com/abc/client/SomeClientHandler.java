package com.abc.client;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.ScheduledFuture;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class SomeClientHandler extends ChannelInboundHandlerAdapter {

    // 当Channel被激活后会触发该方法的执行
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 随机发送心跳
        randomSendHeartBeat(ctx.channel());
    }

    // 随机发送心跳
    private void randomSendHeartBeat(Channel channel) {
        // 生成一个[1，8)的随机数，作为心跳发送间隔
        int hearBeatInternal = new Random().nextInt(7) + 1;
        System.out.println(hearBeatInternal + "秒后将发送下一次心跳");

        ScheduledFuture<?> schedule = channel.eventLoop().schedule(() -> {
            if(channel.isActive()) {
                System.out.println("向Server发送心跳");
                channel.writeAndFlush("~PING~");
            } else {
                System.out.println("与Server的连接已经断开");
                channel.closeFuture();
            }
        }, hearBeatInternal, TimeUnit.SECONDS);

        // 为异步定时任务添加监听器
        schedule.addListener((future) -> {
                    // 若异步定时任务执行成功，则重新再随机发送心跳
                    if(future.isSuccess()) {
                        randomSendHeartBeat(channel);
                    }
                });

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
