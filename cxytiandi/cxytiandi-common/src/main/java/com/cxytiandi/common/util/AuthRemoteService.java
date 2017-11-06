package com.cxytiandi.common.util;

import com.cxytiandi.common.base.ResponseData;

import feign.Headers;
import feign.RequestLine;

@Headers("Content-Type: application/json")
public interface AuthRemoteService {
	
	@RequestLine("POST /rest/auth")
	public ResponseData auth(AuthQuery query);
	
}


class AuthQuery {
	private String accessKey;
	private String secretKey;
	public String getAccessKey() {
		return accessKey;
	}
	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}
	public String getSecretKey() {
		return secretKey;
	}
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
}