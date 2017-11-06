package com.cxytiandi.open.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import com.cxytiandi.open.api.listener.ApplicationListenerStarted;

/**
 * API 网关程序入口
 * @author yinjihuan
 *
 */
@SpringBootApplication
@EnableScheduling
@EnableCaching
@ComponentScan(basePackages={"com.cxytiandi"})
public class OpenApiApplication {
	
	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(OpenApiApplication.class);
		application.addListeners(new ApplicationListenerStarted());
		application.run(args);
	}
	
}
