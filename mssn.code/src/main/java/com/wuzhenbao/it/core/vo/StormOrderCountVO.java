package com.wuzhenbao.it.core.vo;

public class StormOrderCountVO {
	
	private Long orderCountId;
	private String ip;
	private String productName;
	private String customer;
	private String orderType;
	private long orderCount;
	private double orderAmountTotal;
	private long orderQtySum;
	private String batchId;
	public Long getOrderCountId() {
		return orderCountId;
	}
	public void setOrderCountId(Long orderCountId) {
		this.orderCountId = orderCountId;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public long getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(long orderCount) {
		this.orderCount = orderCount;
	}
	public double getOrderAmountTotal() {
		return orderAmountTotal;
	}
	public void setOrderAmountTotal(double orderAmountTotal) {
		this.orderAmountTotal = orderAmountTotal;
	}
	public long getOrderQtySum() {
		return orderQtySum;
	}
	public void setOrderQtySum(long orderQtySum) {
		this.orderQtySum = orderQtySum;
	}
	public String getBatchId() {
		return batchId;
	}
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	
	
}
