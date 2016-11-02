package com.ttsofts.service.money;

import java.util.List;
import java.util.Map;

import com.ttsofts.entity.money.MoneyCard;
import com.ttsofts.mapper.money.MoneyCardMapper;
import com.ttsofts.service.BaseInterfaceService;

public class MoneyCardService implements BaseInterfaceService<MoneyCard> {

	private MoneyCardMapper moneyCardMapper;
	
	@Override
	public List<MoneyCard> findByAll() {
		// TODO Auto-generated method stub
		return moneyCardMapper.findByAll();
	}

	@Override
	public boolean add(MoneyCard entity) {
		// TODO Auto-generated method stub
		moneyCardMapper.add(entity);
		return true;
	}

	@Override
	public boolean update(MoneyCard entity) {
		// TODO Auto-generated method stub
		moneyCardMapper.update(entity);
		return true;
	}

	@Override
	public List<MoneyCard> findByMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return moneyCardMapper.findByMap(map);
	}

	public void setMoneyCardMapper(MoneyCardMapper moneyCardMapper) {
		this.moneyCardMapper = moneyCardMapper;
	}

	@Override
	public boolean deleteById(String id) {
		// TODO Auto-generated method stub
		return false;
	}
}
