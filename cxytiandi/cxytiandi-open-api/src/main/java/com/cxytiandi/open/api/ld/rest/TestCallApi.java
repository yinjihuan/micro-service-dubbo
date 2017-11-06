package com.cxytiandi.open.api.ld.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cxytiandi.common.base.ResponseData;
import com.cxytiandi.common.util.RestApiCallUtils;
import com.cxytiandi.open.api.anno.NoAuth;
import com.cxytiandi.open.api.ld.remote.LdRemoteApi;

@RestController
public class TestCallApi {
	
	private LdRemoteApi ldService = RestApiCallUtils.getRestClient(LdRemoteApi.class);
	
	@GetMapping("/call")
	@NoAuth
	public ResponseData call() {
		return ResponseData.ok(ldService.count());
	}
	
}
