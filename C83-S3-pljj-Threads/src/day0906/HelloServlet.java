package day0906;

import java.io.PrintWriter;

public class HelloServlet extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		//http://127.0.0.1:8080/photo/hello?name=jhon
		String name=request.getParameter("name");
		
		System.out.println("hello world!");
		PrintWriter out=response.getWriter();
		//输出到页面
		out.print("你好!"+name);
	}
}
