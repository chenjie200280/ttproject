package com.ttsofts.mapper;

import java.util.List;
import java.util.Map;

public interface BaseInterfaceMapper<Entity> {

	public int add(Entity entity) ;
	
	public int update(Entity entity);

	public int deleteById(String id);
	
	public Entity findById(String id);
	
	public List<Entity> findByAll();
	
	public List<Entity> findByMap(Map<String, Object> map);
	
	public Integer getCount(Map<String, Object> map);
	
	public List<Entity> findByPage(Map<String, Object> map);
	
	public List<Entity> findByName(Map<String, Object> map) ;
}