package com.laozhao.loginServer.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.laozhao.loginServer.domain.User;
import com.laozhao.loginServer.redis.util.RedisService;

public class LoginService {
   
	@Autowired
	private RedisService  redisService;
	
	@Autowired
	private UserService userService;
	
	public String processLogin(HttpServletRequest req, HttpServletResponse resp)
	{
		 String username=req.getParameter("username");
		 String password=req.getParameter("password");
		 User   user=checkUserInfo(username,password);  //验证用户
		 String token=generateSession(resp);   //建立会话
		 redisService.setUser(token, user);    //写入缓存
		 return token;
	}
	
	private String generateSession(HttpServletResponse resp) {
		// TODO Auto-generated method stub
		return null;
	}

	private User checkUserInfo(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	public void processLogout()
	{
		
	}
	
	public Object processAuth(String token)
	{
		return token;
		
	}
	
}
