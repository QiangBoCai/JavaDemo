
package com.lance.thread;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableThreadDemo  implements Callable<Integer>{

	@Override
	public Integer call() throws Exception {
		
		int random = new Random().nextInt(100);
		System.out.println("ThreadName:"+Thread.currentThread().getName()+";random="+random);
		return random ;
	}

	public static void main(String[] args)
	{
		CallableThreadDemo call = new CallableThreadDemo();
		FutureTask<Integer> future = new FutureTask<Integer>(call);
		Thread thread1 = new Thread(future);
		thread1.setName("thread1");
		thread1.start();
		
		FutureTask<Integer> future2 = new FutureTask<Integer>(call);
		Thread thread2 = new Thread(future2);
		thread2.setName("thread2");
		thread2.start();
		
		try {
			System.out.println("future1Get:"+future.get()+";future2Get="+future2.get());
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
