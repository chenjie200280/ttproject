package com.ttsofts.service.fav;

import java.util.List;
import java.util.Map;

import com.ttsofts.entity.fav.FavInfo;
import com.ttsofts.mapper.fav.FavInfoMapper;
import com.ttsofts.service.BaseInterfaceService;

public class FavInfoService implements BaseInterfaceService<FavInfo> {
	private FavInfoMapper favInfoMapper;

	@Override
	public boolean add(FavInfo entity) {
		// TODO Auto-generated method stub
		favInfoMapper.add(entity);
		return true;
	}

	@Override
	public boolean update(FavInfo entity) {
		// TODO Auto-generated method stub
		favInfoMapper.update(entity);
		return true;
	}

	@Override
	public List<FavInfo> findByAll() {
		// TODO Auto-generated method stub
		return favInfoMapper.findByAll();
	}

	public void setfavInfoMapper(FavInfoMapper favInfoMapper) {
		this.favInfoMapper = favInfoMapper;
	}

	@Override
	public List<FavInfo> findByMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return favInfoMapper.findByMap(map);
	}

	@Override
	public boolean deleteById(String id) {
		// TODO Auto-generated method stub
		favInfoMapper.deleteById(id);
		return true;
	}

}
