package com.ttsofts.service.app;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ttsofts.entity.app.AppLog;
import com.ttsofts.entity.app.AppLogStatistics;
import com.ttsofts.mapper.app.AppLogMapper;
import com.ttsofts.service.BaseInterfaceService;

public class AppLogService implements BaseInterfaceService<AppLog> {

	private AppLogMapper appLogMapper;
	@Override
	public List<AppLog> findByAll() {
		// TODO Auto-generated method stub
		return appLogMapper.findByAll();
	}
	
	public List<AppLogStatistics> findByStatistics(Map<String, Object> map){
		return appLogMapper.findByStatistics(map);
	}
	
	public int getStatisticsCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return appLogMapper.getStatisticsCount(map);
	}

	
	public void setAppLogMapper(AppLogMapper appLogMapper) {
		this.appLogMapper = appLogMapper;
	}
	@Override
	public boolean add(AppLog entity) {
		// TODO Auto-generated method stub
		Date date = new Date();
		entity.setId(String.valueOf(date.getTime()));
		entity.setAddtime(date);
		appLogMapper.add(entity);
		return true;
	}
	@Override
	public boolean update(AppLog entity) {
		// TODO Auto-generated method stub
		appLogMapper.update(entity);
		return true;
	}
	@Override
	public List<AppLog> findByMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean deleteById(String id) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
