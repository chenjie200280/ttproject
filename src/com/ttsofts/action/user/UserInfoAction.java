package com.ttsofts.action.user;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.Action;
import com.ttsofts.action.BaseAbstractAction;
import com.ttsofts.entity.user.UserInfoView;
import com.ttsofts.mapper.user.UserInfoMapper;
import com.ttsofts.util.AppUtil;
import com.ttsofts.util.BeanObjectToMap;

public class UserInfoAction extends BaseAbstractAction {

	private UserInfoMapper userInfoMapper;
	
	// 返回
	private Map<String, Object> result;

	@Override
	public String findByAll() {
		// TODO Auto-generated method stub
		result = new HashMap<String, Object>();
		List<UserInfoView> list = userInfoMapper.findByAll();
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
		try {
			param = URLDecoder.decode(param,"UTF-8");
			JSONObject obj = JSON.parseObject(param);
			String token = obj.getString("token");
			String userid = null;
			if(StringUtils.hasText(token)){
				//查找user
				for(Map<String,String> tokenmap:AppUtil.tokens){
					if(tokenmap.containsKey(token)){
					    userid=tokenmap.get(token);
					}
				}
			}
			if(userid==null){
				result.put("result", false);
				result.put("msg","您还没有登录");
			}else{
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("id", userid);
				List<UserInfoView> list = userInfoMapper.findByMap(map);
				result.put("result", true);
				result.put("list", list);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result.put("result", false);
			result.put("msg", e.getMessage());
		}
		
		return Action.SUCCESS;
	}
	


	public String login() {
		result = new HashMap<String, Object>();
		try {
			param = URLDecoder.decode(param,"UTF-8");
			Object obj = JSON.parseObject(param, UserInfoView.class);
			Map<String, Object> map = BeanObjectToMap.convertBean(obj);
			List<UserInfoView> list = userInfoMapper.findByMap(map);
			UserInfoView userInfo = null;
			if(list!=null&&list.size()>0)
			{
				userInfo = list.get(0);
			}
			if (userInfo == null) {
				result.put("result", false);
				result.put("msg", "用户名密码不正确");
			} else {
				//后期再做安全性验证 生成token
				String token = UUID.randomUUID().toString();
				Map<String,String> tokenmap = new HashMap<String, String>();
				tokenmap.put(token, userInfo.getId());
				AppUtil.tokens.add(tokenmap);
				result.put("result", true);
				result.put("userinfo", userInfo);
				result.put("token", token);
			}

		} catch (Exception e) {
			result.put("result", false);
			result.put("msg", e.getMessage());
		}
		return Action.SUCCESS;
	}

	public void setUserInfoMapper(UserInfoMapper userInfoMapper) {
		this.userInfoMapper = userInfoMapper;
	}

	public Map<String, Object> getResult() {
		return result;
	}

	public void setParam(String param) {
		this.param = param;
	}

	@Override
	public String add() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String update() {
		// TODO Auto-generated method stub
		return null;
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
