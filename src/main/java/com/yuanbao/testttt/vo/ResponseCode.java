package com.yuanbao.testttt.vo;

public enum ResponseCode {

	SUCESS("0");

	private final String retCode;

	ResponseCode(String retCode) {
		this.retCode = retCode;

	}

	public String getRetCode() {
		return retCode;
	}

}
