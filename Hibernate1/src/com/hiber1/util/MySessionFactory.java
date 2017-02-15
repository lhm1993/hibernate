package com.hiber1.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

//该类是final 不能被继承，保证单态
public final class MySessionFactory {

	private static SessionFactory sessionFactory=null;
	private static ThreadLocal<Session> threadLocal=new ThreadLocal<Session>();
	
	private MySessionFactory(){
		
	}
	//static只会被执行一次，可以保证单态问题，并且在调用该类的一个方法的时候就被执行
	static{
		//SessionFactory 是重量级对象，应该保证其是单态的存在
		sessionFactory=new Configuration().configure().buildSessionFactory();
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	//重写openSession()方法
	public static Session openSession(){
		return sessionFactory.openSession();
	}
	
	//重写getCurrentSession()方法
	public static Session getCurretSession(){
		Session session=threadLocal.get();
		if(session==null){
			session=sessionFactory.openSession();
			threadLocal.set(session);
		}
		return session;
	}
}
