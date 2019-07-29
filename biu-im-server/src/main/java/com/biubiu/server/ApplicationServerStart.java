package com.biubiu.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.biubiu.server.config.IMServerConfiguation;

/**
 * 服务端启动
 */
@SpringBootApplication(scanBasePackages = { "com.biubiu" })
public class ApplicationServerStart {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(ApplicationServerStart.class, args);
		IMServerConfiguation imConf = applicationContext.getBean(IMServerConfiguation.class);
		if (!imConf.checkConfig()) {
			applicationContext.close();
		}
	}
}
