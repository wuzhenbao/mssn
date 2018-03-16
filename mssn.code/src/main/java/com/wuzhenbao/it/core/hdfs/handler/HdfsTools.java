package com.wuzhenbao.it.core.hdfs.handler;

import java.util.StringTokenizer;

public class HdfsTools {
	public static String getStrByIndex(int index,String text) {
		String str = null;
		StringTokenizer token = new StringTokenizer(text);
		int i = 0;
		while(token.hasMoreTokens()) {
			if(index == i) {
				str = token.nextToken();
				break;
			}else {
				token.nextToken();
			}
			i++;
		}
		return str;
	}
}	
