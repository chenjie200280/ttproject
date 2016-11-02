package com.ttsofts.mapper.app;


import java.util.List;
import java.util.Map;

import com.ttsofts.entity.app.AppLog;
import com.ttsofts.entity.app.AppLogStatistics;
import com.ttsofts.mapper.BaseInterfaceMapper;


public interface AppLogMapper extends BaseInterfaceMapper<AppLog> {
	public List<AppLogStatistics> findByStatistics(Map<String, Object> map);
	public Integer getStatisticsCount(Map<String, Object> map);
	
	
}
