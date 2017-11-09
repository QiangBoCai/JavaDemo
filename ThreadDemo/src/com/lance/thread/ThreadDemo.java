package com.lance.thread;

public class ThreadDemo  extends Thread{

	public ThreadDemo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ThreadDemo(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		super.run();

		System.out.println("threadName:"+this.getName()+"/"+Thread.currentThread().getName());
	}
	
	public static void main(String[] args)
	{
		ThreadDemo thread1 = new ThreadDemo();
		thread1.start();
		thread1.setName("thread1");
	
		new ThreadDemo("thread2").start();
	}
	
	

}
