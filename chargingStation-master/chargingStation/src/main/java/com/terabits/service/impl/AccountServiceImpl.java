package com.terabits.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.terabits.dao.AccountDAO;
import com.terabits.dao.DeviceDAO;
import com.terabits.dao.LogDAO;
import com.terabits.meta.bo.AccountAreaInfoBO;
import com.terabits.meta.bo.AccountCityInfoBO;
import com.terabits.meta.po.AdminPO;
import com.terabits.meta.vo.AccountAreaInfoVO;
import com.terabits.meta.vo.AccountCityInfoVO;
import com.terabits.service.AccountService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountDAO accountDAO;
	@Autowired
	private LogDAO logDAO;
	@Autowired
	private DeviceDAO deviceDAO;
	
	public JSONArray getAccountCityInfo(AccountCityInfoBO accountCityInfoBO) {
		List<AccountCityInfoVO> accountCityInfoVOs = accountDAO.selectAccountCityInfo(accountCityInfoBO);
		return JSONArray.fromObject(accountCityInfoVOs);
	}

	public int addAccountCityInfo(String currentName, AdminPO adminPO) {
		int result = accountDAO.insertAccountCityInfo(adminPO);
		if (result == 200) {
			//插入日志
			logDAO.insertLog(currentName, 2, "添加城市用户：" + adminPO.getName());
			return 1;
		} else {
			return 2;
		}
	}

	public int removeAccountCityInfo(String currentName, String phone) {
		int result = accountDAO.deleteAccountCityInfo(phone);
		if (result == 200) {
			//插入日志
			logDAO.insertLog(currentName, 2, "删除城市用户：" + phone);
			return 1;
		} else {
			return 2;
		}
	}

	public int modifyAccountCityInfo(String currentName, AdminPO adminPO) {
		int result = accountDAO.updateAccountCityInfo(adminPO);
		if (result == 200) {
			//插入日志
			logDAO.insertLog(currentName, 2, "修改城市用户信息：" + adminPO.getPhone());
			return 1;
		} else {
			return 2;
		}
	}

	public JSONArray getAccountAreaInfo(AccountAreaInfoBO accountAreaInfoBO) {
		List<AccountAreaInfoVO> accountAreaInfoVOs = accountDAO.selectAccountAreaInfo(accountAreaInfoBO);
		HashMap<String, JSONObject> hashMap = new HashMap<String, JSONObject>();
		for (AccountAreaInfoVO accountAreaInfoVO : accountAreaInfoVOs) {
			if (hashMap.containsKey(accountAreaInfoVO.getPhone())) {
				JSONObject jsonObject = hashMap.get(accountAreaInfoVO.getPhone());
				if (jsonObject.get("station") != null) {
					jsonObject.put("count", jsonObject.getInt("count") + 1);
					JSONArray stationArray = jsonObject.getJSONArray("station");
					JSONObject stationObject = new JSONObject();
					stationObject.put("station", accountAreaInfoVO.getStation());
					stationArray.add(stationObject);
					jsonObject.put("station", stationArray);
				}
			} else {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("name", accountAreaInfoVO.getName());
				jsonObject.put("phone", accountAreaInfoVO.getPhone());
				jsonObject.put("type", accountAreaInfoVO.getType());
				jsonObject.put("city", accountAreaInfoVO.getCity());
				jsonObject.put("email", accountAreaInfoVO.getEmail());
				if (accountAreaInfoVO.getStation() != null) {
					JSONArray stationArray = new JSONArray();
					JSONObject stationObject = new JSONObject();
					stationObject.put("station", accountAreaInfoVO.getStation());
					stationArray.add(stationObject);
					jsonObject.put("count", 1);	
					jsonObject.put("station", stationArray);
				} else {
					jsonObject.put("count", 0);	
					jsonObject.put("station", null);
				}
				hashMap.put(accountAreaInfoVO.getPhone(), jsonObject);
			}
		}
		JSONArray info = new JSONArray();
		Iterator iter = hashMap.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			String key = (String)entry.getKey();
			JSONObject val = (JSONObject)entry.getValue();
			info.add(val);
		}
		return info;
	}

	public int modifyAccountArea(String currentName, AdminPO adminPO) {
		int result = accountDAO.updateAccountArea(adminPO);
		if (result == 200) {
			//插入日志
			logDAO.insertLog(currentName, 2, "修改区域负责人信息：" + adminPO.getName());
			return 1;
		} else {
			return 2;
		}
	}

	public JSONObject addAccountAreaSite(String currentName, String phone, List<String> stationS) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", 1);
		JSONArray stationArray = new JSONArray();
		for (String station : stationS) {
			int result = accountDAO.insertAccountAreaSite(phone, station);
			if (result == 200) {
				//插入日志
				logDAO.insertLog(currentName, 2, "添加区域负责人管理网点：" + phone + "-" + station);
			} else {
				jsonObject.put("status", 2);
				JSONObject stationObject = new JSONObject();
				stationObject.put("station", station);
				stationArray.add(stationObject);
			}
		}
		jsonObject.put("station", stationArray);
		return jsonObject;
	}

	public JSONObject removeAccountAreaSite(String currentName, String phone, List<String> stationS) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", 1);
		JSONArray stationArray = new JSONArray();
		for (String station : stationS) {
			int result = accountDAO.deleteAccountAreaSite(phone, station);
			if (result == 200) {
				//插入日志
				logDAO.insertLog(currentName, 2, "删除区域负责人管理网点：" + phone + "-" + station);
			} else {
				jsonObject.put("status", 2);
				JSONObject stationObject = new JSONObject();
				stationObject.put("station", station);
				stationArray.add(stationObject);
			}
		}
		jsonObject.put("station", stationArray);
		return jsonObject;
	}

	public JSONObject addAccountArea(String currentName, AdminPO adminPO, List<String> stationS) {
		//先添加区域负责人信息
		int result = accountDAO.insertAccountArea(adminPO);
		if (result == 200) {
			//插入日志
			logDAO.insertLog(currentName, 2, "添加区域负责人：" + adminPO.getName());
		} else {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", 3);
			return jsonObject;
		}
		//再添加网点信息
		return addAccountAreaSite(currentName, adminPO.getPhone(), stationS);
	}

	public int removeAccountArea(String currentName, String phone) {
		//查找某位区域负责人管理的所有网点
		List<String> stationS = deviceDAO.selectAllStationByArea(phone);
		//删除该区域负责人可管理的网点
		removeAccountAreaSite(currentName, phone, stationS);
		//删除该区域负责人
		int result = accountDAO.deleteAccountArea(phone);
		if (result == 200) {
			//插入日志
			logDAO.insertLog(currentName, 2, "删除区域负责人：" + phone);
			return 1;
		} else {
			return 2;
		}
	}

}
