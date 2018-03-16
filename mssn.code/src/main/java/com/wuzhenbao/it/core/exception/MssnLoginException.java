package com.wuzhenbao.it.core.exception;

@SuppressWarnings("serial")
public class MssnLoginException extends MssnBaseException {
	public MssnLoginException() {
		super("1001", "Login Error");
	}
	
}
