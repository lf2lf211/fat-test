/*******************************************************************************
 * Project Key : CPPII
 * Create on 2018年05月07日 上午10:27:06
 * Copyright (c) 2017.炬火數位有限公司版權所有. 
 * 注意：本內容僅限於炬火數位內部傳閱，禁止外洩以及用於其他商業目的
 ******************************************************************************/
package com.yuanbao.testttt.controller.base;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;


/**
 * 
 * @version 1.0.0
 * @user davidccc 2018年05月07日 上午10:27:06
 * 
 *       所有Controller類都繼承此方法， 可在此捕捉例外並返回訊息
 * 
 */

public class DefaultController {

	protected static final HttpHeaders headers = new HttpHeaders();
    
	static {
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
	}
	
}
