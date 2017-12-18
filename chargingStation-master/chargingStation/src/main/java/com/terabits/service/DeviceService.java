package com.terabits.service;

import com.terabits.meta.bo.BoxConnectionBO;
import com.terabits.meta.bo.SiteInsertBO;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface DeviceService {
	/**
	 * 获取网点设备信息：所属城市，所属区域，区域负责人手机号，网点，网点地址
	 * @param city
	 * @param area
	 * @param station
	 * @param type
	 * @param name
	 * @return
	 */
	public JSONArray getDeviceInfo(String city, String area, String station, int type, String name);
	
	/**
	 * 增加网点
	 * @param deviceInsertBO
	 * @return
	 */
	public int insertSite(String name, SiteInsertBO deviceInsertBO);
	
	/**
	 * 网点设备列表
	 * @param station
	 * @return
	 */
	public JSONArray getBoxSiteList(String station);
	
	/**
	 * 设备连接性查询
	 * @param boxConnectionBO
	 * @return
	 */
	public JSONArray getBoxConnection(BoxConnectionBO boxConnectionBO);	
	
	/**
	 * 近十天设备连接性查询
	 * @param boxConnectionTendaysBO
	 * @return
	 */
	public JSONArray getBoxConnectionTendays(BoxConnectionBO boxConnectionBO);
	
	/**
	 * 查询所有未分配网点
	 * @return
	 */
	public JSONArray getAllStationNoArea();
	
	/**
	 * 登录时查询城市，区域，网点信息
	 * @param type
	 * @param name
	 * @return
	 */
	public JSONObject getCityAreaStation(int type, String name);
	
	/**
	 * 删除网点
	 * @param station
	 * @param type
	 * @param name
	 * @return
	 */
	public int removeStation(String station, int type, String name);
}
