package com.terabits.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.mail.imap.protocol.Status;
import com.terabits.dao.DeviceDAO;
import com.terabits.dao.LogDAO;
import com.terabits.meta.bo.BoxConnectionBO;
import com.terabits.meta.bo.SiteInsertBO;
import com.terabits.meta.vo.BoxConnectionTendaysVO;
import com.terabits.meta.vo.BoxConnectionVO;
import com.terabits.meta.vo.BoxInfoVO;
import com.terabits.meta.vo.CityAreaStationVO;
import com.terabits.meta.vo.DeviceVO;
import com.terabits.service.DeviceService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class DeviceServiceImpl implements DeviceService {

	@Autowired
	private DeviceDAO deviceDAO;
	@Autowired
	private LogDAO logDAO;
	
	public JSONArray getDeviceInfo(String city, String area, String station, int type, String name) {
		List<DeviceVO> deviceVOs = deviceDAO.selectDeviceInfo(city, area, station, type, name);
		JSONArray info = JSONArray.fromObject(deviceVOs);
		return info;
	}

	public int insertSite(String name, SiteInsertBO siteInsertBO) {
		int result = deviceDAO.insertSite(siteInsertBO);
		if (result == 200) {
			logDAO.insertLog(name, 5, "添加网点：" + siteInsertBO.getStation());
			return 1;
		}
		else {
			return 2;
		}
	}

	public JSONArray getBoxSiteList(String station) {
		List<String> boxIds = deviceDAO.selectBoxSiteList(station);
		JSONArray info = new JSONArray();
		for (String boxId : boxIds) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("boxId", boxId);
			info.add(jsonObject);
		}
		return info;
	}

	public JSONArray getBoxConnection(BoxConnectionBO boxConnectionBO) {
		List<BoxInfoVO> boxInfoVOs = deviceDAO.selectBoxInfo(boxConnectionBO);
		JSONArray infoArray = new JSONArray();
		for (BoxInfoVO boxInfoVO : boxInfoVOs) {
			int connectCount = 0;
			JSONObject infoObject = new JSONObject();
			JSONArray connectDetailArray = new JSONArray();
			infoObject.put("imei", boxInfoVO.getImei());
			infoObject.put("status", boxInfoVO.getStatus());
			infoObject.put("city", boxInfoVO.getCity());
			infoObject.put("area", boxInfoVO.getArea());
			infoObject.put("phone", boxInfoVO.getPhone());
			infoObject.put("station", boxInfoVO.getStation());
			infoObject.put("version", boxInfoVO.getVersion());
			infoObject.put("type", boxInfoVO.getType());
			List<BoxConnectionVO> boxConnectionVOs = deviceDAO.selectBoxConnection(boxInfoVO.getImei(), boxInfoVO.getStation(), boxConnectionBO.getBeginTime(), boxConnectionBO.getEndTime());
			for (BoxConnectionVO boxConnectionVO : boxConnectionVOs) {
				JSONObject connectDetailObject = new JSONObject();
				connectDetailObject.put("time", boxConnectionVO.getTime());
				connectDetailObject.put("status", boxConnectionVO.getStatus());
				if (boxConnectionVO.getStatus() == 10) {
					connectCount++;
				}
				connectDetailArray.add(connectDetailObject);
			}
			if (connectCount >= boxConnectionBO.getConnectionCount()) {
				infoObject.put("connectDetail", connectDetailArray);
			}
		}
		return infoArray;
	}

	public JSONArray getBoxConnectionTendays(BoxConnectionBO boxConnectionBO) {
		List<BoxInfoVO> boxInfoVOs = deviceDAO.selectBoxInfo(boxConnectionBO);
		List<BoxConnectionTendaysVO> boxConnectionTendaysVOs = deviceDAO.selectBoxConnectionTendays(boxConnectionBO);
		HashMap<String, JSONObject> hashMap = new HashMap<String, JSONObject>();
		
		for (BoxInfoVO boxInfoVO : boxInfoVOs) {
			JSONObject info = new JSONObject();
			JSONArray jsonArray = new JSONArray();
			info.put("imei", boxInfoVO.getImei());
			info.put("status", boxInfoVO.getStatus());
			info.put("city", boxInfoVO.getCity());
			info.put("area", boxInfoVO.getArea());
			info.put("phone", boxInfoVO.getPhone());
			info.put("station", boxInfoVO.getStation());
			info.put("version", boxInfoVO.getVersion());
			info.put("type", boxInfoVO.getType());
			info.put("connectInfo", jsonArray);
			hashMap.put(boxInfoVO.getImei(), info);
		}
		
		for (BoxConnectionTendaysVO boxConnectionTendaysVO : boxConnectionTendaysVOs) {
			String time = "" + boxConnectionTendaysVO.getYear();
			if (boxConnectionTendaysVO.getMonth() < 10) {
				time += "0" + boxConnectionTendaysVO.getMonth();
			} else {
				time += boxConnectionTendaysVO.getMonth();
			}
			if (boxConnectionTendaysVO.getDay() < 10) {
				time += "0" + boxConnectionTendaysVO.getDay();
			} else {
				time += boxConnectionTendaysVO.getDay();
			}
			JSONObject connectionInfo = new JSONObject();
			connectionInfo.put("time", time);
			connectionInfo.put("count", boxConnectionTendaysVO.getCount());
			if (hashMap.containsKey(boxConnectionTendaysVO.getImei())) {
				JSONArray connectionInfoArray = hashMap.get(boxConnectionTendaysVO.getImei()).getJSONArray("connectInfo");
				connectionInfoArray.add(connectionInfo);
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

	public JSONArray getAllStationNoArea() {
		List<String> stationS = deviceDAO.selectAllStationNoArea();
		JSONArray jsonArray = new JSONArray();
		for (String station : stationS) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("station", station);
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}

	public JSONObject getCityAreaStation(int type, String name) {
		List<CityAreaStationVO> cityAreaStationVOs = deviceDAO.selectCityAreaStation(type, name);
		List<String> citys = new ArrayList<String>();
		HashMap<String, List<String>> area = new HashMap<String, List<String>>();
		HashMap<String, List<String>> station = new HashMap<String, List<String>>();
		for (CityAreaStationVO cityAreaStationVO : cityAreaStationVOs) {
			if (!citys.contains(cityAreaStationVO.getCity())) {
				citys.add(cityAreaStationVO.getCity());
			}
			if (cityAreaStationVO.getArea() != null) {
				if (area.containsKey(cityAreaStationVO.getCity())) {
					if (!area.get(cityAreaStationVO.getCity()).contains(cityAreaStationVO.getArea()))
						area.get(cityAreaStationVO.getCity()).add(cityAreaStationVO.getArea());
				} else {
					List<String> areaList = new ArrayList<String>();
					areaList.add(cityAreaStationVO.getArea());
					area.put(cityAreaStationVO.getCity(), areaList);
				}			
				if (station.containsKey(cityAreaStationVO.getArea())){
					if (!station.get(cityAreaStationVO.getArea()).contains(cityAreaStationVO.getStation()))
						station.get(cityAreaStationVO.getArea()).add(cityAreaStationVO.getStation());
				} else {
					List<String> stationList = new ArrayList<String>();
					stationList.add(cityAreaStationVO.getStation());
					station.put(cityAreaStationVO.getArea(), stationList);
				}
			}
		}
		JSONObject info = new JSONObject();
		info.put("city", citys);
		info.put("area", JSONArray.fromObject(area));
		info.put("station", JSONArray.fromObject(station));
		return info;
	}

	public int removeStation(String station, int type, String name) {
		//判断用户是否能管理此网点
		int count = deviceDAO.selectStationCheckAdmin(station, type, name);
		if (count == 0) {
			return 0;
		}
		//判断此网点下是否有未解绑的设备
		count = deviceDAO.selectStationCheckDevice(station, type, name);
		if (count != 0) {
			return 2;
		}
		//删除网点
		int result = deviceDAO.deleteStation(station);
		if (result == 200) {
			return 1;
		} else {
			return 3;
		}
	}
}
