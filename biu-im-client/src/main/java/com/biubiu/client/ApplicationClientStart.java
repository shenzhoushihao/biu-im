package com.biubiu.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.biubiu.client.config.IMClientConfiguation;

/**
 * 客户端启动
 */
@SpringBootApplication(scanBasePackages = { "com.biubiu.client" })
public class ApplicationClientStart {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(ApplicationClientStart.class, args);
		IMClientConfiguation imConf = applicationContext.getBean(IMClientConfiguation.class);
		if (!imConf.checkConfig()) {
			applicationContext.close();
		}
	}
}
