package com.cxytiandi.model.ld.dto;

import java.util.List;

import com.cxytiandi.common.base.ResponseData;
import com.cxytiandi.model.ld.bo.LouDongBo;

import io.swagger.annotations.ApiModel;
/**
 * 楼栋列表信息
 * @author yinjihuan
 *
 */
@ApiModel(value = "com.cxytiandi.model.ld.dto.LouDongDto", description = "楼栋列表信息")
public class LouDongDto extends ResponseData {
	private List<LouDongBo> data;

	public List<LouDongBo> getData() {
		return data;
	}

	public void setData(List<LouDongBo> data) {
		this.data = data;
	}
}
