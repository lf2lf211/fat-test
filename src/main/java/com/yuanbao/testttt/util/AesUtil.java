package com.yuanbao.testttt.util;

public class AesUtil {

	// public final static String aesEncrypt(Map<String, Object> map, String key, String encodeType)
	// throws Exception {
	// return aesEncrypt(MapToUrlEcodedUtil.mapToString(map), key, encodeType);
	// }
	//
	// public final static String aesEncrypt(String str, String key, String encodeType) throws
	// Exception {
	// if (str == null || key == null)
	// return null;
	// Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
	// cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes(encodeType), "AES"));
	// byte[] bytes = cipher.doFinal(str.getBytes(encodeType));
	// return new BASE64Encoder().encode(bytes);
	// }
	//
	// public final static String aesDecrypt(String str, String key, String encodeType) throws
	// Exception {
	// if (str == null || key == null)
	// return null;
	// Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
	// cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes(encodeType), "AES"));
	// byte[] bytes = new BASE64Decoder().decodeBuffer(str);
	// bytes = cipher.doFinal(bytes);
	// return new String(bytes, encodeType);
	// }

}
