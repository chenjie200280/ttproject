package com.ttsofts.service.user;

import java.util.List;
import java.util.Map;

import com.ttsofts.entity.user.UserInfoView;
import com.ttsofts.mapper.user.UserInfoMapper;
import com.ttsofts.service.BaseInterfaceService;

public class UserInfoService implements BaseInterfaceService<UserInfoView> {
	private UserInfoMapper userInfoMapper;
	
	public UserInfoView fintByLogin(Map<String,Object> map)
	{
		List<UserInfoView> list = userInfoMapper.findByMap(map);
		if(list!=null&&list.size()>0)
		{
			return list.get(0);
		}
		else{
			return null;
		}
	}
	
	@Override
	public boolean add(UserInfoView entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(UserInfoView entity) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public List<UserInfoView> findByAll() {
		// TODO Auto-generated method stub
		return userInfoMapper.findByAll();
	}
	
	public void setUserInfoMapper(UserInfoMapper userInfoMapper) {
		this.userInfoMapper = userInfoMapper;
	}

	@Override
	public List<UserInfoView> findByMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteById(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	



}
