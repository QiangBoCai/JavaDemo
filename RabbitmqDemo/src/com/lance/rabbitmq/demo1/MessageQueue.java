package com.lance.rabbitmq.demo1;

import java.util.LinkedList;

import org.apache.log4j.Logger;
/**
 * 
 * MessageQueue is a LinkedList and a queue
 *
 */
public class MessageQueue <T>{
	private static Logger logger = Logger.getLogger(MessageQueue.class);
	private LinkedList<T> queue = null;
	
	
	public MessageQueue() {
		queue = new LinkedList<T>();
	}
	

	
	public MessageQueue(LinkedList<T> queue) {
		this.queue=queue;
	}
	
	public MessageQueue(MessageQueue<T> msgQueue) {
		this.queue=msgQueue.clone();
	}
	
	
	public LinkedList<T> getQueue() {
		return queue;
	}
	public void setQueue(LinkedList<T> queue) {
		this.queue = queue;
	}
	
	
	public synchronized T poll(){//queue
		return queue.poll();
	}

	
	public synchronized boolean offer(T msg){
		return queue.offer(msg);
	}
	
	public boolean isEmpty(){
		return queue.isEmpty();
	}
	
	@SuppressWarnings("unchecked")
	public LinkedList<T> clone(){
		return (LinkedList<T>) queue.clone();
	}
	
	public synchronized void clear(){
		queue.clear();
	}
	
	
	
	@Override
	public String toString() {
		return "MessageQueue [queue=" + queue + "]";
	}



	public static void main(String[] args)
	{
		MessageQueue<String> queue = new MessageQueue<String>();
		logger.debug("queue.isEmpty():"+queue.isEmpty());
		
		queue.offer("Java");//add(e)
		//queue.clear();
		logger.debug(queue);
		queue.offer("Android");
		logger.debug(queue);
		queue.offer("JS");
		logger.debug(queue);
		queue.poll();//removeFirst(e)
		logger.debug(queue);
		queue.poll();
		logger.debug(queue);
		queue.poll();
		logger.debug(queue);
		queue.poll();
		logger.debug(queue);
		
	}
	
}
