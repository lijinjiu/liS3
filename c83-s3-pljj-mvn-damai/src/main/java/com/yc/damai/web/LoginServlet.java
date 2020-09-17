package com.yc.damai.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.damai.dao.UserDao;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserDao udao=new UserDao();
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String ename=request.getParameter("ename");
		String password=request.getParameter("password");
		String vcode=request.getParameter("vcode");
		//获取会话中的验证码
		String scode=(String) request.getSession().getAttribute("vcode");
		if(vcode!=null&&vcode.trim().isEmpty()==false) {
			if(!vcode.equalsIgnoreCase(scode)) {
				response.getWriter().append("验证码错误");
				return;
			}
		}else {
			response.getWriter().append("验证码错误");
			return;
		}
		Map<String,Object> user=udao.selectByLogin(ename, password);
		if(user!=null) {
			request.getSession().setAttribute("loginedUser", user);
			response.getWriter().print("登录成功");
		} else {
			response.getWriter().print("用户名或密码错误");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
