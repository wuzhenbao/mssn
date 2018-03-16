package com.wuzhenbao.it.core.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;
import com.wuzhenbao.it.core.exception.MssnApplicationException;
import com.wuzhenbao.it.core.service.IMongoDBService;
import com.wuzhenbao.it.core.vo.UserVO;

//@Component("mongoDBService")
public class MongoDBService implements IMongoDBService {
	private static final Log log = LogFactory.getLog(MongoDBService.class);
	
	//@Autowired
    protected MongoTemplate mongoTemplate;
	
	public MongoDBService() {
		// 简单直接的连接数据库，默认为本机地址localhost，端口号27017

		// 或者像这样指定连接地址和端口号
		MongoClient mongoClient = new MongoClient("192.168.72.149", 27017);
		// 或者像这样连接到一个副本集，需要提供一个列表
		/*
		 * MongoClient mongoClient = new MongoClient( Arrays.asList(new
		 * ServerAddress("localhost", 27017), new ServerAddress("localhost",
		 * 27018), new ServerAddress("localhost", 27019)));
		 */

		// 或者使用连接字符串
		/*
		 * MongoClientURI connectionString = new MongoClientURI(
		 * "mongodb://localhost:27017,localhost:27018,localhost:27019");
		 * MongoClient mongoClient = new MongoClient(connectionString);
		 */
		// 获取到数据库对象mydb，如果不存在则自动创建
		MongoDatabase database = mongoClient.getDatabase("wzbdb");
		log.info("mongodb=" + database);
		// database.
	}
	
	public String testMongoDB() throws MssnApplicationException {
		System.out.println("testMongoDB=======================");
		
		return "1";
	}

	public String addUser(UserVO insetuSER) throws MssnApplicationException {
		Gson gson = new Gson();
		DBCollection user=mongoTemplate.getCollection("userdov");
		DBObject dbObject = (DBObject) JSON.parse(gson.toJson(insetuSER));
		user.insert(dbObject);
		return "1";
	}

	public String findUser() throws MssnApplicationException {
		return null;
	}



}
