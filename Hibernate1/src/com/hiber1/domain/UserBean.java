/**
 * function:用来对应数据库中的表
 */
package com.hiber1.domain;

import java.io.Serializable;
import java.util.Date;

public class UserBean implements Serializable {
	/**
	 * 给一个默认的序列号，可以实现当前对象和数据库中的一条数据唯一标识，也可以在网络和文件中传输 
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
