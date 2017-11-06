package com.cxytiandi.open.api.config;

import org.cxytiandi.conf.client.annotation.ConfField;
import org.cxytiandi.conf.client.annotation.CxytianDiConf;

import com.cxytiandi.common.base.Constants;

/**
 * dubbo配置信息
 * @author yinjihuan
 *
 */
@CxytianDiConf(system = Constants.FJ_OPEN_SYS_NAME, env = true, prefix = "spring.dubbo")
public class DubboConf {
	
	@ConfField("zookeeper地址")
	private String registryAddress = "zookeeper://localhost:2181";
	
	@ConfField("dubbo服务名称")
	private String applicationName = Constants.FJ_OPEN_SYS_NAME;
	
	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	
	public String getRegistryAddress() {
		return registryAddress;
	}

	public void setRegistryAddress(String registryAddress) {
		this.registryAddress = registryAddress;
	}

}
