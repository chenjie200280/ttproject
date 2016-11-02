package com.ttsofts.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppUtil {
	
	public static  List<Map<String,String>> tokens = null;
	
	public static Map<String,String> appAdvertSdks = null;
	
	public static Map<String,String> appAdvertLocations = null;
	
	static{
		//tokens认证
		tokens = new ArrayList<Map<String,String>>();
		
		//广告SDK
		appAdvertSdks = new HashMap<String,String>();
		appAdvertSdks.put("baidu", "百度");
		appAdvertSdks.put("qq", "腾讯");
		
		//广告位置
		appAdvertLocations = new HashMap<String,String>();
		appAdvertLocations.put("bannerAd", "横幅");
		appAdvertLocations.put("interAd", "插屏");
	}
	
	
}
