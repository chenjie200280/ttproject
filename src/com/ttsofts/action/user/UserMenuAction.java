package com.ttsofts.action.user;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.Action;
import com.ttsofts.action.BaseAbstractAction;
import com.ttsofts.entity.user.UserMenuView;
import com.ttsofts.mapper.user.UserMenuMapper;

public class UserMenuAction extends BaseAbstractAction {

	private UserMenuMapper userMenuMapper;

	// 返回
	private Map<String, Object> result;

	@Override
	public String findByAll() {
		// TODO Auto-generated method stub
		result = new HashMap<String, Object>();
		List<UserMenuView> list = userMenuMapper.findByAll();
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
		result = new HashMap<String, Object>();
		try {
			param = URLDecoder.decode(param,"UTF-8");
			UserMenuView userMenu = JSON.parseObject(param,UserMenuView.class);
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("parentid", "root");
			map.put("powerid", userMenu.getPowerid());
			List<UserMenuView> roots = userMenuMapper.findByMap(map);
			List<Object> list = new ArrayList<Object>();
			for(UserMenuView root:roots){
				Map<String,Object> rootMap = new HashMap<String, Object>();
				rootMap.put("id", root.getId());
				rootMap.put("name", root.getName());
				rootMap.put("remark", root.getRemark());
				rootMap.put("url", root.getUrl());
				rootMap.put("powerid", root.getPowerid());
				rootMap.put("parentid", root.getParentid());
				rootMap.put("powername",root.getPowername());
				map.put("parentid", root.getId());
				map.put("powerid", userMenu.getPowerid());
				List<UserMenuView> childrens = userMenuMapper.findByMap(map);
				rootMap.put("children",childrens);
				list.add(rootMap);
			}
			result.put("result", true);
			result.put("list", list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result.put("result", false);
			result.put("msg", e.getMessage());
		}
		
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

	public void setUserMenuMapper(UserMenuMapper userMenuMapper) {
		this.userMenuMapper = userMenuMapper;
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
