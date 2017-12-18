package com.terabits.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.terabits.meta.bo.BoxConnectionBO;
import com.terabits.meta.bo.SiteInsertBO;
import com.terabits.meta.po.AdminPO;
import com.terabits.service.AdminService;
import com.terabits.service.DeviceService;
import com.terabits.utils.JWT;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class DeviceController {
	
	@Autowired
	private AdminService adminService;
	@Autowired
	private DeviceService deviceService;
	
	@RequestMapping(value = "/station/info",method = RequestMethod.POST)
    public @ResponseBody
    JSONObject boxInfo(@RequestParam(value = "Authorization") String clientToken, 
    		HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String city = request.getParameter("city");
		String area = request.getParameter("area");
		String station = request.getParameter("station");
		
		if (city != null) {
			city = new String(city.getBytes("8859_1"), "utf8");
		}
		
		if (area != null) {
			area = new String(area.getBytes("8859_1"), "utf8");
		}
		
		if (station != null) {
			station = new String(station.getBytes("8859_1"), "utf8");
		}
		
		AdminPO adminPO = JWT.unsign(clientToken, AdminPO.class);
		if (adminPO == null) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", 0);
			return jsonObject;
		}
		if (!adminService.authConfirm(adminPO.getType(), "/station/info")) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", 0);
			return jsonObject;
		}
		JSONArray info = deviceService.getDeviceInfo(city, area, station, adminPO.getType(), adminPO.getName());
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", 1);
		jsonObject.put("info", info);
		return jsonObject;
    }
	
	@RequestMapping(value = "/station/create",method = RequestMethod.POST)
    public @ResponseBody
    JSONObject stationCreate(@RequestParam(value = "Authorization") String clientToken, 
    		@RequestParam(value = "city") String city,
    		@RequestParam(value = "station") String station,
    		@RequestParam(value = "longitude") Double longitude,
    		@RequestParam(value = "latitude") Double latitude,
    		@RequestParam(value = "adminname") String adminname,
    		@RequestParam(value = "adminphone") String adminphone,
    		HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String address = request.getParameter("address");
		
		if (city != null) {
			city = new String(city.getBytes("8859_1"), "utf8");
		}
		
		if (station != null) {
			station = new String(station.getBytes("8859_1"), "utf8");
		}
		
		if (adminname != null) {
			adminname = new String(adminname.getBytes("8859_1"), "utf8");
		}
		
		if (address != null) {
			address = new String(address.getBytes("8859_1"), "utf8");
		}
		
		AdminPO adminPO = JWT.unsign(clientToken, AdminPO.class);
		if (adminPO == null) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", 0);
			return jsonObject;
		}
		if (!adminService.authConfirm(adminPO.getType(), "/station/create")) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", 0);
			return jsonObject;
		}
		if (!adminPO.getCity().equals(city)) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", 0);
			return jsonObject;
		}
		SiteInsertBO siteInsertBO = new SiteInsertBO();
		siteInsertBO.setAddress(address);
		siteInsertBO.setCity(city);
		siteInsertBO.setLatitude(latitude);
		siteInsertBO.setLongitude(longitude);
		//deviceInsertBO.setCoordinate(coordinate);
		siteInsertBO.setAdminname(adminname);
		siteInsertBO.setAdminphone(adminphone);
		siteInsertBO.setStation(station);		
		int status = deviceService.insertSite(adminPO.getName(), siteInsertBO);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", status);
		return jsonObject;
    }
	
	@RequestMapping(value = "/station/remove",method = RequestMethod.POST)
    public @ResponseBody
    JSONObject stationRemove(@RequestParam(value = "Authorization") String clientToken, 
    		@RequestParam(value = "station") String station,
    		HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (station != null) {
			station = new String(station.getBytes("8859_1"), "utf8");
		}
		AdminPO adminPO = JWT.unsign(clientToken, AdminPO.class);
		if (adminPO == null) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", 0);
			return jsonObject;
		}
		if (!adminService.authConfirm(adminPO.getType(), "/station/remove")) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", 0);
			return jsonObject;
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", deviceService.removeStation(station, adminPO.getType(), adminPO.getName()));
		return jsonObject;
    }
	
	@RequestMapping(value = "/box/station/list",method = RequestMethod.POST)
    public @ResponseBody
    JSONObject boxStationList(@RequestParam(value = "Authorization") String clientToken, 
    		@RequestParam(value = "station") String station,
    		HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if (station != null) {
			station = new String(station.getBytes("8859_1"), "utf8");
		}
		
		AdminPO adminPO = JWT.unsign(clientToken, AdminPO.class);
		if (adminPO == null) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", 0);
			return jsonObject;
		}
		if (!adminService.authConfirm(adminPO.getType(), "/box/station/list")) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", 0);
			return jsonObject;
		}
		JSONArray info = deviceService.getBoxSiteList(station);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", 1);
		jsonObject.put("info", info);
		return jsonObject;
    }
	
	@RequestMapping(value = "/box/connection",method = RequestMethod.POST)
    public @ResponseBody
    JSONObject boxConnection(@RequestParam(value = "Authorization") String clientToken, 
    		HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String city = request.getParameter("city");
		String area = request.getParameter("area");
		String station = request.getParameter("station");
		Integer connectionCount = 0;
		String imei = request.getParameter("imei");
		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime");
		
		if (city != null) {
			city = new String(city.getBytes("8859_1"), "utf8");
		}
		
		if (area != null) {
			area = new String(area.getBytes("8859_1"), "utf8");
		}
		
		if (station != null) {
			station = new String(station.getBytes("8859_1"), "utf8");
		}
		
		if (endTime != null) {
			endTime += " 23:59:59";
		}
		
		if (request.getParameter("connectionCount") != null) {
			connectionCount = Integer.valueOf(request.getParameter("connectionCount"));
		}
		
		AdminPO adminPO = JWT.unsign(clientToken, AdminPO.class);
		if (adminPO == null) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", 0);
			return jsonObject;
		}
		if (!adminService.authConfirm(adminPO.getType(), "/box/connection")) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", 0);
			return jsonObject;
		}
		BoxConnectionBO boxConnectionBO = new BoxConnectionBO();
		boxConnectionBO.setArea(area);
		boxConnectionBO.setCity(city);
		boxConnectionBO.setImei(imei);
		boxConnectionBO.setStation(station);
		boxConnectionBO.setConnectionCount(connectionCount);
		boxConnectionBO.setBeginTime(beginTime);
		boxConnectionBO.setEndTime(endTime);
		boxConnectionBO.setType(adminPO.getType());
		boxConnectionBO.setName(adminPO.getName());
		JSONArray info = deviceService.getBoxConnection(boxConnectionBO);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", 1);
		jsonObject.put("info", info);
		return jsonObject;
    }
	
	@RequestMapping(value = "/box/connection/tendays",method = RequestMethod.POST)
    public @ResponseBody
    JSONObject boxConnectionTendays(@RequestParam(value = "Authorization") String clientToken, 
    		HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String city = request.getParameter("city");
		String area = request.getParameter("area");
		String station = request.getParameter("station");
		String imei = request.getParameter("imei");
		String beginTime = request.getParameter("beginTime");
		String endTime = request.getParameter("endTime");
		
		if (city != null) {
			city = new String(city.getBytes("8859_1"), "utf8");
		}
		
		if (area != null) {
			area = new String(area.getBytes("8859_1"), "utf8");
		}
		
		if (station != null) {
			station = new String(station.getBytes("8859_1"), "utf8");
		}
		
		if (endTime != null) {
			endTime += " 23:59:59";
		}
		
		AdminPO adminPO = JWT.unsign(clientToken, AdminPO.class);
		if (adminPO == null) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", 0);
			return jsonObject;
		}
		if (!adminService.authConfirm(adminPO.getType(), "/box/connection/tendays")) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", 0);
			return jsonObject;
		}
		BoxConnectionBO boxConnectionBO = new BoxConnectionBO();
		boxConnectionBO.setArea(area);
		boxConnectionBO.setBeginTime(beginTime);
		boxConnectionBO.setCity(city);
		boxConnectionBO.setImei(imei);
		boxConnectionBO.setEndTime(endTime);
		boxConnectionBO.setName(adminPO.getName());
		boxConnectionBO.setStation(station);
		boxConnectionBO.setType(adminPO.getType());
		JSONArray info = deviceService.getBoxConnectionTendays(boxConnectionBO);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", 1);
		jsonObject.put("info", info);
		return jsonObject;
    }
	
	@RequestMapping(value = "/query/station/noarea",method = RequestMethod.POST)
    public @ResponseBody
    JSONObject queryStationNoArea(@RequestParam(value = "Authorization") String clientToken, 
    		HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		AdminPO adminPO = JWT.unsign(clientToken, AdminPO.class);
		if (adminPO == null) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", 0);
			return jsonObject;
		}
		if (!adminService.authConfirm(adminPO.getType(), "/query/station/noarea")) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", 0);
			return jsonObject;
		}
		JSONArray info = deviceService.getAllStationNoArea();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", 1);
		jsonObject.put("info", info);
		return jsonObject;
    }
	
	@RequestMapping(value = "/query/city/area/station",method = RequestMethod.POST)
    public @ResponseBody
    JSONObject queryCityAreaStation(@RequestParam(value = "Authorization") String clientToken, 
    		HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		AdminPO adminPO = JWT.unsign(clientToken, AdminPO.class);
		if (adminPO == null) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", 0);
			return jsonObject;
		}
		JSONObject info = deviceService.getCityAreaStation(adminPO.getType(), adminPO.getName());
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", 1);
		jsonObject.put("info", info);
		return jsonObject;
    }
}
