package com.wuzhenbao.it.core.vo.zk;

import java.util.List;

import com.wuzhenbao.it.core.vo.common.JqueryTreeVO;

public class ZKNode extends JqueryTreeVO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1484972821887101618L;
	private String path;
	private String parent;
	private boolean isRoot;
	private List<ZKNode> children;
	private String attribute;
	private String nodeType;
	private String stat;
	private String desc;
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public boolean isRoot() {
		return isRoot;
	}
	public void setRoot(boolean isRoot) {
		this.isRoot = isRoot;
	}
	public List<ZKNode> getChildren() {
		return children;
	}
	public void setChildren(List<ZKNode> children) {
		this.children = children;
	}
	public String getAttribute() {
		return attribute;
	}
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	public String getNodeType() {
		return nodeType;
	}
	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}
	public String getStat() {
		return stat;
	}
	public void setStat(String stat) {
		this.stat = stat;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String data) {
		this.desc = data;
	}
	
}
