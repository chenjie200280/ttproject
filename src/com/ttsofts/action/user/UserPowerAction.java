package com.ttsofts.action.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.ttsofts.action.BaseAbstractAction;
import com.ttsofts.entity.user.UserPower;
import com.ttsofts.mapper.user.UserPowerMapper;

public class UserPowerAction extends BaseAbstractAction {

	private UserPowerMapper userPowerMapper;

	// 返回
	private Map<String, Object> result;

	@Override
	public String findByAll() {
		// TODO Auto-generated method stub
		result = new HashMap<String, Object>();
		List<UserPower> list = userPowerMapper.findByAll();
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
		
		return Action.SUCCESS;
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

	public void setUserPowerMapper(UserPowerMapper userPowerMapper) {
		this.userPowerMapper = userPowerMapper;
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
