package com.laozhao.loginServer.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.laozhao.loginServer.service.LoginService;

@RestController
public class LoginController {
    
	 @PostMapping("login")
	 public void login(HttpServletRequest req, HttpServletResponse resp)
	 {
		 
	 }
	 
	 @PostMapping("auth")
	 public void auth(HttpServletRequest req, HttpServletResponse resp)
	 {
		 
	 }
	 
	 @PostMapping("logout")
	 public void logout(HttpServletRequest req, HttpServletResponse resp)
	 {
		 
	 }
	
}
