package com.lance.thread;

public class RunnableThreadDemo implements Runnable{

	@Override
	public void run() {
		
		System.out.println("ThreadName:"+Thread.currentThread().getName());
		
	}
	
	public static void main(String[] args)
	{
		RunnableThreadDemo target = new RunnableThreadDemo();
		Thread thread1 = new Thread(target);
		thread1.setName("thread1");
		
		Thread thread2 = new Thread(target);
		thread2.setName("thread2");
		
		thread1.start();
		thread2.start();
	
	}

}
