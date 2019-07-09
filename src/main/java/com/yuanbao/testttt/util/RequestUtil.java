package com.yuanbao.testttt.util;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


public class RequestUtil {

	// 請求超時設定
	private static int CONN_TIME_OUT = 5000;
	private static int SO_TIME_OUT = 5000;
	private static RequestConfig REQUEST_CONFIG = RequestConfig.custom().setConnectTimeout(CONN_TIME_OUT)
			.setConnectionRequestTimeout(1000).setSocketTimeout(SO_TIME_OUT).build();

	// 發送post請求,可傳入headerMap
	public static String doPost(String url, String paras, Map<String, Object> header) throws IOException {

			
			// 建立Httpclient對象
			CloseableHttpClient httpclient = HttpClients.custom().setConnectionManagerShared(true)
					.setDefaultRequestConfig(REQUEST_CONFIG).build();
			try {

				HttpPost httppost = new HttpPost(url);

				if (null != header) {
					for (Map.Entry<String, Object> entry : header.entrySet()) {
						httppost.addHeader(entry.getKey(), entry.getValue().toString());
					}
				}

				httppost.setEntity(new StringEntity(paras, Charset.forName("UTF-8")));

				// 建立HttpResponse對象
				CloseableHttpResponse response = httpclient.execute(httppost);
				try {
					HttpEntity entity = response.getEntity();
					String responseResult = "";
					if (entity != null) {
						responseResult = EntityUtils.toString(entity);
						return responseResult;
					}
				} finally {
					response.close();
				}
			} catch (Exception e) {
				// 此處超時例外不捕捉
				if (!(e instanceof SocketTimeoutException)) {
					throw e;
				}
			} finally {
				httpclient.close();
			}
			return null;
	}

	// 發送puT請求,可傳入headerMap
	public static String doPut(String url, String paras, Map<String, Object> header) throws IOException {

		// 建立Httpclient對象
		CloseableHttpClient httpclient = HttpClients.custom().setConnectionManagerShared(true)
				.setDefaultRequestConfig(REQUEST_CONFIG).build();
		try {

			HttpPut httpPut = new HttpPut(url);
			for (Map.Entry<String, Object> entry : header.entrySet()) {
				httpPut.addHeader(entry.getKey(), entry.getValue().toString());
			}

			httpPut.setEntity(new StringEntity(paras, Charset.forName("UTF-8")));

			// 建立HttpResponse對象
			CloseableHttpResponse response = httpclient.execute(httpPut);
			try {
				HttpEntity entity = response.getEntity();
				String responseResult = "";
				if (entity != null) {
					responseResult = EntityUtils.toString(entity);
					return responseResult;
				}
			} finally {
				response.close();
			}
		} catch (Exception e) {
			// 此處超時例外不捕捉
			if (!(e instanceof SocketTimeoutException)) {
				throw e;
			}
		} finally {
			httpclient.close();
		}
		return null;
	}

	// 發送Get請求
	public static String doGet(String url, String paras, Map<String, Object> header) throws IOException {


			// 建立Httpclient對象
			CloseableHttpClient httpclient = HttpClients.custom().setConnectionManagerShared(true)
					.setDefaultRequestConfig(REQUEST_CONFIG).build();
			try {

				HttpGet httpget = new HttpGet(url + "?" + paras);
				for (Map.Entry<String, Object> entry : header.entrySet()) {
					httpget.addHeader(entry.getKey(), entry.getValue().toString());
				}
				// 建立HttpResponse對象
				CloseableHttpResponse response = httpclient.execute(httpget);
				try {
					HttpEntity entity = response.getEntity();
					String responseResult = "";
					if (entity != null) {
						responseResult = EntityUtils.toString(entity);
						return responseResult;
					}
				} finally {
					response.close();
				}
			} catch (Exception e) {
				// 此處超時例外不捕捉
				if (!(e instanceof SocketTimeoutException)) {
					throw e;
				}
			} finally {
				httpclient.close();
			}
			return null;
	}

	// 發送post請求,傳送json格式,指定回傳資料格式為json、編碼為utf-8
	public static String doPost(String url, String paras) throws IOException {

		Map<String, Object> header = new HashMap<String, Object>();
		header.put("Content-type", "application/json; charset=utf-8");

		return doPost(url, paras, header);
	}

	// 發送put請求,傳送預設資料型態,指定回傳資料格式為json、編碼為utf-8
	public static String doPut(String url, String paras) throws IOException {

		Map<String, Object> header = new HashMap<String, Object>();
		header.put("Content-type", "application/json; charset=utf-8");

		return doPut(url, paras, header);
	}

	// 發送get請求,傳送預設資料型態,指定回傳資料格式為json、編碼為utf-8
	public static String doGet(String url, String paras) throws IOException {

		Map<String, Object> header = new HashMap<String, Object>();
		header.put("Content-type", "application/x-www-form-urlencoded; charset=utf-8");

		return doGet(url, paras, header);
	}

}
