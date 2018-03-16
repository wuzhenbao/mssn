package com.wuzhenbao.it.core.zookeeper.handler;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import com.wuzhenbao.it.core.exception.MssnApplicationException;
import com.wuzhenbao.it.core.util.CommonUtil;
import com.wuzhenbao.it.core.vo.zk.ZKNode;
import com.wuzhenbao.it.core.zookeeper.ZKUtil;

public class ZKHandleImpl implements IZKHandler {
	static Logger logger = Logger.getLogger(ZKHandleImpl.class);
	
	public ZKNode getZKNodeTree() throws MssnApplicationException{
		ZKNode node = new ZKNode();
		try {
			ZooKeeper zk = ZKUtil.getZK();
			String nodePath = "/";
			Stat stat = zk.exists(nodePath, false);
			if(stat != null) {
				node.setPath(nodePath);
				node.setText(nodePath);
				node.setRoot(true);
				node.setId(nodePath);
				node = getZKNodeChildren(node);
			}
		} catch (KeeperException e) {
			logger.error("查询zk目录出错："+e);
			throw new MssnApplicationException(e);
		} catch (InterruptedException e) {
			logger.error("查询zk目录出错："+e);
			throw new MssnApplicationException(e);
		}
		return node;
	}
	/**
	 * 遍历所有的ZK树节点
	 * @param node
	 * @return
	 * @throws MssnApplicationException
	 */
	private ZKNode getZKNodeChildren(ZKNode node) throws MssnApplicationException{
		ZKNode nodeNew = node;
		List<ZKNode> nodes = new ArrayList<ZKNode>();
		try {
			ZooKeeper zk = ZKUtil.getZK();
			String nodePath =nodeNew.getPath();
			List<String> children = zk.getChildren(nodePath, true);
			for(String child : children) {
				ZKNode nodeChild = new ZKNode();
				String parentPath = nodeNew.isRoot()?nodeNew.getPath():nodeNew.getPath()+"/";
				nodeChild.setPath(parentPath+child);
				nodeChild.setText(child);
				nodeChild.setRoot(false);
				nodeChild.setId(child);
				nodeChild.setParent(nodeNew.getPath());
				nodeChild = getZKNodeChildren(nodeChild);
				nodes.add(nodeChild);
			}
			nodeNew.setChildren(nodes);
		} catch (KeeperException e) {
			logger.error("查询zk目录出错："+e);
			throw new MssnApplicationException(e);
		} catch (InterruptedException e) {
			logger.error("查询zk目录出错："+e);
			throw new MssnApplicationException(e);
		}
		return nodeNew;
	}
	/**
	 * 读取指定节点数据内容 
	 */
	public String showZKData(String nodePath) throws MssnApplicationException {
		String result = "{}";
		try {
			logger.info("获取数据，path：" + nodePath);
			ZooKeeper zk = ZKUtil.getZK();
			byte[] zkdata = zk.getData(nodePath, false, null);
			if(zkdata != null && zkdata.length > 0) {
				result =new String(zkdata);
				if(!result.startsWith("{")) {
					result = "{result:'" + result +"'}";
				}
			}
		} catch (KeeperException e) {
			logger.error("读取zk节点数据出错：" + e);
			throw new MssnApplicationException(e);
		} catch (InterruptedException e) {
			logger.error("读取zk节点数据出错：" + e);
			throw new MssnApplicationException(e);
		}
		return result;
	}
	/**
	 * 创建节点
	 */
	public String createZKNode(ZKNode zkNodeVO) throws MssnApplicationException {
		String result = "{}";
		try {
			String path = CommonUtil.deFormatPath(zkNodeVO.getPath());
			logger.info("创建ZK节点，path：" + path);
			ZooKeeper zk = ZKUtil.getZK();
			//校验节点路径是否已经存在
			Stat stat = zk.exists(path, false);
			if(stat != null) {
				result = "{result:'node path is exists.'}";
				return result;
			}
			byte[] data = zkNodeVO.getDesc() == null?null:zkNodeVO.getDesc().getBytes();
			
			String flag = zk.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			if(flag != null) {
				result = "{result:'create node success.'}";
			}
		} catch (KeeperException e) {
			logger.error("读取zk节点数据出错：" + e);
			throw new MssnApplicationException(e);
		} catch (InterruptedException e) {
			logger.error("读取zk节点数据出错：" + e);
			throw new MssnApplicationException(e);
		}
		return result;
	}
	/**
	 * 删除ZK节点
	 */
	public String deleteZKNode(String nodePath) throws MssnApplicationException {
		String result = "{}";
		try {
			String path = CommonUtil.deFormatPath(nodePath);
			logger.info("创建ZK节点，path：" + path);
			ZooKeeper zk = ZKUtil.getZK();
			//校验节点路径是否已经存在
			zk.delete(path, -1);
			result = "{result:'delete node success.'}";
		} catch (KeeperException e) {
			logger.error("读取zk节点数据出错：" + e);
			throw new MssnApplicationException(e);
		} catch (InterruptedException e) {
			logger.error("读取zk节点数据出错：" + e);
			throw new MssnApplicationException(e);
		}
		return result;
	} 
}
