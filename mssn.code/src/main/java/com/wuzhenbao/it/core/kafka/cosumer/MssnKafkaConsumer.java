package com.wuzhenbao.it.core.kafka.cosumer;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.log4j.Logger;

import com.wuzhenbao.it.core.exception.MssnApplicationException;
import com.wuzhenbao.it.core.util.PropertiesUtil;


public class MssnKafkaConsumer {
	static Logger logger = Logger.getLogger(MssnKafkaConsumer.class);
	  public static LinkedList<String> consumerByTopic(String topicName,LinkedList<String> queues) throws MssnApplicationException{
    	  try{
    		  Properties props = new Properties();  
    		  props.put("bootstrap.servers",PropertiesUtil.getProperty("bootstrap.servers")); 
              props.put("group.id", PropertiesUtil.getProperty("bootstrap.servers")); 
              props.put("enable.auto.commit", PropertiesUtil.getProperty("bootstrap.servers")); 
              props.put("auto.commit.interval.ms", PropertiesUtil.getProperty("bootstrap.servers")); 
              props.put("session.timeout.ms",PropertiesUtil.getProperty("bootstrap.servers")); 
              props.put("key.deserializer", PropertiesUtil.getProperty("bootstrap.servers")); 
              props.put("value.deserializer", PropertiesUtil.getProperty("bootstrap.servers")); 
              KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);  
              consumer.subscribe(Arrays.asList(topicName));  
              boolean queuesFull = queues.size() >1000;
              while (!queuesFull) {  
                  ConsumerRecords<String, String> records = consumer.poll(100);  
                  for (ConsumerRecord<String, String> record : records)  {
                	  String message = String.format("\"offset = %d, key = %s, value = %s\\n\"",record.offset(), record.key(), record.value());
                	  logger.info(message); 
                	  queues.offer(message);
                  }
                  queuesFull = queues.size() >1000;
              } 
              consumer.close();
    	  }catch(Exception e) {
    		  logger.info("consumer fail:"+e); 
    		  throw new MssnApplicationException(e);
    	  }
          return queues;
    }
}
