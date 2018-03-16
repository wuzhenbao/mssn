package com.wuzhenbao.it.core.vo;

import com.wuzhenbao.it.core.vo.common.BaseVO;

public class PermissionVO extends BaseVO {

	private static final long serialVersionUID = 5056380549701048733L;
	private Integer permissionItemId;
	private String permissCode;
	private String permissDesc;
	private String operationCode;
	private String operationDesc;
	private String className;
	
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	private Class<?> cls;
	
	
	public Integer getPermissionItemId() {
		return permissionItemId;
	}
	public void setPermissionItemId(Integer permissionItemId) {
		this.permissionItemId = permissionItemId;
	}
	public String getPermissCode() {
		return permissCode;
	}
	public void setPermissCode(String permissCode) {
		this.permissCode = permissCode;
	}
	public String getPermissDesc() {
		return permissDesc;
	}
	public void setPermissDesc(String permissDesc) {
		this.permissDesc = permissDesc;
	}
	public String getOperationCode() {
		return operationCode;
	}
	public void setOperationCode(String operationCode) {
		this.operationCode = operationCode;
	}
	public String getOperationDesc() {
		return operationDesc;
	}
	public void setOperationDesc(String operationDesc) {
		this.operationDesc = operationDesc;
	}
	public Class<?> getCls() {
		return cls;
	}
	public void setCls(Class<?> cls) {
		this.cls = cls;
	}
}
