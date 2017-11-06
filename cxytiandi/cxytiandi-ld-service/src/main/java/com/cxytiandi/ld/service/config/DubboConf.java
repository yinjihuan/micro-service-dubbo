package com.cxytiandi.ld.service.config;

import org.cxytiandi.conf.client.annotation.ConfField;
import org.cxytiandi.conf.client.annotation.CxytianDiConf;
import org.cxytiandi.conf.client.core.SmconfUpdateCallBack;
import org.cxytiandi.conf.client.core.rest.Conf;
import com.cxytiandi.common.base.Constants;


/**
 * dubbo配置信息
 * @author yinjihuan
 *
 */
@CxytianDiConf(system = Constants.FJ_LD_SYS_NAME, env = true, prefix = "spring.dubbo")
public class DubboConf implements SmconfUpdateCallBack {
	
	@ConfField("zookeeper地址")
	private String registryAddress = "zookeeper://localhost:2181";
	
	@ConfField("dubbo服务名称")
	private String applicationName = Constants.FJ_LD_SYS_NAME;
	
	@ConfField("dubbo暴露协议")
	private String protocolName = "dubbo";
	
	@ConfField("dubbo暴露端口")
	private Integer protocolPort = 20881;

	public String getRegistryAddress() {
		return registryAddress;
	}

	public void setRegistryAddress(String registryAddress) {
		this.registryAddress = registryAddress;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getProtocolName() {
		return protocolName;
	}

	public void setProtocolName(String protocolName) {
		this.protocolName = protocolName;
	}

	public Integer getProtocolPort() {
		return protocolPort;
	}

	public void setProtocolPort(Integer protocolPort) {
		this.protocolPort = protocolPort;
	}

	@Override
	public void reload(Conf conf) {
		System.out.println("--------------");
		System.out.println(this.protocolPort);
	}
	
}
