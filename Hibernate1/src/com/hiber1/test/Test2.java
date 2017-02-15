package com.hiber1.test;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hiber1.domain.UserBean;
import com.hiber1.util.MySessionFactory;

public class Test2 {
	
	public void test(Session session,UserBean user,Transaction ta){
		
		 //����ع� roll-back
		 try {
			//��ʼ����
			
			//���лỰ
			user=(UserBean)session.load(UserBean.class, 88);
			user.setHiredate(new Date());
			//�ύ���� 
			ta.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			//��������쳣���ͻع�
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
