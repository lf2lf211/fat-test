package com.yuanbao.testttt.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServerResponse<T> implements Serializable {

	private String retCode;
	private T data;

	private ServerResponse(String retCode) {
		this.retCode = retCode;
	}

	private ServerResponse(String retCode, T data) {
		this.retCode = retCode;
		this.data = data;
	}

	public String getRetCode() {
		return retCode;
	}

	public T getData() {
		return data;
	}

	/**
	 * 
	 * <p>以下為回傳建構子</p>
	 * 
	 * @user Eric修義 2018年5月2日 下午12:13:02
	 */

	public static <T> ServerResponse<T> create(String retCode) {
		return new ServerResponse<T>(retCode);
	}

	public static <T> ServerResponse<T> create(String retCode, T data) {
		return new ServerResponse<T>(retCode, data);
	}

}
