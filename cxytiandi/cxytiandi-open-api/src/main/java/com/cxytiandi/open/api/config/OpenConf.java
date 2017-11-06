package com.cxytiandi.open.api.config;

import org.cxytiandi.conf.client.annotation.ConfField;
import org.cxytiandi.conf.client.annotation.CxytianDiConf;
import com.cxytiandi.common.base.Constants;

/**
 * 基础配置信息
 * @author yinjihuan
 *
 */
@CxytianDiConf(system = Constants.FJ_OPEN_SYS_NAME, env = true, prefix = "open.api")
public class OpenConf {
	@ConfField("API URL")
	private String url = "http://localhost:8083";
	
	@ConfField("accessKey")
	private String accessKey = "1";
	
	@ConfField("secretKey")
	private String secretKey = "1";
	
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
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUrl() {
		return url;
	}
}
