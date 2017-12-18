package com.terabits.service;

import java.util.List;

import com.terabits.meta.bo.AccountAreaInfoBO;
import com.terabits.meta.bo.AccountCityInfoBO;
import com.terabits.meta.po.AdminPO;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface AccountService {
	/**
	 * 城市人员查询
	 * @param accountCityInfoBO
	 * @return
	 */
	public JSONArray getAccountCityInfo(AccountCityInfoBO accountCityInfoBO);
	
	/**
	 * 添加城市人员
	 * @param adminPO
	 * @return
	 */
	public int addAccountCityInfo(String currentName, AdminPO adminPO);
	
	/**
	 * 删除城市人员
	 * @param phone
	 * @return
	 */
	public int removeAccountCityInfo(String currentName, String phone);
	
	/**
	 * 修改城市人员信息
	 * @param adminPO
	 * @return
	 */
	public int modifyAccountCityInfo(String currentName, AdminPO adminPO);
	
	/**
	 * 区域负责人查询
	 * @param accountAreaInfoBO
	 * @return
	 */
	public JSONArray getAccountAreaInfo(AccountAreaInfoBO accountAreaInfoBO);
	
	/**
	 * 修改区域负责人信息
	 * @param adminPO
	 * @return
	 */
	public int modifyAccountArea(String currentName, AdminPO adminPO);
	
	/**
	 * 添加区域负责人网点
	 * @param phone
	 * @param station
	 * @return
	 */
	public JSONObject addAccountAreaSite(String currentName, String phone, List<String> stationS);
	
	/**
	 * 删除负责人网点
	 * @param phone
	 * @param station
	 * @return
	 */
	public JSONObject removeAccountAreaSite(String currentName, String phone, List<String> stationS);
	
	/**
	 * 添加区域负责人
	 * @param adminPO
	 * @return
	 */
	public JSONObject addAccountArea(String currentName, AdminPO adminPO, List<String> stationS);
	
	/**
	 * 删除区域负责人
	 * @param phone
	 * @return
	 */
	public int removeAccountArea(String currentName, String phone);
}
