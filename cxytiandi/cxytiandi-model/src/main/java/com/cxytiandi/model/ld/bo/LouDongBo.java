package com.cxytiandi.model.ld.bo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "com.cxytiandi.model.ld.bo.LouDongBo", description = "楼栋信息")
public class LouDongBo implements Serializable {
	private static final long serialVersionUID = -2716747078530382996L;
	
	@ApiModelProperty(value = "名称")
	private String name;
	@ApiModelProperty(value = "城市")
	private String city;
	@ApiModelProperty(value = "区域")
	private String region;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}
	
}
