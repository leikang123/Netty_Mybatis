package servlet;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;

import java.io.UnsupportedEncodingException;
/**
 * 手写tomcat的第二步骤
 * 写响应类，包含的各个字段
 * @author mac1094
 *
 */

// 自定义响应类
public class CustomHttpResponse {
	// 请求属性
    private HttpRequest request;
    // 请求处理器上下文
    private ChannelHandlerContext context;
    
    // 构造方法
    public CustomHttpResponse(HttpRequest request, ChannelHandlerContext context) {
        this.request = request;
        this.context = context;
    }
   // 定义写的方法参数上下文
    public void write(String content) throws Exception {
        // 创建响应对象
        FullHttpResponse response = new DefaultFullHttpResponse(
                HttpVersion.HTTP_1_1,
                HttpResponseStatus.OK,
                Unpooled.wrappedBuffer(content.getBytes("UTF-8")));

        // 初始化响应头
        // 获取到响应头
        HttpHeaders headers = response.headers();
        // 设置响应内容类型
        headers.set(HttpHeaderNames.CONTENT_TYPE, "text/json");
        // 设置响应体长度
        headers.set(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
        // 设置缓存过期时长
        headers.set(HttpHeaderNames.EXPIRES, 0);
        // 若HTTP请求连接是长连接，则设置响应连接也为长连接
        if(HttpUtil.isKeepAlive(request)) {
            headers.set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        }

        // 将响应对象写入到Channel
        context.writeAndFlush(response);
    }
}
