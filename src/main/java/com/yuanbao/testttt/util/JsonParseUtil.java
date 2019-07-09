/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年2月18日 下午5:33:55
 * Copyright (c) 2017.炬火數位有限公司版權所有. 
 * 注意：本內容僅限於炬火數位內部傳閱，禁止外洩以及用於其他商業目的
 ******************************************************************************/
package com.yuanbao.testttt.util;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


/**
 * <P>可將各類java pojo對象轉為jsonStr供網關使用</P>
 * 
 * @version 1.0.0
 * @user davidccc 2018年2月18日 下午5:33:55
 */
public class JsonParseUtil {

	// 建立Jackson對象
	private static final ObjectMapper MAPPER = new ObjectMapper();

	/**
	 * 將對象轉為JsonStr
	 */
	public static String objectToJson(Object data) {
		try {
			MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
			String string = MAPPER.writeValueAsString(data);
			// 去除忽略符號
			return string.replaceAll("\\\\", "");
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 將JsonStr轉為對象
	 */
	public static <T> T jsonToPojo(String jsonData, Class<T> beanType) {
		try {
			T t = MAPPER.readValue(jsonData, beanType);
			return t;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 將jsonStr轉換為List
	 */
	public static <T> List<T> jsonToList(String jsonData, Class<T> beanType) {

		// 此方法用于构造List的Java类型
		JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
		try {
			List<T> list = MAPPER.readValue(jsonData, javaType);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// public void testMain() {
	// CreateBetModelReq createBetModelReq = new CreateBetModelReq();
	//
	// JSONArray jsonArray = new JSONArray();
	//
	// JSONObject jsonObject = new JSONObject();
	// jsonObject.put("firstJson", "111");
	// jsonArray.put(0, jsonObject.toString());
	// jsonObject = new JSONObject();
	// jsonObject.put("secondJson", "222");
	// jsonArray.put(1, jsonObject.toString());
	// createBetModelReq.setAccount("GA00001234");
	// createBetModelReq.setBetResult(jsonArray);
	// createBetModelReq.setGameNumber("COW20180218175734");
	//
	// String result = objectToJson(createBetModelReq);
	// System.out.println("輸出結果:" + result);
	// }

}