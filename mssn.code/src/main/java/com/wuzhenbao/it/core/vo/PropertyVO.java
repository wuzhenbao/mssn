package com.wuzhenbao.it.core.vo;

import com.wuzhenbao.it.core.vo.common.BaseVO;

public class PropertyVO extends BaseVO {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer propertyId;
     private String displayName;
     private String value;
     private Integer parentId;
     private String name;
     private String parentPath;
     private Integer orderBy;
     public PropertyVO(){
    	 
     }
	public Integer getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}
	public Integer getPropertyId() {
		return propertyId;
	}
	public void setPropertyId(Integer propertyId) {
		this.propertyId = propertyId;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParentPath() {
		return parentPath;
	}
	public void setParentPath(String parentPath) {
		this.parentPath = parentPath;
	}
}
