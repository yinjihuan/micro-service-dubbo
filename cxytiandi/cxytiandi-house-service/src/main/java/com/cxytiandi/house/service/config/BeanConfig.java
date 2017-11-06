package com.cxytiandi.house.service.config;

import org.cxytiandi.conf.client.init.ConfInit;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.cxytiandi.jdbc.CxytiandiJdbcTemplate;

@Configuration
public class BeanConfig {
	
	/**
	 * 启动Smconf配置客户端
	 * @return
	 */
	@Bean
	public ConfInit confInit() {
	    return new ConfInit();
	}
	
	/**
	 * JDBC
	 * @return
	 */
	@Bean(autowire=Autowire.BY_NAME)
	public CxytiandiJdbcTemplate cxytiandiJdbcTemplate() {
		return new CxytiandiJdbcTemplate("com.cxytiandi.model.ld.po");
	}
	
}
