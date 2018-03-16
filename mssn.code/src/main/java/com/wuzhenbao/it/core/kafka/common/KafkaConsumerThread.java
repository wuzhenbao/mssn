package com.wuzhenbao.it.core.kafka.common;

import java.util.LinkedList;

import org.apache.log4j.Logger;


public class KafkaConsumerThread implements Runnable{
	static Logger logger = Logger.getLogger(KafkaConsumerThread.class);
	private String consumerName;
	private LinkedList<String> queues;
	
	public KafkaConsumerThread(String consumerName,LinkedList<String> queues) {
		this.consumerName = consumerName;
		this.queues = queues;
	}
	
	public void run() {
		
		logger.info("开始运行消费");
	}


	public String getConsumerName() {
		return consumerName;
	}


	public void setConsumerName(String consumerName) {
		this.consumerName = consumerName;
	}

	public LinkedList<String> getQueues() {
		return queues;
	}

	public void setQueues(LinkedList<String> queues) {
		this.queues = queues;
	}
	
}
