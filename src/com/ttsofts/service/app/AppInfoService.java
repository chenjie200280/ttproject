package com.ttsofts.service.app;

import java.util.List;
import java.util.Map;

import com.ttsofts.entity.app.AppInfo;
import com.ttsofts.mapper.app.AppInfoMapper;
import com.ttsofts.service.BaseInterfaceService;

public class AppInfoService implements BaseInterfaceService<AppInfo> {

	private AppInfoMapper appInfoMapper;
	@Override
	public List<AppInfo> findByAll() {
		// TODO Auto-generated method stub
		return appInfoMapper.findByAll();
	}
	
	public void setAppInfoMapper(AppInfoMapper appInfoMapper) {
		this.appInfoMapper = appInfoMapper;
	}

	@Override
	public boolean add(AppInfo entity) {
		// TODO Auto-generated method stub
		appInfoMapper.add(entity);
		return true;
	}

	@Override
	public boolean update(AppInfo entity) {
		// TODO Auto-generated method stub
		appInfoMapper.update(entity);
		return true;
	}

	@Override
	public List<AppInfo> findByMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return appInfoMapper.findByMap(map);
	}

	@Override
	public boolean deleteById(String id) {
		// TODO Auto-generated method stub
		appInfoMapper.deleteById(id);
		return true;
	}

	public int getCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return appInfoMapper.getCount(map);
	}

	public List<AppInfo> findByPage(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return appInfoMapper.findByPage(map);
	}
	

}
