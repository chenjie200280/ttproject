package com.ttsofts.action.app;

import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.Action;
import com.ttsofts.action.BaseAbstractAction;
import com.ttsofts.entity.app.AppAdvert;
import com.ttsofts.entity.app.AppLog;
import com.ttsofts.service.app.AppAdvertService;
import com.ttsofts.service.app.AppLogService;
import com.ttsofts.util.BeanObjectToMap;


/**
 * 
 * @author chenjie
 *
 */
public class AppAdvertAction extends BaseAbstractAction {

	private AppAdvertService appAdvertService;
	
	private AppLogService appLogService;

	@Override
	public String findByAll() {
		// TODO Auto-generated method stub
		result = new HashMap<String, Object>();
		List<AppAdvert> list =  appAdvertService.findByAll();
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
		Map<String, Object> map;
		try {
			param = URLDecoder.decode(param,"UTF-8");
			Object obj = JSON.parseObject(param, AppAdvert.class);
			map = BeanObjectToMap.convertBean(obj);
			List<AppAdvert> list =  appAdvertService.findByMap(map);
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

	public String init() {
		try {
			JSONObject object = JSON.parseObject(param);
			String aid = "";
			if(object.containsKey("aid")){
				aid = object.getString("aid");
			}
			String mac = "";
			if(object.containsKey("mac")){
				mac = object.getString("mac");
			}
			AppLog appLog = new AppLog();
			appLog.setApp_info_id(aid);
			appLog.setMac(mac);
			appLogService.add(appLog);
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("app_info_id", aid);
			result = appAdvertService.init(map);
			if (result == null) {
				result = new HashMap<String, Object>();
				result.put("result", false);
				result.put("msg", "没有查询到结果");
			} else {
				result.put("result", true);
			}
		} catch (Exception e) {
			result = new HashMap<String, Object>();
			result.put("result", false);
			result.put("msg", e.getMessage());
		}
		return Action.SUCCESS;
	}
	
	public String add() {
		result = new HashMap<String, Object>();
		try {
			param = URLDecoder.decode(param,"UTF-8");
			AppAdvert entity = JSON.parseObject(param,AppAdvert.class);
			Date addtime = new Date();
			entity.setId(String.valueOf(addtime.getTime()));
			boolean b = appAdvertService.add(entity);
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
			AppAdvert entity = JSON.parseObject(param,AppAdvert.class);
			boolean b = appAdvertService.update(entity);
			if (b) {
				result.put("result", true);
				result.put("msg", "修改成功");
			} else {
				result.put("result", false);
				result.put("msg", "修改失败");
			}

		} catch (Exception e) {
			result.put("result", false);
			result.put("msg", e.getMessage());
		}
		return Action.SUCCESS;
	}

	public void setAppAdvertService(AppAdvertService appAdvertService) {
		this.appAdvertService = appAdvertService;
	}

	public void setAppLogService(AppLogService appLogService) {
		this.appLogService = appLogService;
	}

	@Override
	public String delete() {
		// TODO Auto-generated method stub
		result = new HashMap<String, Object>();
		try {
			param = URLDecoder.decode(param,"UTF-8");
			AppAdvert entity = JSON.parseObject(param,AppAdvert.class);
			boolean b = appAdvertService.deleteById(entity.getId());
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
		return null;
	}

	@Override
	public String getCount() {
		// TODO Auto-generated method stub
		return null;
	}


}
