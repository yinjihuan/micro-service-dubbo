package com.cxytiandi.ld.service.listener;

import org.cxytiandi.conf.client.init.SmconfInit;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
/**
 * spring boot启动开始时执行的事件
 * @author yinjihuan
 *
 */
public class ApplicationListenerStarted implements ApplicationListener<ApplicationStartedEvent> {

	public void onApplicationEvent(ApplicationStartedEvent arg0) {
		//启动时需要配置来做连接，需要在spring启动前将一些配置信息加载到环境变量使用
		SmconfInit.init("com.cxytiandi.ld.service.config");
	}

}
