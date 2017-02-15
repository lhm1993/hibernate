package com.hiber1.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

//������final ���ܱ��̳У���֤��̬
public final class MySessionFactory {

	private static SessionFactory sessionFactory=null;
	private static ThreadLocal<Session> threadLocal=new ThreadLocal<Session>();
	
	private MySessionFactory(){
		
	}
	//staticֻ�ᱻִ��һ�Σ����Ա�֤��̬���⣬�����ڵ��ø����һ��������ʱ��ͱ�ִ��
	static{
		//SessionFactory ������������Ӧ�ñ�֤���ǵ�̬�Ĵ���
		sessionFactory=new Configuration().configure().buildSessionFactory();
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	//��дopenSession()����
	public static Session openSession(){
		return sessionFactory.openSession();
	}
	
	//��дgetCurrentSession()����
	public static Session getCurretSession(){
		Session session=threadLocal.get();
		if(session==null){
			session=sessionFactory.openSession();
			threadLocal.set(session);
		}
		return session;
	}
}
