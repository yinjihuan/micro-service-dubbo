package com.cxytiandi.api.ld;

import java.util.ArrayList;
import java.util.List;

import com.cxytiandi.model.ld.po.LouDong;

public class LdRpcServiceMock implements LdRpcService {

	public long count() {
		return 0;
	}

	public List<LouDong> findAll() {
		return new ArrayList<LouDong>();
	}

	public List<LouDong> find(String city) {
		return new ArrayList<LouDong>();
	}

	public List<LouDong> find(String city, String region) {
		return new ArrayList<LouDong>();
	}

	public List<LouDong> find(String city, String region, String name) {
		return new ArrayList<LouDong>();
	}

	public String save(LouDong louDong) {
		return null;
	}

}
