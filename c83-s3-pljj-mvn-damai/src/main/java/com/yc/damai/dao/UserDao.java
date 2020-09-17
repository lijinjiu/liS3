package com.yc.damai.dao;

import java.util.List;
import java.util.Map;

import com.yc.damai.util.DBHelper;

public class UserDao {
  public void insert(String ename,String cname,String password,String email,String phone,String sex) {
	  String sql="insert into dm_user values(null,?,?,?,?,?,?,1,now()) ";
	  new DBHelper().update(sql, ename,cname,password,email,phone,sex);
  }
  
  public Map<String,Object> selectByLogin(String ename,String password){
	  String sql="select * from dm_user where ename=? and password=?";
	  DBHelper db=new DBHelper();
	  List<Map<String,Object>> list=db.query(sql, ename,password); 
	  if(list.size()==0) {
		  return null;
	  }else {
		  Map<String,Object> user=list.get(0);
		  return user;
	  }
	  
  }
}
