package com.wuzhenbao.it.core.vo;

import com.wuzhenbao.it.core.vo.common.BaseVO;

public class UserVO extends BaseVO{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2762335585841045375L;
    private Integer userId;
    private String userNameCn;
	private String userNameEn;
    private String userEmail;
    private String userPhone;
    private String userPassword;
    private String userPassword1;
    private String userAddress;
    private String userSex;
    private String attaId;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	 public String getUserNameCn() {
			return userNameCn;
		}
		public void setUserNameCn(String userNameCn) {
			this.userNameCn = userNameCn;
		}
		public String getUserNameEn() {
			return userNameEn;
		}
		public void setUserNameEn(String userNameEn) {
			this.userNameEn = userNameEn;
		}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public String getUserSex() {
		return userSex;
	}
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}
	public String getAttaId() {
		return attaId;
	}
	public void setAttaId(String attaId) {
		this.attaId = attaId;
	}
	public String getUserPassword1() {
		return userPassword1;
	}
	public void setUserPassword1(String userPassword1) {
		this.userPassword1 = userPassword1;
	}
    
}
