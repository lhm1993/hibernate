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
		//ʹ��openSession()��һ��,ע���getCurrentSession()����(�Ƿ���Ҫ�ֶ��ر�)
		//��ʼһ���µ�session���������session���û�к͵�ǰ�̰߳󶨵Ļ���
		//	ʹ��getCurrentSession()��ʱ�������getCurrentSession()�����Ѿ���MySessionFactory����д���ˣ����������´�һ��session
		 Session session=MySessionFactory.openSession();
		 //���˵�ǰ�߳��Ժ󣬾Ͳ�����hibernate.cfg.xml������thread�Ӷ�ʹ��getCurrentSession()����
		 Session s1=MySessionFactory.getCurretSession();
		 Session s2=MySessionFactory.getCurretSession();
		 System.out.println(session.hashCode()+" "+s2.hashCode());
		 
		 Transaction ta=null;
		 //����ع� roll-back
		 try {
			//��ʼ����
			ta=session.beginTransaction();
			//���лỰ
			UserBean user=(UserBean)session.load(UserBean.class, 1);
			user.setHiredate(new Date());
			
			//�ύ���� 
			ta.commit();
		} catch (Exception e) {
			// TODO: handle exception
			//��������쳣���ͻع�
			e.printStackTrace();
			if(ta!=null){
				ta.rollback();
			}
		}finally{
			//ʹ��openSession()�򿪵ĻỰ��Ҫ�ֶ��رգ����session�ǲ�Ϊ�գ������лỰ���У��ͽ��Ự�ر�
			if(session!=null&&session.isOpen()){
				session.close();
			}
		}
		
		
	}
}
