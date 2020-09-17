package com.yc.damai.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yc.damai.po.DmOrders;
import com.yc.damai.po.DmProduct;
import com.yc.damai.util.DBHelper;

public class OrdersDao {
	
	//添加订单主要记录
	public int insert(String uid) {
		String sql = 
				"INSERT INTO dm_orders SELECT\n" +
						"	NULL,\n" +
						"	c.total,\n" +
						"	now(),\n" +
						"	0,\n" +
						"	a.id,\n" +
						"	b.id\n" +
						"FROM\n" +
						"	dm_user a\n" +
						"JOIN dm_address b ON a.id = b.uid\n" +
						"AND dft = 1\n" +
						"JOIN (\n" +
						"	SELECT\n" +
						"		sum(b.shop_price * a.count) total,\n" +
						"		a.uid\n" +
						"	FROM\n" +
						"		dm_cart a\n" +
						"	JOIN dm_product b ON a.pid = b.id\n" +
						"	WHERE\n" +
						"		a.uid = ?\n" +
						"	GROUP BY\n" +
						"		a.uid\n" +
						") c ON a.id = c.uid\n" +
						"WHERE\n" +
						"	a.id = ?";
		return new DBHelper().update(sql, uid, uid);
	}

	public Map<String,Object> queryNewOrders(String uid){
		String sql="select a.*,b.addr,b.phone,b.name from dm_orders a join dm_address b on a.aid=b.id "
				+ "where a.uid=? and state=0 order by id desc limit 0,1";
		List<Map<String,Object>> list=new DBHelper().query(sql, uid);
		if(list.isEmpty()) {
			return null;
		}else {
			return list.get(0);
		}
	}

	//订单分页查询
  public List<Map<String,Object>>  query1(DmOrders Do, String page,String rows){
	  String where="";
	  List<Object> params=new ArrayList<>();

	  if(Do.getState()!=null) {
		  where +=" and state = ?";
		  params.add(Do.getState());
	  }
	  
	  int ipage=Integer.parseInt(page);
	  int irows=Integer.parseInt(rows);
	  ipage=(ipage-1)*10;
	  String sql="SELECT\r\n" + 
	  		"	a.*, b.`name`,\r\n" + 
	  		"	b.addr\r\n" + 
	  		"FROM\r\n" + 
	  		"	dm_orders a\r\n" + 
	  		"JOIN dm_address b ON a.aid = b.id"
	  		+ " where 1=1"
	  		+ where
	  		+ " limit ?,?";
	  params.add(ipage);
	  params.add(irows);
	  return new DBHelper().query(sql,params.toArray());
  }
  
  
  public int  count1(DmOrders Do){
	  String where="";
	  List<Object> params=new ArrayList<>();
	  
	  if(Do.getState()!=null) {
		  where +=" and state = ?";
		  params.add(Do.getState());
	  }
	  
	  String sql="select * from dm_orders where 1=1"+ where;
	  return new DBHelper().count(sql,params.toArray());
  }
  

  //修改订单信息
  public void update(String id,String name,String total,String addr,String state,String createtime) {
		 String sql="UPDATE dm_address a,\r\n" + 
		 		" dm_orders b\r\n" + 
		 		"SET a.addr =?, b.state =?, a.name =?, b.total =?, b.createtime =?\r\n" + 
		 		"WHERE\r\n" + 
		 		"	a.id = b.aid\r\n" + 
		 		"AND b.id =?";
		  new DBHelper().update(sql,addr,state,name,total,createtime,id );
	}
 // 删除订单
  public int deleteByid(String id) {
		 String sql="delete from dm_orders where id=?";
		 return new DBHelper().update(sql,id);
	  } 
  
  
	  public static void main(String[] args) {
	  //这种写法有数据库事物问题    ---这不是一个事物
		
		  new OrdersDao().insert("2"); 
		  new OrderitemDao().insert("2"); 
	  //出现异常 会导致订单被创建   但订单没删除
		  new CartDao().deleteByUid("2");
		 
	  }
	 
}
