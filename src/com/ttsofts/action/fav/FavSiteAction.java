package com.ttsofts.action.fav;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.Action;
import com.ttsofts.action.BaseAbstractAction;
import com.ttsofts.entity.fav.FavSite;
import com.ttsofts.mapper.fav.FavSiteMapper;
import com.ttsofts.util.BeanObjectToMap;

public class FavSiteAction extends BaseAbstractAction {

	private FavSiteMapper favSiteMapper;
	
	@Override
	public String findByAll() {
		// TODO Auto-generated method stub
		result = new HashMap<String, Object>();
		List<FavSite> list = favSiteMapper.findByAll();
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
	public String findByMap() {
		// TODO Auto-generated method stub
		result = new HashMap<String, Object>();
		Object obj = JSON.parseObject(param, FavSite.class);
		Map<String, Object> map;
		try {
			map = BeanObjectToMap.convertBean(obj);
			List<FavSite> list =  favSiteMapper.findByMap(map);
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

	public String add() {
		// TODO Auto-generated method stub
		result = new HashMap<String, Object>();
		try {
			FavSite entity = JSON.parseObject(param,FavSite.class);
			int b = favSiteMapper.add(entity);
			if (b > 0) {
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
			FavSite entity = JSON.parseObject(param,FavSite.class);
			int b = favSiteMapper.update(entity);
			if (b > 0) {
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
	public String delete() {
		// TODO Auto-generated method stub
		return null;
	}



	public void setFavSiteMapper(FavSiteMapper favSiteMapper) {
		this.favSiteMapper = favSiteMapper;
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
