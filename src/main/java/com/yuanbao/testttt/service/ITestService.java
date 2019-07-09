package com.yuanbao.testttt.service;

import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.util.Map;


public interface ITestService {

	public JSONObject getBalance(String token, String sign) throws IOException;

	public Map<String, Object> getLoginInfo(String token, String sign) throws IOException;

	public JSONObject updateBalance(String data) throws IOException, InterruptedException;

}
