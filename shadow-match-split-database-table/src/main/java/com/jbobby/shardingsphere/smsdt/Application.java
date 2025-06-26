package com.jbobby.shardingsphere.smsdt;

import com.jbobby.shardingsphere.smsdt.service.ShadowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {
	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

		ShadowService shadowService = context.getBean(ShadowService.class);
		LOGGER.info("Start to clean environment");
		shadowService.cleanEnvironment();
		LOGGER.info("Start to init environment");
		shadowService.initEnvironment();
	}
}
