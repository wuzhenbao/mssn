package com.wuzhenbao.it.core.mongo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bson.Document;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoUtil {
	public static void main(String[] args) {
		MongoUtil mu = new MongoUtil();
		mu.connection();
	}

	public void testConnection() {
		ServerAddress serverAddress = new ServerAddress("192.168.72.149",
				27017);
		List<ServerAddress> seeds = new ArrayList<ServerAddress>();
		seeds.add(serverAddress);
		MongoCredential credentials = MongoCredential
				.createScramSha1Credential("wuzhenbao1", "admin",
						"123456".toCharArray());
		List<MongoCredential> credentialsList = new ArrayList<MongoCredential>();
		credentialsList.add(credentials);
		MongoClient client = new MongoClient(seeds, credentialsList);
		MongoDatabase db = client.getDatabase("wzbdb");
		MongoCollection<Document> collect = db.getCollection("userdoc");
		System.out.println(collect);
		long count = collect.count();
		System.out.println("count="+count);
		//BasicDBObject ob = new BasicDBObject();
		//ob.put("name", "lisi");
		Document d = new Document();
		d.put("name", "lisi");
		collect.insertOne(d);
		client.close();
		// MongoIterable<Document> collections=db.listCollections();
		// MongoCollection<Document> collection =
		// db.getCollection("system.users"); List<Document> foundDocument =
		// collection.find().into( new ArrayList<Document>());
		// System.out.println(foundDocument);
	}

	public void connection() {
		try {
			// 连接到 mongodb 服务
			ServerAddress add = new ServerAddress("192.168.72.149", 27017);

			Mongo mongo = new Mongo("192.168.72.149", 27017);
			// mongo.
			// 根据mongodb数据库的名称获取mongodb对象 ,
			DB db = mongo.getDB("wzbdb");
			//db.addUser("wuzhenbao1", new char[] { 1, 2, 3, 4, 5, 6 });
			Set<String> collectionNames = db.getCollectionNames();
			// 打印出test中的集合
			for (String name : collectionNames) {
				System.out.println("collectionName===" + name);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
