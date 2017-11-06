package com.cxytiandi.open.api.data;

import java.util.ArrayList;
import java.util.List;
/**
 * API白名单列表
 * @author yinjihuan
 *
 */
public class ApiWhiteData {
	private static List<String> whiteApis = new ArrayList<String>();
	
	public static List<String> getWhiteApis() {
		return whiteApis;
	}
	
	public static void add(String uri) {
		whiteApis.add(uri);
	}
}
