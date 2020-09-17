package com.yc.damai.dao;

import java.util.List;

import com.yc.damai.po.DmCategory;

public interface DmCategoryMapper {

	List<DmCategory> selectAll();
	int insert(DmCategory dc);
	int update(DmCategory dc);
	int delete(int id);
	List<DmCategory> selectChildren();
}
