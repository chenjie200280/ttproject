package com.ttsofts.action.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.ttsofts.action.BaseAbstractAction;
import com.ttsofts.entity.user.UserInfoPower;
import com.ttsofts.mapper.user.UserInfoPowerMapper;

public class UserInfoPowerAction extends BaseAbstractAction {

	private UserInfoPowerMapper userInfoPowerMapper;

	// 返回
	private Map<String, Object> result;

	@Override
	public String findByAll() {
		// TODO Auto-generated method stub
		result = new HashMap<String, Object>();
		List<UserInfoPower> list = userInfoPowerMapper.findByAll();
		if (list == null || list.size() == 0) {
			result.put("result", false);
			result.put("msg", "没有查询到结果");
		} else {
			result.put("result", true);
			result.put("list", list);
		}
		return Action.SUCCESS;
	}

	@Override
	public String findByMap() {
		// TODO Auto-generated method stub
		return null;
	}


	public Map<String, Object> getResult() {
		return result;
	}

	public void setParam(String param) {
		this.param = param;
	}

	@Override
	public String add() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String update() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setUserInfoPowerMapper(UserInfoPowerMapper userInfoPowerMapper) {
		this.userInfoPowerMapper = userInfoPowerMapper;
	}

	@Override
	public String findByPage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCount() {
		// TODO Auto-generated method stub
		return null;
	}


	
}
