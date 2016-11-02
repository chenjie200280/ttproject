package com.ttsofts.service.money;

import java.util.List;
import java.util.Map;

import com.ttsofts.entity.money.MoneySalary;
import com.ttsofts.mapper.money.MoneySalaryMapper;
import com.ttsofts.service.BaseInterfaceService;

public class MoneySalaryService implements BaseInterfaceService<MoneySalary> {

	private MoneySalaryMapper moneySalaryMapper;
	
	@Override
	public List<MoneySalary> findByAll() {
		// TODO Auto-generated method stub
		return moneySalaryMapper.findByAll();
	}

	@Override
	public boolean add(MoneySalary entity) {
		// TODO Auto-generated method stub
		moneySalaryMapper.add(entity);
		return true;
	}

	@Override
	public boolean update(MoneySalary entity) {
		// TODO Auto-generated method stub
		moneySalaryMapper.update(entity);
		return true;
	}

	@Override
	public List<MoneySalary> findByMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return moneySalaryMapper.findByMap(map);
	}

	public void setMoneySalaryMapper(MoneySalaryMapper moneySalaryMapper) {
		this.moneySalaryMapper = moneySalaryMapper;
	}

	@Override
	public boolean deleteById(String id) {
		// TODO Auto-generated method stub
		moneySalaryMapper.deleteById(id);
		return true;
	}
	
}
