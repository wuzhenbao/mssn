package com.wuzhenbao.it.core.redis;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class RedisConnectionUtil {
	   public static void main(String[] args) {
		      //连接本地的 Redis 服务
		      Jedis jedis = new Jedis("192.168.72.146");
		      System.out.println("Connection to server sucessfully");
		      jedis.set("user.password","123456");
		      //查看服务是否运行
		      System.out.println("Server is running: "+jedis.ping());
		      System.out.println(jedis.get("user.name"));
		      /*jedis.lpush("user.email", "wuzhenbao@huawei.com");
		      jedis.lpush("user.email", "wzb163@163.com");
		      jedis.lpush("user.email", "wzb126@126.com");
		      jedis.lpush("user.email", "wuzhenbao@qq.com");*/
		      List<String> mailList = jedis.lrange("user.email", 0, 3);
		      for(String s :mailList){
		    	  System.out.println(s);
		    	  
		      }
		      Set<String> list = jedis.keys("*");
		      Iterator<String> keyInt = list.iterator();
		     while(keyInt.hasNext()){
		    	 System.out.println(keyInt.next());
		     }
		 }
}
