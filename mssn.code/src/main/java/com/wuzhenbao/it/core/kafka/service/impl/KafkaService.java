package com.wuzhenbao.it.core.kafka.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.wuzhenbao.it.core.exception.MssnApplicationException;
import com.wuzhenbao.it.core.kafka.handler.IKafkaHandler;
import com.wuzhenbao.it.core.kafka.service.IKafkaService;
import com.wuzhenbao.it.core.vo.kafka.KafkaMessageVO;
import com.wuzhenbao.it.core.vo.kafka.KafkaTopicVO;

@Component("kafkaService")
public class KafkaService implements IKafkaService {
	
	static Logger logger = Logger.getLogger(KafkaService.class);
	private IKafkaHandler handler;
	
	public List<KafkaTopicVO> getTopicList() throws MssnApplicationException {
		return null;
	}

	public String createTopic(String topicName) throws MssnApplicationException {
		KafkaTopicVO topic = new KafkaTopicVO();
		topic.setTopicName(topicName);
		String tableJson = "";
		boolean result = handler.createTopic(topic);
		if(result) {
			tableJson = "{result:'success.'}";
		}
		return "["+tableJson+"]";
	}

	public void sendMessage(String topicName, String message) throws MssnApplicationException {
		// TODO Auto-generated method stub
		
	}

	public List<KafkaMessageVO> consumer(String topicName) throws MssnApplicationException {
		
		return null;
	}

}
