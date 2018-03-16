package com.wuzhenbao.it.core.vo.hadoop;

import java.util.List;

import com.wuzhenbao.it.core.vo.common.JqueryTreeVO;
/**
 * hdfs目录的vo
 * @author lenovo
 *
 */
public class HdfsDirVO extends JqueryTreeVO {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7922596477603882256L;
	//是否为要目录
	private boolean isRoot;
	//上层目录路径
	private String parentPath;
	//子目录或文件
	private List<HdfsDirVO> children;
	//是否是目录
	private boolean isDir;
	
	public String getId() {
		if(isRoot) {
			return this.getText();
		}
		return this.parentPath + this.getText();
	}
	
	public List<HdfsDirVO> getChildren() {
		return children;
	}
	public void setChildren(List<HdfsDirVO> children) {
		this.children = children;
	}
	
	public boolean isRoot() {
		return isRoot;
	}
	public void setRoot(boolean isRoot) {
		this.isRoot = isRoot;
	}
	public String getParentPath() {
		return parentPath;
	}
	public void setParentPath(String parentPath) {
		this.parentPath = parentPath;
	}
	public boolean isDir() {
		return isDir;
	}
	public void setDir(boolean isDir) {
		this.isDir = isDir;
	}
	
	
}
