package com.yc.damai.dao;

import java.util.List;

import com.yc.damai.po.DmCategory;
import com.yc.damai.po.DmOrderitem;
import com.yc.damai.po.DmProduct;

public interface DmOrderitemMapper {
    //集合
	List<DmOrderitem> selectAll();
	//单个实体对象
	DmOrderitem selectById(int id);
	//新增订单明细
	int insert(DmOrderitem doi);
}
