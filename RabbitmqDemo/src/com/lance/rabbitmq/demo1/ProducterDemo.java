package com.lance.rabbitmq.demo1;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeoutException;

import org.apache.log4j.Logger;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ProducterDemo  extends EndPoint{
	
	private static Logger logger = Logger.getLogger(ProducterDemo.class);

	public ProducterDemo(String endPointName) throws IOException, TimeoutException {
		super(endPointName);
		// TODO Auto-generated constructor stub
	}


	/**
	 * publish a message to queue
	 * */
	public void sendMessage(String message) throws UnsupportedEncodingException, IOException
	{
		
		channel.basicPublish("", endPointName, null, message.getBytes("utf-8"));
		logger.debug("Productor endPointName:"+endPointName+"message:"+message);
		
	}


	
}
