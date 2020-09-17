 package com.yc.damai.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.yc.damai.po.DmCategory;
import com.yc.damai.po.DmOrderitem;
import com.yc.damai.po.DmOrders;
import com.yc.damai.po.DmProduct;

import junit.framework.Assert;



public class DmProductMapperTest {

	private SqlSession session;
	 { 
		try {
			String resource = "mybatis.xml";
			//读入配置文件 
			InputStream inputStream = Resources.getResourceAsStream(resource);
			//构建会话工厂
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			//开启会话
		    session= sqlSessionFactory.openSession();
		} catch (IOException e) {
			// TODO Auto-generated catch block 
			e.printStackTrace();
		}
	}
	@Test
	public  void test1() throws IOException {
		 List<DmProduct> list= session.selectList("com.yc.damai.dao.productMapper.selectAll");
		  for(DmProduct dp:list) { 
			  System.out.println(dp);
		}
		 //使用断言进行结果判断true标识期望值  list.size()>0实际值
		 Assert.assertEquals(true, list.size()>0);
	}
	
	@Test
	public  void test2() throws IOException {
		DmCategory dc=new DmCategory();
		dc.setCname("测试分类");
		dc.setPid(1);
		//获取映射接口实现类(核心技术)  动态代理(JDK)
		DmCategoryMapper mapper=session.getMapper(DmCategoryMapper.class);
		mapper.insert(dc);
		//不commit 会话会在关闭自动回滚
		session.commit();
		session.close();
	}
	
	@Test
	public  void test3() throws IOException {
		DmCategory dc=new DmCategory();
		dc.setId(88);
		dc.setCname("测试分类"); 
		dc.setPid(2);
		DmCategoryMapper mapper=session.getMapper(DmCategoryMapper.class);
		mapper.update(dc);
		//不commit 会话会在关闭自动回滚
		session.commit();
		session.close();
	}
	
	@Test
	public  void test4() throws IOException {
		DmCategoryMapper mapper=session.getMapper(DmCategoryMapper.class);
		mapper.delete(88);
		//不commit 会话会在关闭自动回滚
		session.commit();
		session.close(); 
	}
	
	@Test
	public  void test5 () throws IOException {
		/**
		 * 1.先查出一个订单明细记录
		 * 2.查出该订单明细对应的商品信息
		 * */
		/*
		 * DmOrderitemMapper dom=session.getMapper(DmOrderitemMapper.class);
		 * DmProductMapper dpm=session.getMapper(DmProductMapper.class);
		 * DmOrderitem doi=dom.selectById(62);
		 * DmProduct dp=dpm.selectById(doi.getPid());
		 */
		DmOrderitemMapper dom=session.getMapper(DmOrderitemMapper.class);
		DmOrderitem doi=dom.selectById(62);
		DmProduct dp=doi.getProduct();
		System.out.println(dp);
		//不commit 会话会在关闭自动回滚
	    session.commit();
		session.close(); 
	}
	
	@Test
	public  void test6() throws IOException {
		DmCategoryMapper mapper=session.getMapper(DmCategoryMapper.class);
		List<DmCategory> dcLsit= mapper.selectAll();
		DmCategory dc=dcLsit.get(1);
		Assert.assertEquals("鞋靴箱包", dc.getCname());
		Assert.assertEquals(6, dc.getChrildren().size());
		//不commit 会话会在关闭自动回滚
	    session.commit();
		session.close(); 
	}
	 
	@Test
	public  void test7() throws IOException {
		DmProductMapper mapper=session.getMapper(DmProductMapper.class);
		System.out.println("==========================");
		mapper.selectByObj(null);
		DmProduct dp=new DmProduct();
		System.out.println("==========================");
		mapper.selectByObj(dp);
		
		dp.setPname("测试");
		System.out.println("==========================");
		mapper.selectByObj(dp);
		
		dp.setPdesc("测试描述");
		System.out.println("==========================");
		mapper.selectByObj(dp);
		
		dp.setIsHot(- 1);
		System.out.println("==========================");
		
		dp.setIsHot(1);
		System.out.println("==========================");
		mapper.selectByObj(dp);
		//不commit 会话会在关闭自动回滚
	    session.commit();
		session.close(); 
	}
	
	@Test
	public  void test8() throws IOException {
		DmProductMapper mapper=session.getMapper(DmProductMapper.class);
		int[] cids= {1,2,3};
		mapper.selectByCids(cids);
		//不commit 会话会在关闭自动回滚
	    session.commit();
		session.close(); 
	}
	
	@Test
	public  void test9() throws IOException {
		DmProductMapper mapper=session.getMapper(DmProductMapper.class);
		DmProduct dp=new DmProduct();
		//只修改一个字段（market_price）值
		dp.setId(2);
		dp.setMarketPrice(436d);
		mapper.update(dp);
		//从数据库查出该记录 验证结果
		DmProduct dbdp=mapper.selectById(2);
		Assert.assertEquals(436d, dbdp.getMarketPrice());
		Assert.assertEquals(172d, dbdp.getShopPrice());
		Assert.assertEquals("女装立领长袖拼接PU皮毛呢外套", dbdp.getPname());
		//不commit 会话会在关闭自动回滚
	    session.commit();
		session.close(); 
	}
	
	@Test
	public  void test10() throws IOException {
		DmOrdersMapper  dosm=session.getMapper(DmOrdersMapper.class);
		DmOrderitemMapper doim=session.getMapper(DmOrderitemMapper.class);
		//生成订单业务
		//定义购买的2个订单明细
		DmOrderitem doi1=new DmOrderitem();
		doi1.setPid(1);
		doi1.setCount(1);
		doi1.setTotal(100d);
		DmOrderitem doi2=new DmOrderitem();
		doi2.setPid(2);
		doi2.setCount(1);
		doi2.setTotal(200d);
	    //定义订单主表记录
		DmOrders dos=new DmOrders();
		dos.setTotal(300d);
		dos.setAid(1);
		dos.setState(1);
		dos.setUid(1);
		
		try {
			dosm.insert(dos);
			/**
			 * 在添加订单明细记录是 必须获取到订单主表主键值id
			 * 二阶段项目  在此处进行了一次查询 select max(id) from dm_orders
			 *     严重问题： 多线程方式下会产生并发问题
			 *  Mybatis  可以实现在insert的同事获取到数据生成的id  不需要select
			 * */
			doi1.setOid(dos.getId());
			doi2.setOid(dos.getId());
			//写订单明细
			doim.insert(doi1);
			doim.insert(doi2);
			//提交事物
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.rollback();
		}finally {
			//不commit 会话会在关闭自动回滚
			session.close(); 	
		}
	}
}
