package com.wuzhenbao.it.core.vo;

import java.util.Date;

import com.wuzhenbao.it.core.vo.common.BaseVO;

public class CatalogVO extends BaseVO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4980460819615800534L;
	private Integer catalogId;
	private String catalogNameCn;
	private String catalogNameEn;
	private String catalogType;
	private String catalogUrl;
	private String parentId;
	private String deleted;
	private String createBy;
	private String ordernum;
	private Date createDate;
	private String lastUpdateBy;
	private Date lastUpdateDate;
	private String description;
	private Integer level;
	
	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public CatalogVO(){}
	
	public String getCatalogType() {
		return catalogType;
	}
	public void setCatalogType(String catalogType) {
		this.catalogType = catalogType;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	

	public Integer getCatalogId() {
		return catalogId;
	}
	public void setCatalogId(Integer catalogId) {
		this.catalogId = catalogId;
	}
	public String getCatalogNameCn() {
		return catalogNameCn;
	}
	public void setCatalogNameCn(String catalogNameCn) {
		this.catalogNameCn = catalogNameCn;
	}
	public String getCatalogNameEn() {
		return catalogNameEn;
	}
	public void setCatalogNameEn(String catalogNameEn) {
		this.catalogNameEn = catalogNameEn;
	}
	public String getCatalogUrl() {
		return catalogUrl;
	}
	public void setCatalogUrl(String catalogUrl) {
		this.catalogUrl = catalogUrl;
	}
	public String getDeleted() {
		return deleted;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getLastUpdateBy() {
		return lastUpdateBy;
	}
	public void setLastUpdateBy(String lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(String ordernum) {
		this.ordernum = ordernum;
	}
	
}
