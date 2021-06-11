package servlet;

// 自定义Servlet规范，是一个抽象类httpServlet，里面有两个抽象方法
public abstract class CustomHttpServlet {
	// doGet方法
    public abstract void doGet(CustomHttpRequest request, CustomHttpResponse response) throws Exception;
    // doPost方法
    public abstract void doPost(CustomHttpRequest request, CustomHttpResponse response) throws Exception;
}
