package com.cxytiandi.open.api.config;

import java.util.ArrayList;
import java.util.List;
import org.cxytiandi.conf.client.init.ConfInit;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cxytiandi.open.api.filter.HTTPBasicAuthorizeFilter;
import com.cxytiandi.trace.filter.HTTPFilter;

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
	
	@Bean  
    public FilterRegistrationBean  filterRegistrationBean() {  
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();  
        HTTPFilter httpBasicFilter = new HTTPFilter();  
        registrationBean.setFilter(httpBasicFilter);  
        List<String> urlPatterns = new ArrayList<String>(1);  
        urlPatterns.add("/rest/*");  
        registrationBean.setUrlPatterns(urlPatterns);  
        return registrationBean;  
    }
	
	@Bean  
    public FilterRegistrationBean  filterRegistrationBean2() {  
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();  
        HTTPBasicAuthorizeFilter httpBasicFilter = new HTTPBasicAuthorizeFilter();  
        registrationBean.setFilter(httpBasicFilter);  
        List<String> urlPatterns = new ArrayList<String>(1);  
        urlPatterns.add("/rest/*");  
        registrationBean.setUrlPatterns(urlPatterns);  
        return registrationBean;  
    }
	
}
