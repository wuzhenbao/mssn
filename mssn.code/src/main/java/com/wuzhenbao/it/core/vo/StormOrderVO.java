package com.wuzhenbao.it.core.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StormOrderVO  {
	/**
	 * 鎵规鍙�
	 */
	private String batchId;
	
	private Long orderId;
	/**
	 * 
	 */
	private String productName;
	/**
	 * 璁㈠崟鐨処P锛�92.168.1.1~254
	 */
	private String ip;
	
	/**
	 * 璁㈠崟鍖哄煙锛屼腑鍥藉悇鐪佷唤
	 */
	private String area;
	/**
	 * 璁㈠崟绫诲瀷锛�涓篜C璁㈠崟锛�涓烘墜鏈鸿鍗�
	 */
	private String orderType;
	/**
	 * 璁㈠崟璇︾粏璁㈠崟
	 */
	private String address;
	/**
	 * 璁㈠崟浠锋牸
	 */
	private double price;
	/**
	 * 璁㈠崟鏁伴噺
	 */
	private int qty;
	/**
	 * 璁㈠崟鏃ユ湡
	 */
	private Date orderDate;
	/**
	 * 璁㈠崟瀹㈡埛
	 */
	private String customer;
	
	
	public String getBatchId() {
		return batchId;
	}
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}


	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	public String toString(){
		StringBuffer rs = new StringBuffer();
		if(this.orderDate != null){
			rs = rs.append(sdf.format(this.orderDate)).append(",");
		}
		rs = rs.append(this.getBatchId()+ ",");
		rs = rs.append(this.ip).append(",");
		rs = rs.append(this.productName).append(",");
		rs = rs.append(this.area).append(",");
		rs = rs.append(this.orderType).append(",");
		//rs = rs.append(this.address).append(",");
		rs = rs.append(this.price).append(",");
		rs = rs.append(this.qty).append(",");
		rs = rs.append(this.customer).append(" ");
		return rs.toString();
	}
}
