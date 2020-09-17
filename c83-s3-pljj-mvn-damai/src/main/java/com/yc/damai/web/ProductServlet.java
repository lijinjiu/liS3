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

import org.apache.commons.beanutils.BeanUtils;

import com.sun.org.apache.regexp.internal.REUtil;
import com.yc.damai.dao.ProductDao;
import com.yc.damai.po.DmProduct;
import com.yc.damai.po.Result;
import com.yc.damai.util.DBHelper;

import redis.clients.jedis.Jedis;

@WebServlet("/Product.do")
public class ProductServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    private ProductDao pdao=new ProductDao();
	protected void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sql="select * from dm_product where is_hot=1 order by id desc limit 0,10";
		List<?> list=new DBHelper().query(sql);
		HashMap<String, Object> page=new HashMap<>();
		page.put("list", list);
		print(response,page);
	}
	
	//后台分页查询
	protected void query1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IllegalAccessException, InvocationTargetException {
		String page=request.getParameter("page");
		String rows=request.getParameter("rows");
		
		
		/**
		 * 对象的封装    参数（前台）-----实体类里
		 * bean  要装载的实体对象
		 * properties  存放属性值的map 集合
		 * request.getParameterMap()---所有的参数封装成map集合
		 * */
		DmProduct dp=new DmProduct();
		BeanUtils.populate(dp, request.getParameterMap());
		
		
		
		List<?> list=pdao.query1(dp,page, rows);
		int total=pdao.count1(dp);
		HashMap<String,Object> data=new HashMap<>();
		//分页数  必要参数
		data.put("rows",list);
		data.put("total",total);
		print(response,data);
	}
	
		protected void querynew(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String sql="select * from dm_product ORDER BY createtime desc limit 0,10";
			List<?> list=new DBHelper().query(sql);
			HashMap<String, Object> page=new HashMap<>();
			page.put("list", list);
			print(response,page);
		}
		

	
	protected void queryProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id=request.getParameter("id");
		String sql = "select * from dm_product p INNER JOIN dm_category c on p.cid=c.id where c.id=? limit 0,12";
		 
		List<?> list = new DBHelper().query(sql,id);
		HashMap<String, Object> page = new HashMap<>();
		page.put("list", list);
		print(response, page);
	//	System.out.println("list"+list);
	}
	
	//查询某件商品  detail
	protected void queryById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		String sql="select * from dm_product where id=?";
		List<?> list=new DBHelper().query(sql,id);
		/**
		 * 将访问次数Redis数据库 Redis开发很大工作是设计键值  key 值
		 * id---- key product:4   值:1,2,3
		 *            product:5   值:数字
		 * */
		
		 // Jedis jd=new Jedis(); //incr返回增长的值 原值加1 实现自增 
		 // long cnt=jd.incr("id:"+id);
		
		print(response,list.get(0));   //一条数据
	}
	
	protected void queryCate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sql = "SELECT * from dm_category where pid is null";
		List<?> list = new DBHelper().query(sql);
		HashMap<String, Object> page = new HashMap<>();
		page.put("list", list);
		print(response, page);
	}

	
	//保存商品  页面接受商品实体属性
	protected void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IllegalAccessException, InvocationTargetException {
		DmProduct dp=new DmProduct();
		//装载方法
		BeanUtils.populate(dp, request.getParameterMap());
		//商品名称验证 空值验证  长度判断
		if(dp.getPname()==null||dp.getPname().trim().isEmpty()) {
			print(response, new Result(0, "商品名称不能为空！"));
			return;
		}
		if(dp.getShopPrice()==null||dp.getShopPrice()<=0) {
			print(response, new Result(0, "商品价格必须大于0！"));
			return;
		}
		if(dp.getId()==null) {
			pdao.insert(dp);
		}else {
			pdao.update(dp);
		}
		print(response, new Result(1, "商品保存成功!"));
	}

}
