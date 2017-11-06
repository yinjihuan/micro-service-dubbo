package com.cxytiandi.api.ld;

import com.cxytiandi.model.ld.dto.LouDongDto;

import feign.Param;
import feign.RequestLine;

public interface LouDongRestService {
	
	@RequestLine("GET /rest/ld/list/{city}")
	public LouDongDto list(@Param("city") String city);
	
}
