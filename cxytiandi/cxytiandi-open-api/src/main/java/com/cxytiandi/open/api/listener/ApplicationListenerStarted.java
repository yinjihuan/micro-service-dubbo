package com.cxytiandi.open.api.listener;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.Semaphore;
import org.cxytiandi.conf.client.init.SmconfInit;
import org.cxytiandi.conf.client.util.ClasspathPackageScannerUtils;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cxytiandi.open.api.anno.ApiRateLimit;
import com.cxytiandi.open.api.anno.NoAuth;
import com.cxytiandi.open.api.data.ApiWhiteData;
import com.cxytiandi.open.api.filter.ApiLimitAspect;
/**
 * spring boot启动开始时执行的事件
 * @author yinjihuan
 *
 */
public class ApplicationListenerStarted implements ApplicationListener<ApplicationStartedEvent> {

	public void onApplicationEvent(ApplicationStartedEvent arg0) {
		//启动时需要配置来做连接，需要在spring启动前将一些配置信息加载到环境变量使用
		SmconfInit.init("com.cxytiandi.open.api.config");
		try {
			initNoAuthApi();
		} catch (Exception e) {
			throw new RuntimeException("初始化不需要认证的API列表异常", e);
		}
		
		try {
			initLimitRateAPI();
		} catch (Exception e) {
			throw new RuntimeException("初始化需要进行并发限制的API异常", e);
		}
	}
	
	/**
	 * 初始化需要进行并发限制的API
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void initLimitRateAPI() throws IOException, ClassNotFoundException {
		ApiLimitAspect.semaphoreMap.put("open.api.defaultLimit", new Semaphore(Integer.parseInt(System.getProperty("open.api.defaultLimit"))));
		ClasspathPackageScannerUtils scan = new ClasspathPackageScannerUtils("com.cxytiandi.open.api");
		List<String> classList = scan.getFullyQualifiedClassNameList();
		for (String clazz : classList) {
			Class<?> clz = Class.forName(clazz);
			if (!clz.isAnnotationPresent(RestController.class)) {
				continue;
			}
			Method[] methods = clz.getDeclaredMethods();
			for (Method method : methods) {
				if (method.isAnnotationPresent(ApiRateLimit.class)) {
					String confKey = method.getAnnotation(ApiRateLimit.class).confKey();
					int limit = Integer.parseInt(System.getProperty(confKey));
					System.out.println(confKey + "\t" + limit);
					ApiLimitAspect.semaphoreMap.put(confKey, new Semaphore(limit));
				}
			}
		}
	}

	
	/**
	 * 初始化不需要认证的API列表
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void initNoAuthApi() throws IOException, ClassNotFoundException {
		ClasspathPackageScannerUtils scan = new ClasspathPackageScannerUtils("com.cxytiandi.open.api");
		List<String> classList = scan.getFullyQualifiedClassNameList();
		for (String clazz : classList) {
			String basePath = "";
			Class<?> clz = Class.forName(clazz);
			if (!clz.isAnnotationPresent(RestController.class)) {
				continue;
			}
			if (clz.isAnnotationPresent(RequestMapping.class)) {
				basePath = clz.getAnnotation(RequestMapping.class).value()[0];
			}
			Method[] methods = clz.getDeclaredMethods();
			for (Method method : methods) {
				if (method.isAnnotationPresent(NoAuth.class) && method.isAnnotationPresent(RequestMapping.class)) {
					String[] values = method.getAnnotation(RequestMapping.class).value();
					String value = values.length > 0 ? values[0] : "";
					String uri = basePath + value;
					ApiWhiteData.add(uri);
				}
				if (method.isAnnotationPresent(NoAuth.class) && method.isAnnotationPresent(GetMapping.class)) {
					String[] values = method.getAnnotation(GetMapping.class).value();
					String value = values.length > 0 ? values[0] : "";
					String uri = basePath + value;
					ApiWhiteData.add(uri);
				}
				if (method.isAnnotationPresent(NoAuth.class) && method.isAnnotationPresent(PostMapping.class)) {
					String[] values = method.getAnnotation(PostMapping.class).value();
					String value = values.length > 0 ? values[0] : "";
					String uri = basePath + value;
					ApiWhiteData.add(uri);
				}
			}
		}
	}


}
