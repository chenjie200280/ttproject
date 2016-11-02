package com.ttsofts.action.app;

import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.Action;
import com.ttsofts.action.BaseAbstractAction;
import com.ttsofts.entity.Page;
import com.ttsofts.entity.app.AppInfo;
import com.ttsofts.entity.taobao.TaoBaoUser;
import com.ttsofts.service.app.AppInfoService;
import com.ttsofts.util.BeanObjectToMap;


public class AppInfoAction extends BaseAbstractAction {

	private AppInfoService appInfoService;

	@Override
	public String findByAll() {
		// TODO Auto-generated method stub
		result = new HashMap<String, Object>();
		List<AppInfo> list = appInfoService.findByAll();;
		if (list == null || list.size() == 0) {
			result.put("result", false);
			result.put("msg","没有查询到结果");
		} else {
			result.put("result", true);
			result.put("list",list);
		}
		return Action.SUCCESS;
	}

	public AppInfoService getAppInfoService() {
		return appInfoService;
	}

	public void setAppInfoService(AppInfoService appInfoService) {
		this.appInfoService = appInfoService;
	}

	public String add() {
		// TODO Auto-generated method stub
		result = new HashMap<String, Object>();
		try {
			param = URLDecoder.decode(param,"UTF-8");
			AppInfo entity = JSON.parseObject(param,AppInfo.class);
			Date addtime = new Date();
			entity.setId(String.valueOf(addtime.getTime()));
			entity.setAddtime(addtime);
			boolean b = appInfoService.add(entity);
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
			param = URLDecoder.decode(param,"UTF-8");
			AppInfo entity = JSON.parseObject(param,AppInfo.class);
			boolean b = appInfoService.update(entity);
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
		return null;
	}

	@Override
	public String delete() {
		// TODO Auto-generated method stub
		result = new HashMap<String, Object>();
		try {
			param = URLDecoder.decode(param,"UTF-8");
			AppInfo entity = JSON.parseObject(param,AppInfo.class);
			boolean b = appInfoService.deleteById(entity.getId());
			if (b) {
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
			List<AppInfo> list = appInfoService.findByPage(pageMap);
			Map<String,Object> infoMap = new HashMap<String,Object>();
			int totalCount = appInfoService.getCount(infoMap);
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
				AppInfo entity = JSON.parseObject(param,AppInfo.class);
				map = BeanObjectToMap.convertBean(entity);
			}
			int b = appInfoService.getCount(map);
			result.put("result", true);
			result.put("count", b);
		} catch (Exception e) {
			result.put("result", false);
			result.put("msg", e.getMessage());
		}
		return Action.SUCCESS;
	}

	
}
