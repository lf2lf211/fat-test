package com.yuanbao.testttt.util;

import java.security.MessageDigest;
import java.util.Map;


public class Md5Util {

	public final static String md5(Map<String, Object> map, String key, String encodeType) {
		return md5(MapToUrlEcodedUtil.mapToString(map), key, encodeType);
	}

	public final static String md5(String paramSrc, String key, String encodeType) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			paramSrc += "&key=" + key;
			byte[] btInput = paramSrc.getBytes(encodeType);
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			mdInst.update(btInput);
			byte[] md = mdInst.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str).toLowerCase();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}