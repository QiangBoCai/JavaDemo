package com.lance.jdbc.mongodb;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

/***********************
 * @author Lance	    
 * @version 1.0        
 * @created 2017-11-19
 * mongodb连接数据库    
 ***********************/
public class MongoDBJDBC {
	public static void main(String[] args)
	{
		//连接到mongodb 服务
		MongoClient  mongoClient = new MongoClient("localhost",27017);
		
		//连接到数据库
		
		MongoDatabase mongoDatabase = mongoClient.getDatabase("test");
		System.out.println("Connect to database lance ");
		
		//创建集合
		mongoDatabase.createCollection("col");
		System.out.println("Create collection Success");
		//获取集合
		MongoCollection<Document> collection = mongoDatabase.getCollection("col");
		//插入文档
		Document document = new Document("name","lance")
			.append("age",28);
		List<Document> documents = new ArrayList<Document>();
		documents.add(document);
		collection.insertMany(documents);
		
		//查询文档
		
		FindIterable<Document> findIterable = collection.find();
		MongoCursor<Document> mongoCursor = findIterable.iterator();
		
		while(mongoCursor.hasNext())
		{
			System.out.println(mongoCursor.next());
		}
		
		//更新文档
		//暂略
		
		
	}
}


