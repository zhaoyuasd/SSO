package com.laozhao.loginServer.webDistrict;

// 网站信任区
/*
 *  请求是否授权 
 *  请求是否有信任区
 *  获取信任区 的key
 */
public class ReliableWebDistrict {
	//获取请求对应的 信任区的key
   public static String getWebDistrictKey(String url)
   {
	  return url;
   }
	
   //判断该请求是否有对应的信任区
   public static boolean existWebDistrict(String url)
   {
	 return false;
   }
   
   //判断该请求是否在系统中进行了注册 是否允许使用该系统
   public static  boolean  isPermissonAccess(String url)
   {
	  return false;
   }
}
