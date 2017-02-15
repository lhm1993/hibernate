/**
 * function:测试持久层是否已经联通
 * 回滚技术需要制作成模板，事物必须要回滚，参考学习笔记  hibernate学习笔记.doc
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
		//使用openSession()打开一个,注意和getCurrentSession()区别(是否需要手动关闭)
		 Session session=MySessionFactory.getSessionFactory().openSession();
		 Transaction ta=null;
		 //事务回滚 roll-back
		 try {
			//开始事务
			ta=session.beginTransaction();
			//进行会话
			UserBean user=(UserBean)session.load(UserBean.class, 88);
			user.setHiredate(new Date());
			//提交事务 
			ta.commit();
		} catch (Exception e) {
			// TODO: handle exception
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

	public static void updateUser() {
		//修改数据（删除一个数据——删除一个数据和修改一个数据首先都要将该数据load()出来才能进行处理）
		//1. 创建一个会话工厂
		SessionFactory sessionFactory=MySessionFactory.getSessionFactory();

		//2. 创建一个会话
		Session session=sessionFactory.openSession();
		//3. 创建一个事物
		Transaction ta=session.beginTransaction();
		//4. 进行会话
		//将要修改的数据从数据库表中读取出来
		//session.load(Object o1,Serializable arg1); o1 表示当前要读的类的 class ，这一点利用的是java的反射机制，arg1是主键对应的在数据库中的值
		UserBean user=(UserBean)session.load(UserBean.class, 1);
		user.setUname("小黄");//这一条命令会被hibernate 翻译成 对应的   update 命令
		
		//删除一个数据——删除一个数据和修改一个数据首先都要将该数据load()出来才能进行处理
		UserBean user_d=(UserBean) session.load(UserBean.class, 8);
		session.delete(user_d);
		//5. 提交事物
		ta.commit();
		//6. 结束会话
		session.close();
	}

	public static void saveUser() {
		//添加一条数据
		//1.初始化 Configuration 用来读取 hibernate.cfg.xml 总的配置文件
		Configuration configeation=new Configuration().configure();
		
		//2. 创建sessionFactory，这是一个会话工程，重量级会话对象，实际使用中很耗费资源，所以一般是单态的
		SessionFactory sessionFactory=configeation.buildSessionFactory();
	
		//3. 创建一个会话
		Session session=sessionFactory.openSession();
		
		//4. hibernate 要求程序员在进行修改删除添加的时候使用 事务提交 Transaction
		Transaction transaction=session.beginTransaction();
		
		//添加一个用户
		UserBean user=new UserBean();
		user.setId(100);
		//主键 id 配置的 generator是increment  
		//所以不能自己设置id号，如果想要自己设置id号，需要将generator设置为assigned
		user.setUname("小明");
		user.setUsex("male");
		user.setUemail("xioaming@163.com");
		user.setHiredate(new Date());
		
		//5. 使用session——保存一条记录
		session.save(user); //==>save()对应成  insert 语句，由hibernate封装成当前所要操作的数据库
		
		//6. 使用事务提交当前会话
		transaction.commit();
		
		//7. 关闭当前会话
		session.close();
	}
	
	

}
