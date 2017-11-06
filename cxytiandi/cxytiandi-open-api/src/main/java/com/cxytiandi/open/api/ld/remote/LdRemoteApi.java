package com.cxytiandi.open.api.ld.remote;

import com.cxytiandi.common.base.ResponseData;
import feign.RequestLine;

public interface LdRemoteApi {
	
	@RequestLine("GET /rest/ld/count")
	public ResponseData count();
	
}
