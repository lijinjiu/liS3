package com.yc.damai.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.yc.damai.dao.AddressDao;
import com.yc.damai.dao.ProductDao;
import com.yc.damai.po.DmAddress;
import com.yc.damai.po.DmProduct;
import com.yc.damai.po.Result;
import com.yc.damai.util.DBHelper;

@WebServlet("/address.do")
public class AddressServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private AddressDao adao=new AddressDao();
	/**
	 * 用户地址查询
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	protected void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IllegalAccessException, InvocationTargetException {
		/* String uid="2"; */
		HttpSession session=request.getSession(); 
		Map<String,Object> user=(Map<String, Object>) session.getAttribute("loginedUser");
		String id=String.valueOf(user.get("id")) ;
		System.out.println("-----"+id+"-------");
		String sql="select *from dm_address where uid=?";
		List<?> list=new DBHelper().query(sql,id);
		print(response,list);
	}
//	手机号码格式验证
	public static boolean IsMobilePhone(String InPut){
		String reg = "^1[0-9]{10}$";
		return Pattern.matches(reg, InPut);
	}
	protected void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IllegalAccessException, InvocationTargetException {
		DmAddress da=new DmAddress();
		BeanUtils.populate(da, request.getParameterMap());
//		地址验证，电话验证，姓名验证
		if(da.getAddr()==null || da.getAddr().trim().isEmpty()){
			print(response,new Result(0,"地址不能为空！"));
			return;
		}
		if(!(IsMobilePhone(da.getPhone()))){
			print(response,new Result(0,"请输入正确的联系方式"));
			return;
		}
		if(da.getName()==null || da.getName().trim().isEmpty()){
			print(response,new Result(0,"联系人不能为空！"));
			return;
		}
		System.out.println(da.getId()+"===============");
		if(da.getId()==0) {
			adao.insert(da);
			print(response,new Result(1,"地址添加成功！"));
		}else {
			adao.Addressedit(da);
			print(response,new Result(1,"地址修改成功！"));
		}
	}

}
