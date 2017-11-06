package com.cxytiandi.house.service;

import java.util.concurrent.CountDownLatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.cxytiandi.house.service.listener.ApplicationListenerStarted;

/**
 * 房号服务启动入口
 * @author yinjihuan
 *
 */
@SpringBootApplication
@EnableTransactionManagement
@ComponentScan(basePackages={"com.cxytiandi"})
public class HouseApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(HouseApplication.class);
	
	public static void main(String[] args) {
		new SpringApplicationBuilder().sources(HouseApplication.class).web(false).listeners(new ApplicationListenerStarted()).run(args);
        try {
        	 new CountDownLatch(1).await();
		} catch (InterruptedException e) {
			LOGGER.error("项目启动异常", e);
		}
	}
}
