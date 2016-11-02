package com.ttsofts.service;

import java.util.List;
import java.util.Map;

public interface BaseInterfaceService<Entity> {
	public List<Entity> findByAll();
	public boolean add(Entity entity);
	public boolean update(Entity entity);
	public boolean deleteById(String id);
	public List<Entity> findByMap(Map<String,Object> map);
}
