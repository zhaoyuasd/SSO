package com.laozhao.loginServer.webDistrict;

// 网站信任区
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
}
