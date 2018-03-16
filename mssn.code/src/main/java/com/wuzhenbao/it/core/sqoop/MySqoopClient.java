package com.wuzhenbao.it.core.sqoop;

import org.apache.sqoop.client.SqoopClient;

public class MySqoopClient {
	static SqoopClient client;
	public static SqoopClient getClient(String url,boolean rebuild){
		if(rebuild){
			client = new SqoopClient(url);
		}else{
			if(null == client){
				client = new SqoopClient(url);
			}
		}
		return client;
	}
	public static SqoopClient getClient(String url){
		return getClient(url,false);
	}
}
