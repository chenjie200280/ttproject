package com.ttsofts.action.money;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.Action;
import com.ttsofts.action.BaseAbstractAction;
import com.ttsofts.entity.money.MoneyPay;
import com.ttsofts.service.money.MoneyPayService;
import com.ttsofts.util.BeanObjectToMap;

public class MoneyPayAction extends BaseAbstractAction {

	private MoneyPayService moneyPayService;

	@Override
	public String findByAll() {
		// TODO Auto-generated method stub
		result = new HashMap<String, Object>();
		List<MoneyPay> list = moneyPayService.findByAll();
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
			MoneyPay entity = JSON.parseObject(param,MoneyPay.class);
			boolean b = moneyPayService.add(entity);
			if (b) {
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
			MoneyPay entity = JSON.parseObject(param,MoneyPay.class);
			boolean b = moneyPayService.update(entity);
			if (b) {
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
		Object obj = JSON.parseObject(param, MoneyPay.class);
		Map<String, Object> map;
		try {
			map = BeanObjectToMap.convertBean(obj);
			List<MoneyPay> list =  moneyPayService.findByMap(map);
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
	
	public void setMoneyPayService(MoneyPayService moneyPayService) {
		this.moneyPayService = moneyPayService;
	}

	@Override
	public String delete() {
		// TODO Auto-generated method stub
		return null;
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
