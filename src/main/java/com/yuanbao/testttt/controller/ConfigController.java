package com.yuanbao.testttt.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ConfigController {

	@Value("${server.port}")
	private String port;

	@Value("${spring.application.name}")
	private String applicationName;

	@GetMapping("/testConfig")
	public String testConfig() {
		return "服務:" + applicationName + "<BR>啟動在:" + this.port + "port";
	}

}
