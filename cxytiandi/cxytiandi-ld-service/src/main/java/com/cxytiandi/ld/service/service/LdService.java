package com.cxytiandi.ld.service.service;

import java.util.List;
import com.cxytiandi.jdbc.PageQueryParam;
import com.cxytiandi.model.ld.bdo.LouDongDo;
import com.cxytiandi.model.ld.po.LouDong;

public interface LdService {
	
	long count();
	
	List<LouDong> findAll();
	
	List<LouDong> find(String city);
	
	List<LouDong> find(String city, String region);
	
	List<LouDong> find(String city, String region, String name);
	
	List<LouDong> findAll(PageQueryParam page);
	
	boolean exists(String city);
	
	List<LouDong> in(String[] names);
	
	List<LouDongDo> group();
	
	LouDong get(String id);
	
	void delete(String name);
	
	void save(LouDong louDong);
	
	void saveList(List<LouDong> list);
	
	void update(LouDong louDong);
	
	void updateList(List<LouDong> list);
}
