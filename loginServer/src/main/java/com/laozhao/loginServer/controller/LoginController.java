package com.laozhao.loginServer.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.laozhao.loginServer.service.LoginService;

@RestController
public class LoginController {
    
	private LoginService loginServcie ;
	 @PostMapping("login")
	 public void login(HttpServletRequest req, HttpServletResponse resp) throws Exception
	 {
		 String username=req.getParameter("username");
		 String password=req.getParameter("password");
		 String targetUrl=req.getParameter("url");
		 String token=loginServcie.processLogin(req,resp);
		 resp.sendRedirect(targetUrl+"?token="+token);
		 return ;
	 }
	 
	 @PostMapping("auth")
	 public Object auth(HttpServletRequest req, HttpServletResponse resp)
	 {
		 String token= req.getParameter("token");
		 Object result=loginServcie.processAuth(token);
		 return result;
	 }
	 
	 @PostMapping("logout")
	 public void logout(HttpServletRequest req, HttpServletResponse resp)
	 {
		 
	 }
	
}
