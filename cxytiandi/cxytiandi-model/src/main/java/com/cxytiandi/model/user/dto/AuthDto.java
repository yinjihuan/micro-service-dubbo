package com.cxytiandi.model.user.dto;

import com.cxytiandi.common.base.ResponseData;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * API调用认证
 * @author yinjihuan
 *
 */
@ApiModel(value = "com.cxytiandi.model.user.dto.AuthDto", description = "API调用认证")
public class AuthDto extends ResponseData {
	@ApiModelProperty(value = "认证通过的TOKEN")
	private String data;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
