package com.furlenco.commons.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Adminusertest")
public class AdminUser {

	private Integer adminId;
	private String userName;
	private String login;
	private String password;
	
	@Id
	@Column(name = "adminid", nullable = false)
	public Integer getId() {
		return adminId;
	}
	public void setId(Integer adminId) {
		this.adminId = adminId;
	}
	
	@Column(name = "username", nullable = false)
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column(name = "login", nullable = false)
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	@Column(name = "password", nullable = false)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "AdminUser [id=" + adminId + ", userName=" + userName + ", login="
				+ login + ", password=" + password + "]";
	}
}
