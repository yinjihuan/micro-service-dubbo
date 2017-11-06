package com.cxytiandi.open.api.filter;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cxytiandi.open.api.anno.ApiRateLimit;
import com.cxytiandi.trace.common.LogUtils;

/**
 * 具体API并发控制
 * @author yinjihuan
 *
 */
//@Aspect
//@Order(value = Ordered.HIGHEST_PRECEDENCE)
//@Configuration
public class ApiLimitAspect {
	private static LogUtils logUtils = LogUtils.getLogger(ApiLimitAspect.class);
	
	public static Map<String, Semaphore> semaphoreMap = new ConcurrentHashMap<String, Semaphore>();
	
	Semaphore semap = null;
	
	@Around("execution(* com.cxytiandi.open.api.*.rest.*.*(..))")
	public Object twiceAsOld(ProceedingJoinPoint joinPoint) {
		long start = System.currentTimeMillis();
		Object result = null;
		Class<?> clazz = joinPoint.getTarget().getClass();
		Map<String, String> map = isRateLimit(clazz, joinPoint.getSignature().getName(), joinPoint.getArgs());
		String key = map.get("key");
		String uri = getUri(clazz) + map.get("uri");
		if (key != null) {
			semap = semaphoreMap.get(key);
		} else {
			semap = semaphoreMap.get("open.api.defaultLimit");
		}
		try {
			semap.acquire();
			result = joinPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			semap.release();
			long end = System.currentTimeMillis();
			logUtils.info(uri + "##" + (end - start));
		}
		return result;
	}
	
	private Map<String, String> isRateLimit(Class<?> clazz, String methodName, Object[] args) {
		Map<String, String> map = new HashMap<String, String>();
		for (Method method : clazz.getDeclaredMethods()) {
			if(method.getName().equals(methodName) && paramMath(method, args)){
				if (method.isAnnotationPresent(ApiRateLimit.class)) {
					String key = method.getAnnotation(ApiRateLimit.class).confKey();
					map.put("key", key);
				}
				map.put("uri", getUri(method));
			}
		}
		return map;
	}
	
	private String getUri(Method method) {
		if (method.isAnnotationPresent(RequestMapping.class)) {
			return method.getAnnotation(RequestMapping.class).value()[0];
		}
		if (method.isAnnotationPresent(PostMapping.class)) {
			return method.getAnnotation(PostMapping.class).value()[0];
		}
		if (method.isAnnotationPresent(GetMapping.class)) {
			return method.getAnnotation(GetMapping.class).value()[0];
		}
		if (method.isAnnotationPresent(PostMapping.class)) {
			return method.getAnnotation(PostMapping.class).value()[0];
		}
		return null;
	}
	
	private String getUri(Class<?> clazz) {
		if (clazz.isAnnotationPresent(RequestMapping.class)) {
			return clazz.getAnnotation(RequestMapping.class).value()[0];
		}
		if (clazz.isAnnotationPresent(PostMapping.class)) {
			return clazz.getAnnotation(PostMapping.class).value()[0];
		}
		if (clazz.isAnnotationPresent(GetMapping.class)) {
			return clazz.getAnnotation(GetMapping.class).value()[0];
		}
		if (clazz.isAnnotationPresent(PostMapping.class)) {
			return clazz.getAnnotation(PostMapping.class).value()[0];
		}
		return null;
	}
	
	/**
	 * 进行参数匹配，防止有重载的方法会影响到配置
	 * @param method
	 * @param args
	 * @return
	 */
	private boolean paramMath(Method method, Object[] args) {
		Class<?>[] params = method.getParameterTypes();
		if (params == null && args == null) {
			return true;
		} else if (params != null && args != null) {
			if (params.length != args.length) {
				return false;
			}
			boolean flag = true;
			for (int i = 0; i < params.length; i++) {
				if (!params[i].getName().equals(args[i].getClass().getName())) {
					flag = false;
					break;
				}
			}
			return flag;
		} 
		return false;
	}
}
