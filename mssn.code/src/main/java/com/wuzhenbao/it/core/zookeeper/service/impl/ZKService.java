package com.wuzhenbao.it.core.zookeeper.service.impl;

import org.springframework.stereotype.Component;

import com.wuzhenbao.it.core.exception.MssnApplicationException;
import com.wuzhenbao.it.core.util.CommonUtil;
import com.wuzhenbao.it.core.vo.zk.ZKNode;
import com.wuzhenbao.it.core.zookeeper.handler.IZKHandler;
import com.wuzhenbao.it.core.zookeeper.handler.ZKHandleImpl;
import com.wuzhenbao.it.core.zookeeper.service.IZKService;

import net.sf.json.JSONObject;

@Component("zkService")
public class ZKService implements IZKService {
	private IZKHandler handler;
	 
	public ZKService() {
		handler = new ZKHandleImpl();
	}
	public String getZKTree() throws MssnApplicationException {
		ZKNode node = handler.getZKNodeTree();
		String tableJson = JSONObject.fromObject(node).toString();;
		return "["+tableJson+"]";
	}
	
	public String showZKData(String nodePath) throws MssnApplicationException {
		String tableJson = "";
		String result = handler.showZKData(CommonUtil.deFormatPath(nodePath));
		if(result != null) {
			tableJson = JSONObject.fromObject(result).toString();
		}
		return "["+tableJson+"]";
	}
	public String createZKNode(String path,String desc) throws MssnApplicationException {
		ZKNode zkNodeVO = new ZKNode();
		zkNodeVO.setPath(path);
		zkNodeVO.setDesc(desc);
		String tableJson = "";
		String result = handler.createZKNode(zkNodeVO);
		if(result != null) {
			tableJson = JSONObject.fromObject(result).toString();
		}
		return "["+tableJson+"]";
	}
	public String deleteZKNode(String nodePath) throws MssnApplicationException {
		String tableJson = "";
		String result = handler.deleteZKNode(nodePath);
		if(result != null) {
			tableJson = JSONObject.fromObject(result).toString();
		}
		return "["+tableJson+"]";
	}

}
