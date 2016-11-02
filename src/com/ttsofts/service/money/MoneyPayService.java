package com.ttsofts.service.money;

import java.util.List;
import java.util.Map;

import com.ttsofts.entity.money.MoneyPay;
import com.ttsofts.mapper.money.MoneyPayMapper;
import com.ttsofts.service.BaseInterfaceService;

public class MoneyPayService implements BaseInterfaceService<MoneyPay> {

	private MoneyPayMapper moneyPayMapper;
	
	@Override
	public List<MoneyPay> findByAll() {
		// TODO Auto-generated method stub
		return moneyPayMapper.findByAll();
	}

	@Override
	public boolean add(MoneyPay entity) {
		// TODO Auto-generated method stub
		moneyPayMapper.add(entity);
		return true;
	}

	@Override
	public boolean update(MoneyPay entity) {
		// TODO Auto-generated method stub
		moneyPayMapper.update(entity);
		return true;
	}

	@Override
	public List<MoneyPay> findByMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return moneyPayMapper.findByMap(map);
	}

	public void setMoneyPayMapper(MoneyPayMapper moneyPayMapper) {
		this.moneyPayMapper = moneyPayMapper;
	}

	@Override
	public boolean deleteById(String id) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
