package com.wuzhenbao.it.core.exception;

@SuppressWarnings("serial")
public class MssnBaseException extends Exception {
	private String code;
	private String message;
	public MssnBaseException(String code,String message) {
		super(code+":"+message);
		this.code = code;
		this.message = message;
	}
	public MssnBaseException(String message) {
		super(message);
	}
	public MssnBaseException(String code,Throwable e) {
		super(code,e);
	}
	public MssnBaseException(Throwable e) {
		super(e);
	}
	public MssnBaseException(){}
}
