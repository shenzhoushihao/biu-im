package com.biubiu.route;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.biubiu.route.listener.RegistryServerCenterListener;

/**
 * @author: roc
 * @description: route start
 */
@SpringBootApplication(scanBasePackages = { "com.biubiu.route" })
public class RouteApplicationStart implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(RouteApplicationStart.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// server listener
		Thread thread = new Thread(new RegistryServerCenterListener());
		thread.setName("server-register-listener");
		thread.start();
	}
}
