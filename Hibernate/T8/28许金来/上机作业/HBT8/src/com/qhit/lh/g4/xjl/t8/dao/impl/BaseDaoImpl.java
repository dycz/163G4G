/**
 * 
 */
package com.qhit.lh.g4.xjl.t8.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.qhit.lh.g4.xjl.t8.bean.Emp;
import com.qhit.lh.g4.xjl.t8.bean.UserInfo;
import com.qhit.lh.g4.xjl.t8.dao.BaseDao;
import com.qhit.lh.g4.xjl.t8.utils.HibernateSessionFactory;



/**
 * @author 许金来
 *2017-12-19下午4:55:32
 * TODO
 */
public class BaseDaoImpl implements BaseDao {




	@Override
	public void add(Object obj) {
		// TODO Auto-generated method stub
		//2,先获取session对象
		Session session = HibernateSessionFactory.getSession();
		//3,Hibernate是以事务驱动的ORM框架，需要开启事务
		Transaction ts = session.beginTransaction();
		//4,操作对象-->完成数据的CRUD
		session.save(obj);
		//5,提交事务
		ts.commit();
		//6,关闭session，释放资源
		HibernateSessionFactory.closeSession();
	}


	@Override
	public void delete(Object obj) {
		// TODO Auto-generated method stub
		//2,先获取session对象
		Session session = HibernateSessionFactory.getSession();
		//3,Hibernate是以事务驱动的ORM框架，需要开启事务
		Transaction ts = session.beginTransaction();
		//4,操作对象-->完成数据的CRUD
		session.delete(obj);
		//5,提交事务
		ts.commit();
		//6,关闭session，释放资源
		HibernateSessionFactory.closeSession();
	}


	@Override
	public void update(Object obj) {
		// TODO Auto-generated method stub
		//2,先获取session对象
		Session session = HibernateSessionFactory.getSession();
		//3,Hibernate是以事务驱动的ORM框架，需要开启事务
		Transaction ts = session.beginTransaction();
		//4,操作对象-->完成数据的CRUD
		session.update(obj);
		//5,提交事务
		ts.commit();
		//6,关闭session，释放资源
		HibernateSessionFactory.closeSession();
	}


	@Override
	public List<Object> queryAll(String tableName) {
		// TODO Auto-generated method stub
		//2,先获取session对象
		Session session = HibernateSessionFactory.getSession();
		//3,Hibernate是以事务驱动的ORM框架，需要开启事务
		Transaction ts = session.beginTransaction();
		Query query = session.createQuery(tableName);
		//4,查询
		List<Object> list = query.list();
		//5,提交事务
		ts.commit();
		//6,关闭session，释放资源
		HibernateSessionFactory.closeSession();
		return list;

	}


	/* (non-Javadoc)
	 * @see com.qhit.lh.g4.xjl.t4.dao.BaseDao#getObjectById(java.lang.Class, int)
	 */
	@Override
	public Object getObjectById(Class clazz, int id) {
		//2,先获取session对象
		Session session = HibernateSessionFactory.getSession();
		//3,Hibernate是以事务驱动的ORM框架，需要开启事务
		Transaction ts = session.beginTransaction();
		//4,操作对象-->完成数据的CRUD
		 Object obj = session.get(clazz, id);
		//5,提交事务
		ts.commit();
		//6,关闭session，释放资源
		HibernateSessionFactory.closeSession();
		return obj;
	}


	/* (non-Javadoc)
	 * @see com.qhit.lh.g4.xjl.t5.dao.BaseDao#getEmpByName(java.lang.String)
	 */
	@Override
	public List<Emp> getEmpByName(String name) {
		Session session = HibernateSessionFactory.getSession();
		
		String hql = "select emp from Emp emp where emp.empName like ?";
		Query query = session.createQuery(hql);
		query.setString(0, "张%");
		return query.list();
	}


	/* (non-Javadoc)
	 * @see com.qhit.lh.g4.xjl.t5.dao.BaseDao#login(com.qhit.lh.g4.xjl.t5.bean.UserInfo)
	 */
	@Override
	public UserInfo login(UserInfo user) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		
		String hql = "select user from UserInfo user where user.userName = :name and passeord";
		Query query = session.createQuery(hql);
		query.setString("name", user.getUserName());
		query.setString("password", user.getPasword());
		
		List<UserInfo> result = query.list();
 		return result.get(0);
	}

}
