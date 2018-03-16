package com.wuzhenbao.it.core.vo;

import com.wuzhenbao.it.core.vo.common.BaseVO;

public class AttachmentVO extends BaseVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer attaId ;
	private String attaName;
	private String attaPath;
	private String attaType;
	public Integer getAttaId() {
		return attaId;
	}
	public void setAttaId(Integer attaId) {
		this.attaId = attaId;
	}
	public String getAttaName() {
		return attaName;
	}
	public void setAttaName(String attaName) {
		this.attaName = attaName;
	}
	public String getAttaPath() {
		return attaPath;
	}
	public void setAttaPath(String attaPath) {
		this.attaPath = attaPath;
	}
	public String getAttaType() {
		return attaType;
	}
	public void setAttaType(String attaType) {
		this.attaType = attaType;
	}
}
