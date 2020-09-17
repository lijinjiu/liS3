package com.yc.damai.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.yc.damai.dao.CartDao;
import com.yc.damai.dao.OrderitemDao;
import com.yc.damai.dao.OrdersDao;
import com.yc.damai.po.DmAddress;
import com.yc.damai.po.DmOrders;
import com.yc.damai.po.DmProduct;
import com.yc.damai.po.DmUser;
import com.yc.damai.po.Result;



/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/orders.do")
public class OrdersServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    private OrdersDao odao=new OrdersDao();
    private OrderitemDao oidao=new OrderitemDao();
    private CartDao cdao=new CartDao();
    //添加订单
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         // String uid="2";   //用户从会话中获取loginedUser.getId(),
		HttpSession session=request.getSession(); Map<String,Object>
		  user=(Map<String, Object>) session.getAttribute("loginedUser"); String
		  uid=String.valueOf(user.get("id")) ;
		  System.out.println("-----"+uid+"-------");
		  
		odao.insert(uid);
          oidao.insert(uid); 
    	  cdao.deleteByUid(uid);
          response.getWriter().append("{\"code\":\"1\"}");
	}
    
	//查询新增的订单
	protected void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       // String uid="2";   //用户从会话中获取loginedUser.getId(),
		HttpSession session=request.getSession(); Map<String,Object>
		  user=(Map<String, Object>) session.getAttribute("loginedUser"); String
		  uid=String.valueOf(user.get("id")) ;
		  System.out.println("-----"+uid+"-------");
		 
        Map<String,Object> ret=new HashMap<>();
        Map<String,Object> orders=odao.queryNewOrders(uid);
        ret.put("orders",orders);
        ret.put("orderitem",oidao.queryNewOrders(""+orders.get("id")));
        print(response, ret);
	}
	
  protected void query1(HttpServletRequest request, HttpServletResponse response) throws IOException, IllegalAccessException, InvocationTargetException {
	  String page=request.getParameter("page");
	  String rows=request.getParameter("rows");
	  
	  DmOrders Do=new DmOrders();
	  BeanUtils.populate(Do, request.getParameterMap());
	  
	  List<Map<String,Object>> list=odao.query1(Do,page,rows);
	  int total=odao.count1(Do);
	  HashMap<String,Object> data=new HashMap<>();
	  data.put("rows", list);
	  data.put("total", total);
	  print(response, data);
  }
	
//保存商品  页面接受商品实体属性
	protected void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IllegalAccessException, InvocationTargetException {
		String id=request.getParameter("id");
		String name=request.getParameter("name");
		String total=request.getParameter("total");
		String addr=request.getParameter("addr");
		String state=request.getParameter("state");
		String createtime=request.getParameter("createtime");
		if(name==null||name.trim().isEmpty()) {
			print(response,new Result(0, "用户不能为空"));
			return;
		}
		if(total==null) {
			print(response, new Result(0, "金额不能为空！"));
			return;
		}
		if(addr==null) {
			print(response, new Result(0, "地址不能为空！"));
			return;
		}
		odao.update(id,name,total,addr,state,createtime);
		print(response, new Result(1, "订单修改成功!"));
	}

	//删除订单
	protected void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id=request.getParameter("id");
        odao.deleteByid(id);
        print(response, new Result(1, "删除订单成功！"));
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
