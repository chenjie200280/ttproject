package com.ttsofts.service.taobao;

import java.util.List;
import java.util.Map;

import com.ttsofts.entity.taobao.TaoBaoShop;
import com.ttsofts.mapper.taobao.TaoBaoShopMapper;
import com.ttsofts.service.BaseInterfaceService;

public class TaoBaoShopService implements BaseInterfaceService<TaoBaoShop> {
	private TaoBaoShopMapper taoBaoShopMapper;
	
	@Override
	public boolean add(TaoBaoShop entity) {
		// TODO Auto-generated method stub
		taoBaoShopMapper.add(entity);
		return true;
	}

	@Override
	public boolean update(TaoBaoShop entity) {
		// TODO Auto-generated method stub
		taoBaoShopMapper.update(entity);
		return true;
	}
	
	@Override
	public List<TaoBaoShop> findByAll() {
		// TODO Auto-generated method stub
		return taoBaoShopMapper.findByAll();
	}
	

	@Override
	public List<TaoBaoShop> findByMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return taoBaoShopMapper.findByMap(map);
	}

	public void setTaoBaoShopMapper(TaoBaoShopMapper taoBaoShopMapper) {
		this.taoBaoShopMapper = taoBaoShopMapper;
	}

	@Override
	public boolean deleteById(String id) {
		// TODO Auto-generated method stub
		return false;
	}





}
