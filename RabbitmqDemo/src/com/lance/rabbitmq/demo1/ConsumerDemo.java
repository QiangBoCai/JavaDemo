package com.lance.rabbitmq.demo1;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.apache.log4j.Logger;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;

public class ConsumerDemo  extends EndPoint implements Runnable, Consumer{

	private static Logger logger = Logger.getLogger(ConsumerDemo.class);
	private boolean isShutdownSignal = false;
	
	
	
	public boolean isShutdownSignal() {
		return isShutdownSignal;
	}

	public void setShutdownSignal(boolean isShutdownSignal) {
		this.isShutdownSignal = isShutdownSignal;
	}

	public ConsumerDemo(String endPointName) throws IOException, TimeoutException {
		super(endPointName);
		
	}

	@Override
	public void handleCancel(String consumerTag) throws IOException {
		logger.info( "Consumer,handleCancel " + consumerTag);
	}

	@Override
	public void handleCancelOk(String consumerTag) {
		logger.info( "Consumer,handleCancelOk " + consumerTag);
		
	}

	@Override
	public void handleConsumeOk(String consumerTag) {
		logger.info( "Consumer " + consumerTag + " registered");
	}

	@Override
	public void handleDelivery(String consumerTag, Envelope env,
			BasicProperties props, byte[] body) throws IOException {
		String msg = new String(body);
		channel.basicAck(env.getDeliveryTag(), false);//收到消息后发送应答
		int hashCode = this.hashCode();
		logger.debug("Consumer,handleDelivery msg:"+msg+"hashCode:"+hashCode);
	}

	@Override
	public void handleRecoverOk(String consumerTag) {
		logger.info( "Consumer,handleRecoverOk " + consumerTag);
		
	}

	@Override
	public void handleShutdownSignal(String consumerTag, ShutdownSignalException e) {
		isShutdownSignal = true;
		logger.error( "Consumer,handleShutdownSignal:" + consumerTag	+"ShutdownSignalException:"+e);	
	}


	public void getMessage()
	{
		try {
			channel.basicQos(1);//1设置为1表示公平转发
			channel.basicConsume(endPointName, false, this);// false 表示打开消息应答机制
			logger.debug(" Consumer endPointName:"+endPointName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void run() {
		
		try {
			Thread.sleep(2000);//处理耗时任务
			getMessage();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
	

}
