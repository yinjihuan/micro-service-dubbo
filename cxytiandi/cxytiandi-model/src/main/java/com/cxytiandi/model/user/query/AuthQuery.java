package com.cxytiandi.model.user.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * API用户认证参数类
 * @author yinjihuan
 *
 */
@ApiModel(value = "com.cxytiandi.model.user.query.AuthQuery", description = "API用户认证参数类")
public class AuthQuery {
	@ApiModelProperty(value = "AK", required = true)
	private String accessKey;
	
	@ApiModelProperty(value = "SK", required = true)
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
