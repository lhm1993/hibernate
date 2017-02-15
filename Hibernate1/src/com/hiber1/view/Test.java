/**
 * function:���Գ־ò��Ƿ��Ѿ���ͨ
 * �ع�������Ҫ������ģ�壬�������Ҫ�ع����ο�ѧϰ�ʼ�  hibernateѧϰ�ʼ�.doc
 */
package com.hiber1.view;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.*;

import com.hiber1.domain.UserBean;
import com.hiber1.util.MySessionFactory;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//ʹ��openSession()��һ��,ע���getCurrentSession()����(�Ƿ���Ҫ�ֶ��ر�)
		 Session session=MySessionFactory.getSessionFactory().openSession();
		 Transaction ta=null;
		 //����ع� roll-back
		 try {
			//��ʼ����
			ta=session.beginTransaction();
			//���лỰ
			UserBean user=(UserBean)session.load(UserBean.class, 88);
			user.setHiredate(new Date());
			//�ύ���� 
			ta.commit();
		} catch (Exception e) {
			// TODO: handle exception
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

	public static void updateUser() {
		//�޸����ݣ�ɾ��һ�����ݡ���ɾ��һ�����ݺ��޸�һ���������ȶ�Ҫ��������load()�������ܽ��д���
		//1. ����һ���Ự����
		SessionFactory sessionFactory=MySessionFactory.getSessionFactory();

		//2. ����һ���Ự
		Session session=sessionFactory.openSession();
		//3. ����һ������
		Transaction ta=session.beginTransaction();
		//4. ���лỰ
		//��Ҫ�޸ĵ����ݴ����ݿ���ж�ȡ����
		//session.load(Object o1,Serializable arg1); o1 ��ʾ��ǰҪ������� class ����һ�����õ���java�ķ�����ƣ�arg1��������Ӧ�������ݿ��е�ֵ
		UserBean user=(UserBean)session.load(UserBean.class, 1);
		user.setUname("С��");//��һ������ᱻhibernate ����� ��Ӧ��   update ����
		
		//ɾ��һ�����ݡ���ɾ��һ�����ݺ��޸�һ���������ȶ�Ҫ��������load()�������ܽ��д���
		UserBean user_d=(UserBean) session.load(UserBean.class, 8);
		session.delete(user_d);
		//5. �ύ����
		ta.commit();
		//6. �����Ự
		session.close();
	}

	public static void saveUser() {
		//���һ������
		//1.��ʼ�� Configuration ������ȡ hibernate.cfg.xml �ܵ������ļ�
		Configuration configeation=new Configuration().configure();
		
		//2. ����sessionFactory������һ���Ự���̣��������Ự����ʵ��ʹ���кܺķ���Դ������һ���ǵ�̬��
		SessionFactory sessionFactory=configeation.buildSessionFactory();
	
		//3. ����һ���Ự
		Session session=sessionFactory.openSession();
		
		//4. hibernate Ҫ�����Ա�ڽ����޸�ɾ����ӵ�ʱ��ʹ�� �����ύ Transaction
		Transaction transaction=session.beginTransaction();
		
		//���һ���û�
		UserBean user=new UserBean();
		user.setId(100);
		//���� id ���õ� generator��increment  
		//���Բ����Լ�����id�ţ������Ҫ�Լ�����id�ţ���Ҫ��generator����Ϊassigned
		user.setUname("С��");
		user.setUsex("male");
		user.setUemail("xioaming@163.com");
		user.setHiredate(new Date());
		
		//5. ʹ��session��������һ����¼
		session.save(user); //==>save()��Ӧ��  insert ��䣬��hibernate��װ�ɵ�ǰ��Ҫ���������ݿ�
		
		//6. ʹ�������ύ��ǰ�Ự
		transaction.commit();
		
		//7. �رյ�ǰ�Ự
		session.close();
	}
	
	

}
