package com.wuzhenbao.it.core.zookeeper;

import org.apache.log4j.Logger;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class TestZK {
	static Logger logger = Logger.getLogger(TestZK.class);
	public static void main(String[] args) {
		logger.info("开始工作");
		try {
			ZooKeeper zk = new ZooKeeper("storm.master:2181,storm.slave1:2181,storm.slave2:2181", 60000, new DemoWatcher());
			logger.info("链接上服务器了="+zk.getSessionId());
			String node = "/";
			Stat stat = zk.exists(node, false);
			if(stat == null){
				String result = zk.create(node, "testZookeeper".getBytes(), 
						ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
				logger.info("result=="+result);
			}
			byte[] b = zk.getData(node, false, stat);
			logger.info("getData=="+b);
			zk.close();
		} catch (Exception e) {
			logger.info(e);
		}finally{
			
		}
		
	}
	
	static class DemoWatcher implements Watcher{

		public void process(WatchedEvent event) {
			logger.info("----------->");
			logger.info("path:" + event.getPath());
			logger.info("type:" + event.getType());
			logger.info("stat:" + event.getState());
			logger.info("<-----------");
		}
		
	}
}
