package com.ttsofts.service.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ttsofts.entity.app.AppAdvert;
import com.ttsofts.entity.app.AppAdvertSdk;
import com.ttsofts.mapper.app.AppAdvertMapper;
import com.ttsofts.mapper.app.AppAdvertSdkMapper;
import com.ttsofts.service.BaseInterfaceService;

public class AppAdvertService implements BaseInterfaceService<AppAdvert> {

	private AppAdvertMapper appAdvertMapper;
	
	private AppAdvertSdkMapper appAdvertSdkMapper;

	@Override
	public List<AppAdvert> findByAll() {
		// TODO Auto-generated method stub
		return appAdvertMapper.findByAll();
	}
	
	@Override
	public List<AppAdvert> findByMap(Map<String,Object> map)
	{
		List<AppAdvert> resultList = new ArrayList<AppAdvert>();
		List<AppAdvert> list = appAdvertMapper.findByMap(map);
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("app_info_id",map.get("app_info_id"));
		List<AppAdvertSdk> appAdvertSdks = appAdvertSdkMapper.findByMap(paramMap);
		for (AppAdvert appAdvert : list) {
			for (AppAdvertSdk appAdvertSdk : appAdvertSdks) {
				if(appAdvertSdk.getId().equals(appAdvert.getSdkid())){
					appAdvert.setSdk(appAdvertSdk.getSdk());
					resultList.add(appAdvert);
					break;
				}
			}
		}
		return resultList;
	}

	public Map<String, Object> init(Map<String,Object> map) {
		Map<String, Object> resultMap = null;
		//获取所有广告信息
		List<AppAdvert> list = appAdvertMapper.findByMap(map);
		if (list!=null && list.size() > 0) {
			resultMap = new HashMap<String, Object>();
			resultMap.put("app_info_id",map.get("app_info_id"));
			for (AppAdvert appAdvert : list) {
				AppAdvertSdk appAdvertSdk = appAdvertSdkMapper.findById(appAdvert.getSdkid());
				Map<String, Object> advert = new HashMap<String, Object>();
				advert.put("sdk", appAdvertSdk.getSdk());
				advert.put("appid", appAdvertSdk.getAppid());
				advert.put("adid", appAdvertSdk.getAdid());
				advert.put("status", appAdvert.getStatus());
				resultMap.put(appAdvert.getLocation(), advert);
			}
		}
		return resultMap;
	}

	
	@Override
	public boolean deleteById(String id) {
		// TODO Auto-generated method stub
		appAdvertMapper.deleteById(id);
		return true;
	}

	@Override
	public boolean add(AppAdvert entity) {
		// TODO Auto-generated method stub
		appAdvertMapper.add(entity);
		return true;
	}

	@Override
	public boolean update(AppAdvert entity) {
		// TODO Auto-generated method stub
		appAdvertMapper.update(entity);
		return true;
	}
	
	public void setAppAdvertMapper(AppAdvertMapper appAdvertMapper) {
		this.appAdvertMapper = appAdvertMapper;
	}
	
	public void setAppAdvertSdkMapper(AppAdvertSdkMapper appAdvertSdkMapper) {
		this.appAdvertSdkMapper = appAdvertSdkMapper;
	}

}
