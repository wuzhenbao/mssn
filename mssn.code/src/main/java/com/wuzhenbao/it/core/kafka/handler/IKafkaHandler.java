package com.wuzhenbao.it.core.kafka.handler;

import java.util.List;

import com.wuzhenbao.it.core.exception.MssnApplicationException;
import com.wuzhenbao.it.core.vo.kafka.KafkaMessageVO;
import com.wuzhenbao.it.core.vo.kafka.KafkaTopicVO;

public interface IKafkaHandler {
	/**
	 * 获取topic的列表
	 * @return
	 * @throws MssnApplicationException
	 */
	public List<KafkaTopicVO> getTopicList() throws MssnApplicationException;
	/**
	 * 新创建topic
	 * @param topic
	 * @return
	 * @throws MssnApplicationException
	 */
	public boolean createTopic(KafkaTopicVO topic)throws MssnApplicationException;
	/**
	 * 生产消息
	 * @param message
	 * @param topic
	 * @throws MssnApplicationException
	 */
	public void sendMessage(KafkaMessageVO message,KafkaTopicVO topic)throws MssnApplicationException;
	/**
	 * 消费消息
	 * @param topic
	 * @return
	 * @throws MssnApplicationException
	 */
	public List<KafkaMessageVO> consumer(KafkaTopicVO topic)throws MssnApplicationException;
}
