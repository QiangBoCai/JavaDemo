package com.lance.rabbitmq.demo1;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.apache.log4j.Logger;

public class RabbitmqDemo1Test {
	private static Logger logger = Logger.getLogger(RabbitmqDemo1Test.class);
	public final static String QUEUE_NAME = "first queue";
	public static void main(String[] args) throws IOException, TimeoutException {
		ConsumerDemo consumer = new ConsumerDemo(QUEUE_NAME);
		Thread consumerThread = new Thread(consumer);
		consumerThread.start();
		ConsumerDemo consumer2 = new ConsumerDemo(QUEUE_NAME);
		Thread consumerThread2 = new Thread(consumer2);
		consumerThread2.start();
		
		ProducterDemo productor = new ProducterDemo(QUEUE_NAME);
		productor.sendMessage("helloworld");
		productor.sendMessage("helloworld3");
		
	}

}
