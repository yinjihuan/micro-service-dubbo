package com.cxytiandi.open.api.config;

import org.cxytiandi.conf.client.annotation.ConfField;
import org.cxytiandi.conf.client.annotation.CxytianDiConf;
import org.cxytiandi.conf.client.core.SmconfUpdateCallBack;
import org.cxytiandi.conf.client.core.rest.Conf;

import com.cxytiandi.open.api.filter.HTTPBasicAuthorizeFilter;
import com.cxytiandi.common.base.Constants;
import com.google.common.util.concurrent.RateLimiter;

@CxytianDiConf(system = Constants.FJ_OPEN_SYS_NAME, env = true)
public class RateLimiterConf implements SmconfUpdateCallBack {
	
	@ConfField("QPS并发量")
	private double permitsPerSecond = 10;

	public double getPermitsPerSecond() {
		return permitsPerSecond;
	}

	public void setPermitsPerSecond(double permitsPerSecond) {
		this.permitsPerSecond = permitsPerSecond;
	}

	public void reload(Conf conf) {
		HTTPBasicAuthorizeFilter.rateLimiter = RateLimiter.create(this.permitsPerSecond);
	}
	
}
