package com.cxytiandi.api.ld;

import java.util.List;

import com.cxytiandi.model.ld.po.LouDong;

/**
 * 楼栋接口
 * @author yinjihuan
 *
 */
public interface LdRpcService {
	
	long count();
	
	List<LouDong> findAll();
	
	List<LouDong> find(String city);
	
	List<LouDong> find(String city, String region);
	
	List<LouDong> find(String city, String region, String name);
	
	String save(LouDong louDong);
}
