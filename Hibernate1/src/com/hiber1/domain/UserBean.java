/**
 * function:������Ӧ���ݿ��еı�
 */
package com.hiber1.domain;

import java.io.Serializable;
import java.util.Date;

public class UserBean implements Serializable {
	/**
	 * ��һ��Ĭ�ϵ����кţ�����ʵ�ֵ�ǰ��������ݿ��е�һ������Ψһ��ʶ��Ҳ������������ļ��д��� 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String uname;
	private String usex;
	private String uemail;
	private Date hiredate;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}	
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUsex() {
		return usex;
	}
	public void setUsex(String usex) {
		this.usex = usex;
	}
	public String getUemail() {
		return uemail;
	}
	public void setUemail(String uemail) {
		this.uemail = uemail;
	}
	public Date getHiredate() {
		return hiredate;
	}
	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}
}
