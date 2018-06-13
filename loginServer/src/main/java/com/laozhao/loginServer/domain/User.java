package com.laozhao.loginServer.domain;

public class User {
  private String  username;
  private String  password;
  private String  id;
  private String  loginIp;
  private String  loginBrower;
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getLoginIp() {
	return loginIp;
}
public void setLoginIp(String loginIp) {
	this.loginIp = loginIp;
}
public String getLoginBrower() {
	return loginBrower;
}
public void setLoginBrower(String loginBrower) {
	this.loginBrower = loginBrower;
}
  
}
