package com.lance.rabbitmq.demo1;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public abstract class EndPoint {
	protected Channel channel;
	protected Connection connection ;
	protected String endPointName ;
	public EndPoint(String endPointName) throws IOException, TimeoutException {
		super();
		this.endPointName = endPointName;
		init(endPointName);
	}
	
	/**
	 * init chanel&connection
	 * @throws IOException 
	 * */
	
	public void  init (String endPointName) throws IOException, TimeoutException 
	{
		/*
		 * create a connection to rabbitmq
		 * */
		//1. new connection factory
		
		ConnectionFactory factory = new ConnectionFactory();
		
		//2. set rabbitmq ip,port,username,password
		
		factory.setHost("127.0.0.1");
		factory.setPort(new Integer("5672"));
		factory.setUsername("lance");
		factory.setPassword("lance");
		//3. create a  connection& channel
		connection = factory.newConnection();
		channel = connection.createChannel();  
		//4。declare a queue 
		//  If queue does not exist,it will be created on the server.
		channel.queueDeclare(endPointName, true, false, false, null);//true表示消息持久化
	}
	
	

	
	/**
	 * close chanel&connection
	 * */
	public void close() throws IOException, TimeoutException 
	{
		
		this.channel.close();
		this.connection.close();
	}
}
