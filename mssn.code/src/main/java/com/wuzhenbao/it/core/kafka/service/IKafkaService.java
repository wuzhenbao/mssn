package com.wuzhenbao.it.core.kafka.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.wuzhenbao.it.core.exception.MssnApplicationException;
import com.wuzhenbao.it.core.vo.kafka.KafkaMessageVO;
import com.wuzhenbao.it.core.vo.kafka.KafkaTopicVO;

@Path("/kafkaService")
public interface IKafkaService {
	/**
	 * 获取topic的列表
	 * @return
	 * @throws MssnApplicationException
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/getTopicList")
	public List<KafkaTopicVO> getTopicList() throws MssnApplicationException;
	/**
	 * 新创建topic
	 * @param topic
	 * @return
	 * @throws MssnApplicationException
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/createTopic/{topicName}")
	public String createTopic(@QueryParam("topicName")String topicName)throws MssnApplicationException;
	/**
	 * 生产消息
	 * @param message
	 * @param topic
	 * @throws MssnApplicationException
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/sendMessage/{topicName}/{message}")
	public void sendMessage(@QueryParam("topicName")String topicName,
			@QueryParam("message")String message)throws MssnApplicationException;
	/**
	 * 消费消息
	 * @param topic
	 * @return
	 * @throws MssnApplicationException
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/consumer/{topicName}")
	public List<KafkaMessageVO> consumer(@PathParam("topicName")String topicName)throws MssnApplicationException;
}
