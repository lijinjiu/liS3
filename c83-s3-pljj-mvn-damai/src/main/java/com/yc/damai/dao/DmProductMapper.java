package com.yc.damai.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.yc.damai.po.DmProduct;

public interface DmProductMapper {
    //集合
	List<DmProduct> selectAll();
	//单个实体对象
	DmProduct selectById(int id);
	List<DmProduct> selectByObj(DmProduct dp); 
	/**
	 * 根据枚举的分类id进行查询
	 * cids存放分类id的数组0-N
	 * */
	List<DmProduct> selectByCids(@Param("cids") int[] cids); 
	int update(DmProduct dp);
}
