package day0906;

public class LoginPageServlet extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies= request.getCookies();
		/*
		 * response.getWriter().append(cookies[0].toString());
		 * response.getWriter().append(cookies[1].toString());
		 */
		response.getWriter().append("�û���:<input value='"+cookies[0].getValue()+"'><br>");
		response.getWriter().append("����:<input value=''><br>");
		response.getWriter().append("<input type='button' value='��¼'><br>");
		
	}
}
