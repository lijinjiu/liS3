package day0906;

import java.io.IOException;

/**
 * Servlet继承关系 
 * Servlet---》GenericeServlet---》HttpServlet----》自定义Servlet
 * */
public interface Servlet {
	public void service(HttpServletRequest request,HttpServletResponse response) throws IOException;
}
