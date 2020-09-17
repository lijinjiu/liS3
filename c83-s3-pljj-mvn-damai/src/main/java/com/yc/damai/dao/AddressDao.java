package com.yc.damai.dao;

import com.yc.damai.po.DmAddress;
import com.yc.damai.util.DBHelper;

//添加地址
public class AddressDao {
	
	public void insert(DmAddress da) {
	//	String uid = "2";
		String sql = "insert into dm_address values (null,?,?,?,?,?,now())";
		new DBHelper().update(sql, 
				da.getUid(),
				da.getAddr(), 
				da.getPhone(),
				da.getName(),
				da.getDft());
	}
	
  public void Addressedit(DmAddress da) {
	  String sql="UPDATE dm_address\n" +
			  "SET addr = ?,\n" +
			  " phone = ?,\n" +
			  " NAME = ?,\n" +
			  " dft = ?\n" +
			  "WHERE\n" +
			  "	id = ?";
	new DBHelper().update(sql,
			  da.getAddr(),
			  da.getPhone(),
			  da.getName(),
			  da.getDft(),
			  da.getId());
  }	

}

