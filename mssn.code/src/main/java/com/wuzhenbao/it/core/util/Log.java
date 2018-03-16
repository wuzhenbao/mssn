package com.wuzhenbao.it.core.util;

import org.apache.log4j.Logger;


public class Log {
	
	static Logger log1 = Logger.getLogger("operateLog");
	static Logger log2 = Logger.getLogger("systemLog");
	public static void doLog(String s){
		log1.info(s);
		log2.info(s);
	}
	public static void doLog(String s,Throwable e){
		log1.info(s, e);
		log2.info(s, e);
	}
	
	public static void main(String[] args) {
		for(int i = 1 ;i < 100000;i++){
			boolean flag  = false;
			for(int j = 2;j < i;j++){
				if(i % j == 0 && !flag){
					flag = true;
				}
			}
			if(!flag){
				System.out.println(":=="+i);
			}
		}
	}
}
