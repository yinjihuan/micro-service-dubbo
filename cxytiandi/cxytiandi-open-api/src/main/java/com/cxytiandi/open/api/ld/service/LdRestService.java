package com.cxytiandi.open.api.ld.service;

import java.util.List;

import com.cxytiandi.model.ld.bo.LouDongBo;

public interface LdRestService {
	
	long count();
	
	List<LouDongBo> findAll();
	
	List<LouDongBo> find(String city);
	
	List<LouDongBo> find(String city, String region);
	
	List<LouDongBo> find(String city, String region, String name);
	
}
