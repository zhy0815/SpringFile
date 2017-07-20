package com.zhy.model;

import java.io.Serializable;

public class Admin implements Serializable {
	private String loginname;
	private String password;
	private String username;
	public Admin() {
		super();
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
