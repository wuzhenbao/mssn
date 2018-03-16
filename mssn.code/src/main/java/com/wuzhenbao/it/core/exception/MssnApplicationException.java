package com.wuzhenbao.it.core.exception;

@SuppressWarnings("serial")
public class MssnApplicationException extends MssnBaseException {
	
	public MssnApplicationException(String code,String message){
		super(code, message);
	}
	public MssnApplicationException() {
		super("1000", "MSSN Unknown Exception");
	}
	public MssnApplicationException(String code,Throwable e) {
		super(code,e);
	}
	public MssnApplicationException(Throwable e) {
		super(e);
	}
}
