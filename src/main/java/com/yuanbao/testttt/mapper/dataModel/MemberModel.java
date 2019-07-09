package com.yuanbao.testttt.mapper.dataModel;

import java.io.Serializable;
import java.math.BigDecimal;


public class MemberModel implements Serializable {

	private String account;
	private BigDecimal balance;
	private String name;
	private String headerUrl;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHeaderUrl() {
		return headerUrl;
	}

	public void setHeaderUrl(String headerUrl) {
		this.headerUrl = headerUrl;
	}

}
