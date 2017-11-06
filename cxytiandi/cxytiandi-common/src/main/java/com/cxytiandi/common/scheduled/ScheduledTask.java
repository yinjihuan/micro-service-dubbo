package com.cxytiandi.common.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cxytiandi.common.util.RestApiCallUtils;

@Component
public class ScheduledTask {
	public final static long ONE_Minute = 60 * 1000 * 60 * 20;

	/**
	 * 刷新Token
	 */
	@Scheduled(fixedDelay = ONE_Minute)
	public void relaodAPIToken() {
		while (!RestApiCallUtils.loadToken()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
	}
}
