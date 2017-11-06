package com.cxytiandi.open.api.config;

import java.util.concurrent.Semaphore;
import org.cxytiandi.conf.client.annotation.ConfField;
import org.cxytiandi.conf.client.annotation.CxytianDiConf;
import org.cxytiandi.conf.client.core.SmconfUpdateCallBack;
import org.cxytiandi.conf.client.core.rest.Conf;

import com.cxytiandi.open.api.filter.ApiLimitAspect;
import com.cxytiandi.common.base.Constants;

/**
 * 接口限流配置
 * @author yinjihuan
 *
 */
@CxytianDiConf(system = Constants.FJ_OPEN_SYS_NAME, env = true, prefix = "open.api")
public class RestAPIRateLimitConf implements SmconfUpdateCallBack {
	@ConfField("默认的接口并发限制")
	private int defaultLimit = 10;
	
	@ConfField("城市下的楼栋接口并发限制")
	private int listCityLimit = 1;
	
	@ConfField("城市区域下的楼栋接口并发限制")
	private int listCityRegionLimit = 3;

	public int getListCityRegionLimit() {
		return listCityRegionLimit;
	}

	public void setListCityRegionLimit(int listCityRegionLimit) {
		this.listCityRegionLimit = listCityRegionLimit;
	}

	public int getDefaultLimit() {
		return defaultLimit;
	}

	public void setDefaultLimit(int defaultLimit) {
		this.defaultLimit = defaultLimit;
	}

	public int getListCityLimit() {
		return listCityLimit;
	}

	public void setListCityLimit(int listCityLimit) {
		this.listCityLimit = listCityLimit;
	}

	@Override
	public void reload(Conf conf) {
		ApiLimitAspect.semaphoreMap.put("open.api." + conf.getKey(), new Semaphore(Integer.parseInt(conf.getValue().toString())));
	}

}