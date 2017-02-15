package com.hiber1.test;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hiber1.domain.UserBean;
import com.hiber1.util.MySessionFactory;

public class Test2 {
	
	public void test(Session session,UserBean user,Transaction ta){
		
		 //事务回滚 roll-back
		 try {
			//开始事务
			
			//进行会话
			user=(UserBean)session.load(UserBean.class, 88);
			user.setHiredate(new Date());
			//提交事务 
			ta.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			//如果发生异常，就回滚
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
