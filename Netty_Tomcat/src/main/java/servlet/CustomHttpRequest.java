package servlet;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;

import java.util.List;
import java.util.Map;
/**
 * servlet的规范三步法
 * 1.request请求
 * 2.responese相应的相应法
 * 3.httpServlet类的抽象方法，doGet(),doPost()
 * 手写tomcat的第一步定义规范
 * servlet的规范
 * @author mac1094
 *自定义请求类
 *
 */
public class CustomHttpRequest {
	// 定义请求属性
    private HttpRequest request;
    // 定义通道处理器上下文
    private ChannelHandlerContext context;
   
    // 构造方法
    public CustomHttpRequest(HttpRequest request, ChannelHandlerContext context) {
        this.request = request;
        this.context = context;
    }

    // 获取请求的URI，其中是包含请求路径与请求参数的
    public String getUri() {
        return request.uri();
    }

    // 获取请求方式（POST、GET等）
    public String getMethod() {
        return request.method().name();
    }

    // 获取所有请求参数列表
    public Map<String, List<String>> getParameters() {
        QueryStringDecoder decoder = new QueryStringDecoder(request.uri());
        return decoder.parameters();
    }

    // 获取到请求路径
    public String getPath() {
        return new QueryStringDecoder(request.uri()).path();
    }

    // 获取指定名称的参数
    public List<String> getParameters(String name) {
        return this.getParameters().get(name);
    }
   
    // 获取指定的参数第一个值
    public String getParameter(String name) {
    	// 参数列表
        List<String> list = this.getParameters(name);
        if(list == null) {
            return null;
        }
        return list.get(0);
    }


}
