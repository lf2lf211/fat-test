package com.yuanbao.testttt.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class DateUtil {

	/**
	 * Date小幫手
	 * 
	 * @return 返回西元(年月日時分秒)共14碼
	 * 
	 */
	public static String getFullSimpleDate() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

		String str = sdf.format(System.currentTimeMillis());

		return str;
	}

	/**
	 * Date小幫手
	 * 
	 * @return 返回西元(年月日)共8碼
	 */

	public static String getDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String str = sdf.format(System.currentTimeMillis());

		return str;
	}

	/**
	 * Date小幫手
	 * 
	 * @return 返回時間(時分秒)共6碼
	 * 
	 */
	public static String getTime() {

		SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");

		String str = sdf.format(System.currentTimeMillis());

		return str;
	}

	/**
	 * Date小幫手
	 * 
	 * @return 返回時間(時)共2碼(24小時制)
	 * 
	 */
	public static String getHours() {

		SimpleDateFormat sdf = new SimpleDateFormat("HH");

		String str = sdf.format(System.currentTimeMillis());

		return str;
	}

	/**
	 * Date小幫手
	 * 
	 * @return 返回時間(分)共2碼(24小時制)
	 * 
	 */
	public static String getMinutes() {

		SimpleDateFormat sdf = new SimpleDateFormat("mm");

		String str = sdf.format(System.currentTimeMillis());

		return str;
	}

	/**
	 * Date小幫手
	 * 
	 * @return 返回時間(秒)共2碼(24小時制)
	 * 
	 */
	public static String getSeconds() {

		SimpleDateFormat sdf = new SimpleDateFormat("ss");

		String str = sdf.format(System.currentTimeMillis());

		return str;
	}

	/**
	 * Date小幫手
	 * 
	 * @return 返回明日時間 西元(年月日)共8碼
	 */
	public static String getYesterday() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		String str = sdf.format(cal.getTime());

		return str;
	}
}
