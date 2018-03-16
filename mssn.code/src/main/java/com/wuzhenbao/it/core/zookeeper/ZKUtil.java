package com.wuzhenbao.it.core.zookeeper;

import org.apache.log4j.Logger;
import org.apache.zookeeper.ZooKeeper;

import com.wuzhenbao.it.core.util.PropertiesUtil;

public class ZKUtil {
	static Logger logger = Logger.getLogger(ZKUtil.class);
	private static ZooKeeper zk;
	public static ZooKeeper getZK() {
		try {
			if(zk == null) {
				String zkHosts = PropertiesUtil.getProperty("zk.hosts");
				String timeout = PropertiesUtil.getProperty("zk.timeout");
				zk = new ZooKeeper(zkHosts, Integer.parseInt(timeout), new MssnWatcher());
			}
		}catch(Exception e) {
			logger.error("加载ZK连接失败！"+e);
		}
		return zk;
	}
}
