package com.wuzhenbao.it.core.kafka.handler;

import java.util.List;

import com.wuzhenbao.it.core.exception.MssnApplicationException;
import com.wuzhenbao.it.core.vo.kafka.KafkaMessageVO;
import com.wuzhenbao.it.core.vo.kafka.KafkaTopicVO;

public class KafkaHandlerImpl implements IKafkaHandler {

	public List<KafkaTopicVO> getTopicList() throws MssnApplicationException {
		
		return null;
	}
	public boolean createTopic(KafkaTopicVO topic) throws MssnApplicationException {
		return false;
	}
	public void sendMessage(KafkaMessageVO message,KafkaTopicVO topic) throws MssnApplicationException {
		
	}
	public List<KafkaMessageVO> consumer(KafkaTopicVO topic) throws MssnApplicationException {
		return null;
	}
}
