package com.cxytiandi.model.ld.dto;

import com.cxytiandi.common.base.ResponseData;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 楼栋数量
 * @author yinjihuan
 *
 */
@ApiModel(value = "com.cxytiandi.model.ld.dto.LouDongCountDto", description = "楼栋数量")
public class LouDongCountDto extends ResponseData {
	@ApiModelProperty(value = "楼栋数量")
	private Long data;

	public Long getData() {
		return data;
	}

	public void setData(Long data) {
		this.data = data;
	}
}
