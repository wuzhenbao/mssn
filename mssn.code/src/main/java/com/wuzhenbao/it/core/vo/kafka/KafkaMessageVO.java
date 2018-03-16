package com.wuzhenbao.it.core.vo.kafka;

import com.wuzhenbao.it.core.vo.common.BaseVO;

public class KafkaMessageVO extends BaseVO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
