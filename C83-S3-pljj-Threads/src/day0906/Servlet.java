package day0906;

import java.io.IOException;

/**
 * Servlet�̳й�ϵ 
 * Servlet---��GenericeServlet---��HttpServlet----���Զ���Servlet
 * */
public interface Servlet {
	public void service(HttpServletRequest request,HttpServletResponse response) throws IOException;
}
