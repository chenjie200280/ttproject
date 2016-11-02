package com.ttsofts.action.app;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.Action;
import com.ttsofts.action.BaseAbstractAction;
import com.ttsofts.entity.Page;
import com.ttsofts.entity.app.AppLog;
import com.ttsofts.entity.app.AppLogStatistics;
import com.ttsofts.service.app.AppLogService;

/**
 * SELECT COUNT(id),DATE_FORMAT(ADDTIME,'%Y-%m-%d') AS DAY
   FROM app_log  GROUP BY DAY ORDER BY DAY DESC;
 * @author chenjie
 *
 */
public class AppLogAction extends BaseAbstractAction {

	private AppLogService appLogService;
	
	@Override
	public String findByAll() {
		// TODO Auto-generated method stub
		result = new HashMap<String, Object>();
		List<AppLog> list = appLogService.findByAll();
		if (list == null || list.size() == 0) {
			result.put("result", false);
			result.put("msg","没有查询到结果");
		} else {
			result.put("result", true);
			result.put("list",list);
		}
		return Action.SUCCESS;
	}
	
	public String findByStatistics(){
		// TODO Auto-generated method stub
		result = new HashMap<String, Object>();
		try {
			param = URLDecoder.decode(param,"UTF-8");
			AppLogStatistics entity = JSON.parseObject(param,AppLogStatistics.class);
			Map<String, Object> pageMap = new HashMap<String, Object>();
			//分页参数
			Page page = entity.getPage();
			int pageNum = page.getPageNum();
			int pageSize = page.getPageSize();
			pageMap.put("startRowNum", (pageNum-1)*pageSize);
			pageMap.put("pageSize",pageSize);
			List<AppLogStatistics> list = appLogService.findByStatistics(pageMap);
			Map<String,Object> infoMap = new HashMap<String,Object>();
			int totalCount = appLogService.getStatisticsCount(infoMap);
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
	public String findByMap() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setAppLogService(AppLogService appLogService) {
		this.appLogService = appLogService;
	}

	public String add() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		result = new HashMap<String, Object>();
		try {
			AppLog entity = JSON.parseObject(param,AppLog.class);
			boolean b = appLogService.add(entity);
			if (b) {
				result.put("result", false);
				result.put("msg", "添加失败");
			} else {
				result.put("result", true);
				result.put("msg", "添加成功");
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
			AppLog entity = JSON.parseObject(param,AppLog.class);
			boolean b = appLogService.update(entity);
			if (b) {
				result.put("result", false);
				result.put("msg", "添加失败");
			} else {
				result.put("result", true);
				result.put("msg", "添加成功");
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
