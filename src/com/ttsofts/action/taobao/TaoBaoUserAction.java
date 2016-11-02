package com.ttsofts.action.taobao;

import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.Action;
import com.ttsofts.action.BaseAbstractAction;
import com.ttsofts.entity.Page;
import com.ttsofts.entity.taobao.TaoBaoUser;
import com.ttsofts.mapper.taobao.TaoBaoUserMapper;
import com.ttsofts.util.BeanObjectToMap;


public class TaoBaoUserAction extends BaseAbstractAction {

	private TaoBaoUserMapper taoBaoUserMapper;

	@Override
	public String findByAll() {
		// TODO Auto-generated method stub
		result = new HashMap<String, Object>();
		List<TaoBaoUser> list =  taoBaoUserMapper.findByAll();
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
	

	@Override
	public String add() {
		// TODO Auto-generated method stub
		result = new HashMap<String, Object>();
		try {
			param = URLDecoder.decode(param,"UTF-8");
			TaoBaoUser entity = JSON.parseObject(param,TaoBaoUser.class);
			entity.setId(String.valueOf(new Date().getTime()));
			int b = taoBaoUserMapper.add(entity);
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
			param = URLDecoder.decode(param,"UTF-8");
			TaoBaoUser entity = JSON.parseObject(param,TaoBaoUser.class);
			int b = taoBaoUserMapper.update(entity);
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
		result = new HashMap<String, Object>();
		try {
			param = URLDecoder.decode(param,"UTF-8");
			TaoBaoUser entity = JSON.parseObject(param,TaoBaoUser.class);
			int b = taoBaoUserMapper.deleteById(entity.getId());
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
	
	@Override
	public String findByPage() {
		// TODO Auto-generated method stub
		result = new HashMap<String, Object>();
		try {
			param = URLDecoder.decode(param,"UTF-8");
			TaoBaoUser entity = JSON.parseObject(param,TaoBaoUser.class);
			Map<String, Object> pageMap = new HashMap<String, Object>();
			//分页参数
			Page page = entity.getPage();
			int pageNum = page.getPageNum();
			int pageSize = page.getPageSize();
			pageMap.put("startRowNum", (pageNum-1)*pageSize);
			pageMap.put("pageSize",pageSize);
			List<TaoBaoUser> list = taoBaoUserMapper.findByPage(pageMap);
			Map<String,Object> infoMap = new HashMap<String,Object>();
			int totalCount = taoBaoUserMapper.getCount(infoMap);
			page.setTotalCount(totalCount);
			if (list == null || list.size() == 0) {
				result.put("result", false);
				result.put("msg", "没有查询到结果");
			} else {
				result.put("result", true);
				result.put("page", page);
				result.put("list", list);
			}
		}catch (Exception e) {
			result.put("result", false);
			result.put("msg", e.getMessage());
		}	
		return Action.SUCCESS;
	}

	@Override
	public String getCount() {
		// TODO Auto-generated method stub
		result = new HashMap<String, Object>();
		try {
			Map<String, Object> map = null;
			if(param!=null){
				param = URLDecoder.decode(param,"UTF-8");
				TaoBaoUser entity = JSON.parseObject(param,TaoBaoUser.class);
				map = BeanObjectToMap.convertBean(entity);
			}
			int b = taoBaoUserMapper.getCount(map);
			result.put("result", true);
			result.put("count", b);
		} catch (Exception e) {
			result.put("result", false);
			result.put("msg", e.getMessage());
		}
		return Action.SUCCESS;
	}
	
	public void setTaoBaoUserMapper(TaoBaoUserMapper taoBaoUserMapper) {
		this.taoBaoUserMapper = taoBaoUserMapper;
	}
}
