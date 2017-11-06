package com.cxytiandi.open.api.ld.service;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.cxytiandi.api.ld.LdRpcService;
import com.cxytiandi.model.ld.bo.LouDongBo;
import com.cxytiandi.model.ld.po.LouDong;
import com.cxytiandi.trace.common.LogUtils;
import com.cxytiandi.common.base.Constants;
import com.cxytiandi.common.util.CxytiandiBeanUtils;

@Component
@Service(interfaceClass = LdRestService.class)
public class LdRestServiceImpl implements LdRestService {
	private static LogUtils logUtils = LogUtils.getLogger(LdRestServiceImpl.class);
	
	@Reference(interfaceClass = LdRpcService.class, version = Constants.V1, check = false, mock = "true")
	private LdRpcService ldRpcService;

	//@Cacheable(value = "c.c.o.a.l.s.LdRestService.count##600", keyGenerator = "wiselyKeyGenerator")
	public long count() {
		long count = ldRpcService.count();
		System.err.println(count);
		return count;
	}

	@Cacheable(value = "c.c.o.a.l.s.LdRestService.findAll##60", keyGenerator = "wiselyKeyGenerator")
	public List<LouDongBo> findAll() {
		System.out.println("findAll");
		List<LouDong> list = ldRpcService.findAll();
		return CxytiandiBeanUtils.copyProperties(list, LouDongBo.class);
	}

	//@Cacheable(value = "c.c.o.a.l.s.LdRestService.find##60", keyGenerator = "wiselyKeyGenerator")
	public List<LouDongBo> find(String city) {
		logUtils.error("dsd");
		List<LouDong> list = ldRpcService.find(city);
		return CxytiandiBeanUtils.copyProperties(list, LouDongBo.class);
	}

	public List<LouDongBo> find(String city, String region) {
		List<LouDong> list = ldRpcService.find(city, region);
		return CxytiandiBeanUtils.copyProperties(list, LouDongBo.class);
	}

	public List<LouDongBo> find(String city, String region, String name) {
		List<LouDong> list = ldRpcService.find(city, region, name);
		return CxytiandiBeanUtils.copyProperties(list, LouDongBo.class);
	}

}
