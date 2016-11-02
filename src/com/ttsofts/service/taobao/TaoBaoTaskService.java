package com.ttsofts.service.taobao;

import java.util.List;
import java.util.Map;

import com.ttsofts.entity.taobao.TaoBaoTask;
import com.ttsofts.mapper.taobao.TaoBaoTaskMapper;
import com.ttsofts.service.BaseInterfaceService;

public class TaoBaoTaskService implements BaseInterfaceService<TaoBaoTask> {

	private TaoBaoTaskMapper taoBaoTaskMapper;

	@Override
	public List<TaoBaoTask> findByAll() {
		// TODO Auto-generated method stub
		return taoBaoTaskMapper.findByAll();
	}

	@Override
	public boolean add(TaoBaoTask entity) {
		// TODO Auto-generated method stub
		taoBaoTaskMapper.add(entity);
		return true;
	}

	@Override
	public boolean update(TaoBaoTask entity) {
		// TODO Auto-generated method stub
		taoBaoTaskMapper.update(entity);
		return true;
	}

	public void setTaoBaoTaskMapper(TaoBaoTaskMapper taoBaoTaskMapper) {
		this.taoBaoTaskMapper = taoBaoTaskMapper;
	}

	@Override
	public List<TaoBaoTask> findByMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteById(String id) {
		// TODO Auto-generated method stub
		taoBaoTaskMapper.deleteById(id);
		return true;
	}

}
