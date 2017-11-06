package com.cxytiandi.ld.service.rpc;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.cxytiandi.api.house.HouseRpcService;
import com.cxytiandi.api.ld.LdRpcService;
import com.cxytiandi.ld.service.service.LdService;
import com.cxytiandi.model.ld.po.LouDong;
import com.cxytiandi.trace.common.TraceUtils;
import com.cxytiandi.common.base.Constants;

@Component("LdRpcService")
@Service(interfaceClass = LdRpcService.class, version = Constants.V1, timeout = 5000)
public class LdRpcServiceImpl implements LdRpcService {
	
	@Reference(interfaceClass = HouseRpcService.class, version = Constants.V1, check = false, mock = "true")
	private HouseRpcService houseRpcService;
	
	@Autowired
	private LdService ldService;
	
	public long count() {
		long c = ldService.count();
		return c;
	}

	public List<LouDong> findAll() {
		System.err.println(TraceUtils.getTraceInfo());
		return ldService.findAll();
	}

	public List<LouDong> find(String city) {
		System.err.println(houseRpcService.count());
		return ldService.find(city);
	}

	public List<LouDong> find(String city, String region) {
		return ldService.find(city, region);
	}

	public List<LouDong> find(String city, String region, String name) {
		return ldService.find(city, region, name);
	}

	public String save(LouDong louDong) {
		ldService.save(louDong);
		return louDong.getId();
	}

}
