package com.ttsofts.action.money;

import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.Action;
import com.ttsofts.action.BaseAbstractAction;
import com.ttsofts.entity.money.MoneySalaryType;
import com.ttsofts.mapper.money.MoneySalaryTypeMapper;
import com.ttsofts.util.BeanObjectToMap;

public class MoneySalaryTypeAction extends BaseAbstractAction {

	private MoneySalaryTypeMapper moneySalaryTypeMapper;


	@Override
	public String findByAll() {
		// TODO Auto-generated method stub
		result = new HashMap<String, Object>();
		List<MoneySalaryType> list = moneySalaryTypeMapper.findByAll();
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
			param = URLDecoder.decode(param,"UTF-8");
			MoneySalaryType entity = JSON.parseObject(param,MoneySalaryType.class);
			Date addtime = new Date();
			entity.setId(String.valueOf(addtime.getTime()));
			int b = moneySalaryTypeMapper.add(entity);
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
			param = URLDecoder.decode(param,"UTF-8");
			MoneySalaryType entity = JSON.parseObject(param,MoneySalaryType.class);
			int b = moneySalaryTypeMapper.update(entity);
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
		Map<String, Object> map;
		try {
			param = URLDecoder.decode(param,"UTF-8");
			Object obj = JSON.parseObject(param, MoneySalaryType.class);
			map = BeanObjectToMap.convertBean(obj);
			List<MoneySalaryType> list =  moneySalaryTypeMapper.findByMap(map);
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
		result = new HashMap<String, Object>();
		try {
			param = URLDecoder.decode(param,"UTF-8");
			MoneySalaryType entity = JSON.parseObject(param,MoneySalaryType.class);
			int b = moneySalaryTypeMapper.deleteById(entity.getId());
			if (b>0) {
				result.put("result", true);
				result.put("msg", "删除成功");

			} else {
				result.put("result", false);
				result.put("msg", "删除失败");
			}

		} catch (Exception e) {
			result.put("result", false);
			result.put("msg", e.getMessage());
		}
		return Action.SUCCESS;
	}
	public void setMoneySalaryTypeMapper(MoneySalaryTypeMapper moneySalaryTypeMapper) {
		this.moneySalaryTypeMapper = moneySalaryTypeMapper;
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
