package com.wuzhenbao.it.core.zookeeper.handler;


import com.wuzhenbao.it.core.exception.MssnApplicationException;
import com.wuzhenbao.it.core.vo.zk.ZKNode;

public interface IZKHandler {
	public ZKNode getZKNodeTree() throws MssnApplicationException;
	public String showZKData(String nodePath) throws MssnApplicationException;
	public String createZKNode(ZKNode zkNodeVO) throws MssnApplicationException;
	public String deleteZKNode(String nodePath) throws MssnApplicationException;
}
