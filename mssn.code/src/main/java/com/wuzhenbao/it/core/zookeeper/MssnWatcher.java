package com.wuzhenbao.it.core.zookeeper;

import org.apache.log4j.Logger;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

public class MssnWatcher implements Watcher {
	static Logger logger = Logger.getLogger(MssnWatcher.class);
	public void process(WatchedEvent event) {
		logger.info("----------->");
		logger.info("path:" + event.getPath());
		logger.info("type:" + event.getType());
		logger.info("stat:" + event.getState());
		logger.info("<-----------");
	}

}
