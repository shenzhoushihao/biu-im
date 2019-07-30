package com.biubiu.server;

import java.net.InetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.biubiu.server.config.IMServerConfiguration;
import com.biubiu.server.thread.ServerRegistryCenter;

/**
 * @author: roc
 * @description: server start
 */
@SpringBootApplication(scanBasePackages = { "com.biubiu.server",
		"com.biubiu.route.utils", 
		"com.biubiu.route.common" })
public class ApplicationServerStart implements CommandLineRunner {

	@Autowired
	private IMServerConfiguration imConf;

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(ApplicationServerStart.class, args);
		IMServerConfiguration imConf = applicationContext.getBean(IMServerConfiguration.class);
		if (!imConf.checkConfig()) {
			applicationContext.close();
		}
	}

	@Override
	public void run(String... args) throws Exception {
		// acquire local server ip
		String address = InetAddress.getLocalHost().getHostAddress();
		Thread thread = new Thread(new ServerRegistryCenter(address, imConf.getImProtoPort(), imConf.getServerPort()));
		thread.setName("server-registry-center");
		thread.start();
	}
}
