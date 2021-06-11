package com.abc;

import servlet.CustomHttpRequest;
import servlet.CustomHttpResponse;
import servlet.CustomHttpServlet;
/**
 * 手写tomcat的第三大步
 * 业务servlet的定义，就是使用起来
 * @author mac1094
 *
 */
// 业务Servlet
public class SomeServlet extends CustomHttpServlet {
    @Override
    public void doGet(CustomHttpRequest request, CustomHttpResponse response) throws Exception {
        String uri = request.getUri();
        String path = request.getPath();
        String param = request.getParameter("name");
        String method = request.getMethod();
        String content = "method = " + method + "\n" +
                         "uri = " + uri + "\n" +
                         "path = " + path + "\n" +
                         "param = " + param;

        response.write(content);
    }

    @Override
    public void doPost(CustomHttpRequest request, CustomHttpResponse response) throws Exception {
        doGet(request, response);
    }
}
