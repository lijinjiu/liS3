package com.yc.damai.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yc.damai.dao.CartDao;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/cart.do")
public class CartServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    private CartDao cdao=new CartDao();
    //cart.do?op=add&pid=???
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         // String uid="2";   //鐢ㄦ埛浠庝細璇濅腑鑾峰彇loginedUser.getId(),
		
		  HttpSession session=request.getSession(); Map<String,Object>
		  user=(Map<String, Object>) session.getAttribute("loginedUser");
		  String uid=String.valueOf(user.get("id")) ;
		  System.out.println("-----"+uid+"-------");
		 
          String pid=request.getParameter("pid");
          if(cdao.update(uid,pid)==0) {
        	  //缁撴灉涓�0 璇存槑璇ョ敤鎴疯繕娌℃湁娣诲姞杩囧晢鍝�
        	  cdao.insert(uid, pid);
          }
          response.getWriter().append("{\"msg\":\"娣诲姞璐墿杞︽垚鍔燂紒\"}");
	}
    
	protected void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id=request.getParameter("id");
        cdao.deleteByid(id);
        response.getWriter().append("{\"msg\":\"商品删除成功！\"}");
	}
	protected void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //String uid="2";   //鐢ㄦ埛浠庝細璇濅腑鑾峰彇loginedUser.getId(),
		
		  HttpSession session=request.getSession(); Map<String,Object>
		  user=(Map<String, Object>) session.getAttribute("loginedUser"); String
		  uid=String.valueOf(user.get("id")) ;
		  System.out.println("-----"+uid+"-------");
		 
        List<?> list=cdao.queryByUid(uid);
        print(response,list);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
