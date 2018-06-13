package com.laozhao.loginServer.redis.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.laozhao.loginServer.domain.User;
import com.laozhao.loginServer.util.Constants;

@Component
public class RedisService {

	@Autowired
	private RedisTemplate<String, User>  redisTemplate;
	
	
	public  void   setUser(String key,User user)
	{
		redisTemplate.opsForHash().put(Constants.userKey,key,user);
		return;
	}
	
	public User getUser(String key,User user)
	{
		return (User) redisTemplate.opsForHash().get(Constants.userKey,key);
	}
}
