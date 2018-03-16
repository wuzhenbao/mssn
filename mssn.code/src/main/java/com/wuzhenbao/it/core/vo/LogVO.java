package com.wuzhenbao.it.core.vo;

import java.util.Date;

import com.wuzhenbao.it.core.vo.common.BaseVO;

//@Entity
//@Table(name="T_SYS_LOG")
//@SequenceGenerator(name="seq_log",sequenceName="S_SYS_LOG",allocationSize=1)
public class LogVO extends BaseVO {
	private static final long serialVersionUID = 1L;
	private Integer logId;
	private String ipAddress;
	private Date logTime;
	private String logType;
	private String logContent;
	private String objectId;
	private String businessType;
	
	public LogVO(String ipAddress, Date logTime,
			String logType, String logContent, String objectId,
			String businessType) {
		super();
		this.ipAddress = ipAddress;
		this.logTime = logTime;
		this.logType = logType;
		this.logContent = logContent;
		this.objectId = objectId;
		this.businessType = businessType;
	}

	public LogVO() {
		super();
	}
	public Integer getLogId() {
		return logId;
	}
	public void setLogId(Integer logId) {
		this.logId = logId;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public Date getLogTime() {
		return logTime;
	}
	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}
	public String getLogType() {
		return logType;
	}
	public void setLogType(String logType) {
		this.logType = logType;
	}
	public String getLogContent() {
		return logContent;
	}
	public void setLogContent(String logContent) {
		this.logContent = logContent;
	}
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	
}
