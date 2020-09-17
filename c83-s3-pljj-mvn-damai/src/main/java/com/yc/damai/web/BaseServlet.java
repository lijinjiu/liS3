package com.yc.damai.web;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 所有的业务Servlet的父类,BaseServlet不能被直接创建成对象,如何从语法上确保
 * servlet的封装     abstract抽象  公共的
 * */
//@WebServlet("/BaseAction")
//servlet公共类 获取参数op
public abstract class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * productServlet   商品操作的Servlet 商品的查询修改删除...
	 * 参数
	 * product.do?op=query  查询
	 * product.do?op=add    新增
	 * product.do?op=del    删除
	 * */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		  request.setCharacterEncoding("utf-8");
		  response.setCharacterEncoding("utf-8");
		  response.setContentType("text/html;charset=utf-8");
		 
		
		//获取操作字段
		String op=request.getParameter("op");
		//java黑科技  -----反射技术
		//通过op获取方法对象     ****方法----对象
		try {
			Method method=this.getClass().getDeclaredMethod(op, HttpServletRequest.class,HttpServletResponse.class);
		    //设置方法可以被访问
			method.setAccessible(true);
			// 执行方法
			method.invoke(this, request,response);
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().append("系统错误!");
		} 
		
		// 捕获方法
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void print(HttpServletResponse response,Object obj) throws IOException {
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		response.getWriter().print(gson.toJson(obj));
	}
}
