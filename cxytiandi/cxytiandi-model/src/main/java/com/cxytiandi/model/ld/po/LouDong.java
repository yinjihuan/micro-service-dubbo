package com.cxytiandi.model.ld.po;

import java.io.Serializable;

import com.cxytiandi.jdbc.Orders;
import com.cxytiandi.jdbc.annotation.AutoId;
import com.cxytiandi.jdbc.annotation.Field;
import com.cxytiandi.jdbc.annotation.TableName;

@TableName(value="loudong", desc="楼栋表", author="yinjihuan")
public class LouDong implements Serializable {
	
	private static final long serialVersionUID = -6690784263770712827L;
	
	@AutoId
	@Field(value="id", desc="主键ID")
	private String id;
	
	@Field(value="name", desc="小区名称")
	private String name;
	
	@Field(value="city", desc="城市")
	private String city;
	
	@Field(value="region", desc="区域")
	private String region;
	
	@Field(value="ld_num", desc="楼栋号")
	private String ldNum;
	
	@Field(value="unit_num", desc="单元号")
	private String unitNum;

	public LouDong() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getLdNum() {
		return ldNum;
	}

	public void setLdNum(String ldNum) {
		this.ldNum = ldNum;
	}

	public String getUnitNum() {
		return unitNum;
	}

	public void setUnitNum(String unitNum) {
		this.unitNum = unitNum;
	}
	
	public final static String[] SHOW_FIELDS = new String[]{ "city", "region", "name", "ld_num" };
	
	public final static String[] QUERRY_FIELDS = new String[]{ "city", "region", "name" };
	
	public final static Orders[] ORDER_FIELDS = new Orders[] { new Orders("id", Orders.OrderyType.ASC) };
}
