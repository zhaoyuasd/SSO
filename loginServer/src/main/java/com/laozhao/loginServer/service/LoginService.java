package com.laozhao.loginServer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.laozhao.loginServer.domain.User;
import com.laozhao.loginServer.redis.util.RedisService;

public class LoginService {
   
	@Autowired
	private RedisService  redisService;
	
	@Autowired
	private UserService userService;
	
	public void processLogin()
	{
		
	}
	
	public void processLogout()
	{
		
	}
	
	public void processAuth()
	{
		
	}
	
}
