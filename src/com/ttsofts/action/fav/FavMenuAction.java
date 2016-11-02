package com.ttsofts.action.fav;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.Action;
import com.ttsofts.action.BaseAbstractAction;
import com.ttsofts.entity.fav.FavMenu;
import com.ttsofts.mapper.fav.FavMenuMapper;
import com.ttsofts.util.BeanObjectToMap;

public class FavMenuAction extends BaseAbstractAction {
	
	private FavMenuMapper favMenuMapper;

	
	@Override
	public String findByAll() {
		// TODO Auto-generated method stub
		result = new HashMap<String, Object>();
		List<FavMenu> list = favMenuMapper.findByAll();
		if (list == null || list.size() == 0) {
			result.put("result", false);
			result.put("msg","没有查询到结果");
		} else {
			result.put("result", true);
			result.put("list",list);
		}
		return Action.SUCCESS;
	}

	@Override
	public String add() {
		// TODO Auto-generated method stub
		result = new HashMap<String, Object>();
		try {
			FavMenu entity = JSON.parseObject(param,FavMenu.class);
			int b = favMenuMapper.add(entity);
			if (b>0) {
				result.put("result", true);
				result.put("msg", "添加成功");
			} else {
				result.put("result", false);
				result.put("msg", "添加失败");
			}

		} catch (Exception e) {
			result.put("result", false);
			result.put("msg", e.getMessage());
		}
		return Action.SUCCESS;
	}

	@Override
	public String update() {
		// TODO Auto-generated method stub
		result = new HashMap<String, Object>();
		try {
			FavMenu entity = JSON.parseObject(param,FavMenu.class);
			int b = favMenuMapper.update(entity);
			if (b>0) {
				result.put("result", true);
				result.put("msg", "添加成功");
			} else {
				result.put("result", false);
				result.put("msg", "添加失败");
			}

		} catch (Exception e) {
			result.put("result", false);
			result.put("msg", e.getMessage());
		}
		return Action.SUCCESS;
	}

	@Override
	public String findByMap() {
		// TODO Auto-generated method stub
		result = new HashMap<String, Object>();
		Object obj = JSON.parseObject(param, FavMenu.class);
		Map<String, Object> map;
		try {
			map = BeanObjectToMap.convertBean(obj);
			List<FavMenu> list =  favMenuMapper.findByMap(map);
			if (list == null || list.size() == 0) {
				result.put("result", false);
				result.put("msg", "没有查询到结果");
			} else {
				result.put("result", true);
				result.put("list", list);
			}
		} catch (Exception e) {
			result = new HashMap<String, Object>();
			result.put("result", false);
			result.put("msg", e.getMessage());
		}
		return Action.SUCCESS;
	}



	@Override
	public String delete() {
		// TODO Auto-generated method stub
		return null;
	}

	public FavMenuMapper getFavMenuMapper() {
		return favMenuMapper;
	}

	public void setFavMenuMapper(FavMenuMapper favMenuMapper) {
		this.favMenuMapper = favMenuMapper;
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
