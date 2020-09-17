package com.yc.damai.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.damai.dao.UserDao;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/User.do")
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    private UserDao udao=new UserDao();
	protected void reg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ename=request.getParameter("ename");
		String cname=request.getParameter("cname");
		String password=request.getParameter("password");
		String repassword=request.getParameter("repassword");
		String email=request.getParameter("email");
		String phone=request.getParameter("phone");
		String sex=request.getParameter("sex");
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
		if(ename==null||ename.trim().isEmpty()) {
			response.getWriter().append("请输入用户名!");
		}else if(cname==null||cname.trim().isEmpty()) {
			response.getWriter().append("请输入您的姓名!");
		}else if(password==null||ename.trim().isEmpty()) {
			response.getWriter().append("请输入密码!");
		}else if(password.equals(repassword)==false) {
			response.getWriter().append("两次输入的密码不一致!");
		}else if(email==null||email.trim().isEmpty()) {
			response.getWriter().append("请输入邮箱号!");
		}else if(phone==null||phone.trim().isEmpty()) {
			response.getWriter().append("请输入号码!");
		}else if(sex==null) {
			response.getWriter().append("请选择性别!");
		}else {
			udao.insert(ename, cname, repassword, email, phone, sex);
			response.getWriter().append("用户注册成功!");
		}
	 }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
