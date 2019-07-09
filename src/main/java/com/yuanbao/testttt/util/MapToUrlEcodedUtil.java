package com.yuanbao.testttt.util;

import java.util.Map;


public class MapToUrlEcodedUtil {

	public final static String mapToString(Map<String, Object> map) {
		StringBuilder sb = new StringBuilder();
		for (String key : map.keySet()) {
			String value = (String) map.get(key);
			if (!ifNull(value)) {
				sb.append(key + "=" + value + "&");
			}
		}
		return sb.toString().substring(0, sb.toString().length() - 1);
	}

	private final static boolean ifNull(String object) {
		if (object == null || object.trim().length() == 0) {
			return true;
		}
		return false;
	}

}