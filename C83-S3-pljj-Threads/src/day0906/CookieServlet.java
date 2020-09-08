package day0906;

public class CookieServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		/**
		 * 向浏览器发送Cookie值 name=武松
		 * 
		 * */
		Cookie cookie=new Cookie("name","wusong");
		cookie.setMaxAge(60*60*24);
		response.addCookie(cookie);
		
		cookie=new Cookie("sex","0");
		cookie.setMaxAge(60*60*24);
		response.addCookie(cookie);
		response.getWriter().append("cookie");
		
		//为Cookie对象设置过期时间maxAge
	}
}
 