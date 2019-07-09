package com.yuanbao.testttt.util;

public class CheckObject {
	/**
	 * 有關Object部份的null檢核，若屬null的狀況，則傳回defaultvalue的值
	 * 
	 * @param o 原始的Object
	 * @param defaultvalue 預設的Object
	 * @return Object
	 */
	public static Object checkNull(Object o, Object defaultvalue) {
		if (o == null)
			return defaultvalue;
		else
			return o;
	}

}