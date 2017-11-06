package com.cxytiandi.open.api.filter;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.cxytiandi.open.api.data.ApiWhiteData;
import com.cxytiandi.common.base.ResponseCode;
import com.cxytiandi.common.base.ResponseData;
import com.cxytiandi.common.util.JWTUtils;
import com.cxytiandi.common.util.JsonUtils;
import com.google.common.util.concurrent.RateLimiter;

/**
 * API 调用权限控制
 * @author yinjihuan
 *
 */
public class HTTPBasicAuthorizeFilter implements Filter {
	public static volatile RateLimiter rateLimiter = RateLimiter.create(Double.parseDouble(System.getProperty("permitsPerSecond")));
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		ServletContext context = filterConfig.getServletContext();  
        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);  
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;  
        httpResponse.setCharacterEncoding("UTF-8");    
        httpResponse.setContentType("application/json; charset=utf-8"); 
        String auth = httpRequest.getHeader("Authorization");
        System.err.println("-----------");
        //白名单，放过
        /*if (ApiWhiteData.getWhiteApis().contains(httpRequest.getRequestURI())) {
        	chain.doFilter(httpRequest, response);
        	return;  
		}
        
        //验证TOKEN
        if (!StringUtils.hasText(auth)) {
			PrintWriter print = httpResponse.getWriter();
        	print.write(JsonUtils.toJson(ResponseData.fail("非法请求【缺少Authorization信息】", ResponseCode.NO_AUTH_CODE.getCode())));  
            return;  
		}
        JWTUtils.JWTResult jwt = JWTUtils.checkToken(auth);
		if (!jwt.isStatus()) {
			PrintWriter print = httpResponse.getWriter();
			print.write(JsonUtils.toJson(ResponseData.fail(jwt.getMsg(), jwt.getCode())));  
			return;
		}*/
		
		//总体限流
		rateLimiter.acquire();
		
		chain.doFilter(httpRequest, response);
	}
	
	@Override
	public void destroy() {
		
	}

}
