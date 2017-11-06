package com.cxytiandi.common.util;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;

public class CxytiandiBeanUtils {
	
	@SuppressWarnings("unchecked")
	public static <T> List<T> copyProperties(Object sources, Class<?> targetClass) {
		List<T> targets = new ArrayList<T>();
		if (sources instanceof List) {
			List<Object> list = (List<Object>) sources;
			for (Object source : list) {
				try {
					T target = (T) targetClass.newInstance();
					BeanUtils.copyProperties(source, target);
					targets.add(target);
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		
		return targets;
	}
}
