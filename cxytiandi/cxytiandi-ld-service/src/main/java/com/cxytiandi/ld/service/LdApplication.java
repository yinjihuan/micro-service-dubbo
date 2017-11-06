package com.cxytiandi.ld.service;

import java.util.concurrent.CountDownLatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.cxytiandi.ld.service.listener.ApplicationListenerStarted;

/**
 * 楼栋服务启动入口
 * @author yinjihuan
 *
 */
@SpringBootApplication
@EnableTransactionManagement
@EnableCaching
@ComponentScan(basePackages={"com.cxytiandi"})
public class LdApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(LdApplication.class);
	
	public static void main(String[] args) {
		new SpringApplicationBuilder().sources(LdApplication.class).web(false).listeners(new ApplicationListenerStarted()).run(args);
        try {
        	 new CountDownLatch(1).await();
		} catch (InterruptedException e) {
			LOGGER.error("项目启动异常", e);
		}
	}
}
