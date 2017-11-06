package com.cxytiandi.common.util;

import com.cxytiandi.common.base.ResponseData;
import com.cxytiandi.common.jackson.JacksonDecoder;
import com.cxytiandi.common.jackson.JacksonEncoder;

import feign.Feign;
import feign.Logger;
import feign.RequestInterceptor;
import feign.RequestTemplate;

/**
 * Rest API 调用工具类
 * 
 * @author yinjihuan
 *
 */
public class RestApiCallUtils {
	private static AuthRemoteService authRemoteService = getRestClient(AuthRemoteService.class);
	private static volatile String TOKEN = null;

	/**
	 * 获取API 接口代理对象
	 * @param apiType  接口类，比如LouDongRemoteService.class
	 * @return
	 */
	public static <T> T getRestClient(Class<T> apiType) {
		if (System.getProperty("open.api.url") == null) {
			throw new RuntimeException("请在smconf中配置open.api.url");
		}
		return Feign.builder().encoder(new JacksonEncoder()).decoder(new JacksonDecoder()).client(new ApacheHttpClient())
				.requestInterceptor(requestTokenBearerInterceptor())
				.logger(new Logger.JavaLogger().appendToFile("logs/http.log"))
                .logLevel(Logger.Level.FULL)
				.target(apiType, System.getProperty("open.api.url"));
	}

	/**
	 * 在调用接口前设置请求头
	 * @return
	 */
	public static RequestInterceptor requestTokenBearerInterceptor() {
		return new RequestInterceptor() {
			public void apply(RequestTemplate requestTemplate) {
				System.out.println(requestTemplate.toString());
				if(!requestTemplate.url().equals("/rest/auth") && TOKEN == null) {
					loadToken();
				}
				requestTemplate.header("Authorization", TOKEN);
			}
		};
	}

	/**
	 * 加载Token 加载异常或者失败，反复加载，直到成功为止
	 */
	public synchronized static boolean loadToken() {
		System.out.println("申请TOKEN");
		try {
			AuthQuery query = new AuthQuery();
			query.setAccessKey(System.getProperty("open.api.accessKey"));
			query.setSecretKey(System.getProperty("open.api.secretKey"));
			ResponseData responseData = authRemoteService.auth(query);
			if (responseData.getStatus()) {
				TOKEN = responseData.getData().toString();
				System.out.println(TOKEN);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
