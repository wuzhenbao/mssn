package com.wuzhenbao.it.core.vo;

import com.wuzhenbao.it.core.vo.common.BaseVO;

public class RoleVO extends BaseVO {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer roleId;
    private String roleName;
    private Integer orderBy;
    public RoleVO(){
    	 
     }
	public Integer getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
