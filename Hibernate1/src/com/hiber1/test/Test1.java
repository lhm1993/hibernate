package com.hiber1.test;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.hiber1.domain.UserBean;
import com.hiber1.util.MySessionFactory;

public class Test1 {
	
	public static void main(String []args){
		
		// TODO Auto-generated method stub
		//使用openSession()打开一个,注意和getCurrentSession()区别(是否需要手动关闭)
		//开始一个新的session，但是这个session如果没有和当前线程绑定的话，
		//	使用getCurrentSession()的时候（这里的getCurrentSession()方法已经在MySessionFactory中重写过了），还是重新打开一个session
		 Session session=MySessionFactory.openSession();
		 //绑定了当前线程以后，就不用在hibernate.cfg.xml中设置thread从而使用getCurrentSession()方法
		 Session s1=MySessionFactory.getCurretSession();
		 Session s2=MySessionFactory.getCurretSession();
		 System.out.println(session.hashCode()+" "+s2.hashCode());
		 
		 Transaction ta=null;
		 //事务回滚 roll-back
		 try {
			//开始事务
			ta=session.beginTransaction();
			//进行会话
			UserBean user=(UserBean)session.load(UserBean.class, 1);
			user.setHiredate(new Date());
			
			//提交事务 
			ta.commit();
		} catch (Exception e) {
			// TODO: handle exception
			//如果发生异常，就回滚
			e.printStackTrace();
			if(ta!=null){
				ta.rollback();
			}
		}finally{
			//使用openSession()打开的会话需要手动关闭，如果session是不为空，并且有会话进行，就将会话关闭
			if(session!=null&&session.isOpen()){
				session.close();
			}
		}
		
		
	}
}
