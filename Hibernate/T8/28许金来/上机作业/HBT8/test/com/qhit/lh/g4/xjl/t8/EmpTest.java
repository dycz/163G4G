/**
 * 
 */
package com.qhit.lh.g4.xjl.t8;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import com.qhit.lh.g4.xjl.t8.bean.Dept;
import com.qhit.lh.g4.xjl.t8.bean.Emp;
import com.qhit.lh.g4.xjl.t8.bean.UserInfo;
import com.qhit.lh.g4.xjl.t8.service.BaseService;
import com.qhit.lh.g4.xjl.t8.service.impl.BaseServiceImpl;
import com.qhit.lh.g4.xjl.t8.utils.HibernateSessionFactory;

/**
 * @author 许金来
 *2017年12月19日下午4:44:57
 * TODO
 */
public class EmpTest {

	private BaseService baseService = new BaseServiceImpl();
	@Test
	public void add() {
		//来了一个新员工
		Emp emp = new Emp();
		emp.setEmpName("张晓明");
		emp.setEmpSex("M");
		emp.setBirthday("2017-01-28");
		//分配公司账户
		UserInfo userInfo = new UserInfo();
		userInfo.setUserName("zxm");
		userInfo.setPasword("333333");
		//关联员工和账户
		emp.setUserInfo(userInfo);
		userInfo.setEmp(emp);
		//分配到部门
		Dept dept = (Dept) baseService.getObjectById(Dept.class, 1);
		//员工部门的多对一
		emp.setDept(dept);
		baseService.add(emp);
	}

	@Test
	public void delete() {
		Emp emp = (Emp) baseService.getObjectById(Emp.class, 1);
		baseService.delete(emp);
	}

	@Test
	public void update() {
		//先找到人
		Emp emp1 = (Emp) baseService.getObjectById(Emp.class, 1);
		Emp emp2 = (Emp) baseService.getObjectById(Emp.class,2);
		//改的是员工当前所属的部门
		Dept dept = (Dept) baseService.getObjectById(Dept.class, 2);
		//分配到部门
		emp1.setDept(dept);
		emp2.setDept(dept);
		baseService.update(emp1);
		baseService.update(emp2);
	}

	@Test
	public void query() {
		List<Object> list = baseService.queryAll("tableName");
		for (Object object : list) {
			Emp emp = (Emp) object;
			System.out.println(toString());
		}
	}
	
	@Test
	public void getEmpByName(){
		//获取session对象
		Session session = HibernateSessionFactory.getSession();
		
		//获取条件查询器
		Criteria criteria = session.createCriteria(Emp.class)
				.add(Restrictions.like("empName", "张%"));
		
		//执行查询
		List<Emp> list = criteria.list();
		for (Emp emp : list) {
			System.out.println(emp.getEmpName());
		}
	}
	@Test
	public void getEmpByDeptName(){
		//获取session对象
		Session session = HibernateSessionFactory.getSession();
		//获取条件查询器
		Criteria criteria = session.createCriteria(Emp.class)
				.setFetchMode("dept",FetchMode.JOIN)
				.createAlias("dept", "d")
				.add(Restrictions.eq("d.deptName", "外交部"));
		//执行查询
		List<Emp> list = criteria.list();
		
		for (Emp emp : list) {
			System.out.println(emp.getEid()+""+emp.getEmpName());
		}
	}

}
