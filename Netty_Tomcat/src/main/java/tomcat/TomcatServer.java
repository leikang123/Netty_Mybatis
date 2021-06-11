package tomcat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.codec.http.HttpServerCodec;
/**
 * 手写tomcat的第二大类步骤
 * 1.定义服务启动类
 * 服务启动类就是启动处理器
 * @author mac1094
 *
 */

// 定义服务器启动类，创建并初始化服务器启动对象serverBootStrap
public class TomcatServer {
    public static void main(String[] args) throws InterruptedException {
        // 默认情况下，Group中会有当前主机逻辑内核数量的2倍的EventLoop
    	// 创建线程池父对象
        EventLoopGroup parentGroup = new NioEventLoopGroup();
        // 子对象
        EventLoopGroup childGroup = new NioEventLoopGroup();
        try {
        	// 创建服务端启动器
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(parentGroup, childGroup)
                    // 指定用于存放请求的队列的长度，默认为50，其是Socket的标准参数
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    // 指定使用心跳机制来维护长连接
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            // 获取ChannelPipline
                            ChannelPipeline pipeline = ch.pipeline();
                            // 向pipeline中添加解码器
                            pipeline.addLast(new HttpRequestDecoder());
                            // 向pipeline中添加编码器
                            pipeline.addLast(new HttpResponseEncoder());
                            // pipeline.addLast(new HttpServerCodec());
                            // 定义的处理器
                            pipeline.addLast(new TomcatServerHandler());
                        }
                    });
            // 创建操作方式
            ChannelFuture future = bootstrap.bind(8888).sync();
            System.out.println("Tomcat已启动，端口号为8888");
            future.channel().closeFuture().sync();
        } finally {
            parentGroup.shutdownGracefully();
            childGroup.shutdownGracefully();
        }
    }
}
