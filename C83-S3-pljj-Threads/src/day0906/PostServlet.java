package day0906;

public class PostServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		response.getWriter().append("post");
	}
}
 