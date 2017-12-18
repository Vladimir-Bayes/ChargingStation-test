package com.terabits.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.terabits.meta.bo.AccountAreaInfoBO;
import com.terabits.meta.bo.AccountCityInfoBO;
import com.terabits.meta.po.AdminPO;
import com.terabits.service.AccountService;
import com.terabits.service.AdminService;
import com.terabits.utils.JWT;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class AccountController {

	@Autowired
	private AdminService adminService;
	@Autowired
	private AccountService accountService;
	
	@RequestMapping(value = "/account/city/info",method = RequestMethod.POST)
    public @ResponseBody
    JSONObject accountCityInfo(@RequestParam(value = "Authorization") String clientToken, 
    		HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int type = 0;
		String city = request.getParameter("city");
		String phone = request.getParameter("phone");
		String name = request.getParameter("name");
		
		if (city != null) {
			city = new String(city.getBytes("8859_1"), "utf8");
		}
		
		if (name != null) {
			name = new String(name.getBytes("8859_1"), "utf8");
		}
		
		if (request.getParameter("type") != null) {
			type = Integer.valueOf(request.getParameter("type"));
		}
		
		AdminPO adminPO = JWT.unsign(clientToken, AdminPO.class);
		if (adminPO == null) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", 0);
			return jsonObject;
		}
		if (!adminService.authConfirm(adminPO.getType(), "/account/city/info")) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", 0);
			return jsonObject;
		}
		AccountCityInfoBO accountCityInfoBO = new AccountCityInfoBO();
		accountCityInfoBO.setCity(city);
		accountCityInfoBO.setCurrentName(adminPO.getName());
		accountCityInfoBO.setCurrentType(adminPO.getType());
		accountCityInfoBO.setName(name);
		accountCityInfoBO.setPhone(phone);
		accountCityInfoBO.setType(type);
		JSONArray info = accountService.getAccountCityInfo(accountCityInfoBO);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", 1);
		jsonObject.put("info", info);
		return jsonObject;
    }
	
	@RequestMapping(value = "/account/city/add",method = RequestMethod.POST)
    public @ResponseBody
    JSONObject accountCityAdd(@RequestParam(value = "Authorization") String clientToken, 
    		@RequestParam(value = "name") String name, 
    		@RequestParam(value = "password") String password, 
    		@RequestParam(value = "phone") String phone, 
    		@RequestParam(value = "type") Integer type, 
    		@RequestParam(value = "city") String city, 
    		@RequestParam(value = "email") String email, 
    		HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		/*if (name != null) {
			name = new String(name.getBytes("8859_1"), "utf8");
		}
		
		if (city != null) {
			city = new String(city.getBytes("8859_1"), "utf8");
		}*/
		
		AdminPO adminPO = JWT.unsign(clientToken, AdminPO.class);
		if (adminPO == null) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", 0);
			return jsonObject;
		}
		if (!adminService.authConfirm(adminPO.getType(), "/account/city/add")) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", 0);
			return jsonObject;
		}
		JSONObject jsonObject = new JSONObject();
		AdminPO cityAdminPO = new AdminPO();
		cityAdminPO.setCity(city);
		cityAdminPO.setEmail(email);
		cityAdminPO.setName(name);
		cityAdminPO.setPassword(password);
		cityAdminPO.setPhone(phone);
		cityAdminPO.setType(type);
		int result = accountService.addAccountCityInfo(adminPO.getName(), cityAdminPO);
		jsonObject.put("status", result);
		return jsonObject;
    }
	
	@RequestMapping(value = "/account/city/modify",method = RequestMethod.POST)
    public @ResponseBody
    JSONObject accountCityModify(@RequestParam(value = "Authorization") String clientToken, 
    		@RequestParam(value = "phone") String phone,   
    		HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String city = request.getParameter("city");
		String email = request.getParameter("email");
		Integer type = null;
		if (request.getParameter("type") != null) {
			type = Integer.valueOf(request.getParameter("type"));
		}
		if (city != null) {
			city = new String(city.getBytes("8859_1"), "utf8");
		}
		
		AdminPO adminPO = JWT.unsign(clientToken, AdminPO.class);
		if (adminPO == null) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", 0);
			return jsonObject;
		}
		if (!adminService.authConfirm(adminPO.getType(), "/account/city/modify")) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", 0);
			return jsonObject;
		}
		JSONObject jsonObject = new JSONObject();
		AdminPO cityAdminPO = new AdminPO();
		cityAdminPO.setCity(city);
		cityAdminPO.setEmail(email);
		cityAdminPO.setPhone(phone);
		cityAdminPO.setType(type);
		int result = accountService.modifyAccountCityInfo(adminPO.getName(), cityAdminPO);
		jsonObject.put("status", result);
		return jsonObject;
    }
	
	@RequestMapping(value = "/account/city/remove",method = RequestMethod.POST)
    public @ResponseBody
    JSONObject accountCityRemove(@RequestParam(value = "Authorization") String clientToken, 
    		@RequestParam(value = "phone") String phone, 
    		HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		AdminPO adminPO = JWT.unsign(clientToken, AdminPO.class);
		if (adminPO == null) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", 0);
			return jsonObject;
		}
		if (!adminService.authConfirm(adminPO.getType(), "/account/city/remove")) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", 0);
			return jsonObject;
		}
		JSONObject jsonObject = new JSONObject();
		int result = accountService.removeAccountCityInfo(adminPO.getName(), phone);
		jsonObject.put("status", result);
		return jsonObject;
    }
	
	@RequestMapping(value = "/account/area/info",method = RequestMethod.POST)
    public @ResponseBody
    JSONObject accountAreaInfo(@RequestParam(value = "Authorization") String clientToken, 
    		HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int type = 0;
		String city = request.getParameter("city");
		String phone = request.getParameter("phone");
		String name = request.getParameter("name");
		
		if (name != null) {
			name = new String(name.getBytes("8859_1"), "utf8");
		}
		
		if (city != null) {
			city = new String(city.getBytes("8859_1"), "utf8");
		}
		
		if (request.getParameter("type") != null) {
			type = Integer.valueOf(request.getParameter("type"));
		}
		
		AdminPO adminPO = JWT.unsign(clientToken, AdminPO.class);
		if (adminPO == null) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", 0);
			return jsonObject;
		}
		if (!adminService.authConfirm(adminPO.getType(), "/account/area/info")) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", 0);
			return jsonObject;
		}
		AccountAreaInfoBO accountAreaInfoBO = new AccountAreaInfoBO();
		accountAreaInfoBO.setCity(city);
		accountAreaInfoBO.setCurrentName(adminPO.getName());
		accountAreaInfoBO.setCurrentType(adminPO.getType());
		accountAreaInfoBO.setName(name);
		accountAreaInfoBO.setPhone(phone);
		accountAreaInfoBO.setType(type);
		JSONArray info = accountService.getAccountAreaInfo(accountAreaInfoBO);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", 1);
		jsonObject.put("info", info);
		return jsonObject;
    }
	
	@RequestMapping(value = "/account/area/modify",method = RequestMethod.POST)
    public @ResponseBody
    JSONObject accountAreaModify(@RequestParam(value = "Authorization") String clientToken, 
    		@RequestParam(value = "phone") String phone, 
    		HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String city = request.getParameter("city");
		String email = request.getParameter("email");
		Integer type = null;
		if (request.getParameter("type") != null) {
			type = Integer.valueOf(request.getParameter("type"));
		}
		/*if (city != null) {
			city = new String(city.getBytes("8859_1"), "utf8");
		}*/
		
		AdminPO adminPO = JWT.unsign(clientToken, AdminPO.class);
		if (adminPO == null) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", 0);
			return jsonObject;
		}
		if (!adminService.authConfirm(adminPO.getType(), "/account/area/modify")) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", 0);
			return jsonObject;
		}
		AdminPO areaAdminPO = new AdminPO();
		areaAdminPO.setCity(city);
		areaAdminPO.setEmail(email);
		areaAdminPO.setType(type);
		areaAdminPO.setPhone(phone);
		JSONObject jsonObject = new JSONObject();
		int result = accountService.modifyAccountArea(adminPO.getName(), areaAdminPO);
		jsonObject.put("status", result);
		return jsonObject;
    }
	
	@RequestMapping(value = "/account/area/station/add",method = RequestMethod.POST)
    public @ResponseBody
    JSONObject accountAreaStationAdd(@RequestParam(value = "Authorization") String clientToken, 
    		@RequestParam(value = "phone") String phone, 
    		@RequestParam(value = "station") JSONArray stationS, 
    		HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		AdminPO adminPO = JWT.unsign(clientToken, AdminPO.class);
		if (adminPO == null) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", 0);
			return jsonObject;
		}
		if (!adminService.authConfirm(adminPO.getType(), "/account/area/station/add")) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", 0);
			return jsonObject;
		}
		JSONObject jsonObject = accountService.addAccountAreaSite(adminPO.getName(), phone, stationS);
		return jsonObject;
    }
	
	@RequestMapping(value = "/account/area/station/remove",method = RequestMethod.POST)
    public @ResponseBody
    JSONObject accountAreaStationRemove(@RequestParam(value = "Authorization") String clientToken, 
    		@RequestParam(value = "phone") String phone, 
    		@RequestParam(value = "station") JSONArray stationS, 
    		HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		AdminPO adminPO = JWT.unsign(clientToken, AdminPO.class);
		if (adminPO == null) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", 0);
			return jsonObject;
		}
		if (!adminService.authConfirm(adminPO.getType(), "/account/area/station/remove")) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", 0);
			return jsonObject;
		}
		JSONObject jsonObject = accountService.removeAccountAreaSite(adminPO.getName(), phone, stationS);
		return jsonObject;
    }
	
	@RequestMapping(value = "/account/area/add",method = RequestMethod.POST)
    public @ResponseBody
    JSONObject accountAreaAdd(@RequestParam(value = "Authorization") String clientToken, 
    		@RequestParam(value = "name") String name, 
    		@RequestParam(value = "password") String password, 
    		@RequestParam(value = "phone") String phone, 
    		@RequestParam(value = "type") Integer type, 
    		@RequestParam(value = "city") String city, 
    		@RequestParam(value = "email") String email,
    		@RequestParam(value = "station") String stationS,
    		HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		AdminPO adminPO = JWT.unsign(clientToken, AdminPO.class);
		if (adminPO == null) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", 0);
			return jsonObject;
		}
		if (!adminService.authConfirm(adminPO.getType(), "/account/area/station/add")) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", 0);
			return jsonObject;
		}
		List<String> stationList = new ArrayList<String>();
		if (stationS != null) {
			String[] stationArray = stationS.split(",");
			for (String station : stationArray) {
				stationList.add(station);
			}
		}
		
		AdminPO areaAdminPO = new AdminPO();
		areaAdminPO.setCity(city);
		areaAdminPO.setEmail(email);
		areaAdminPO.setName(name);
		areaAdminPO.setPassword(password);
		areaAdminPO.setPhone(phone);
		areaAdminPO.setType(type);
		JSONObject jsonObject = accountService.addAccountArea(adminPO.getName(), areaAdminPO, stationList);
		return jsonObject;
    }
	
	@RequestMapping(value = "/account/area/remove",method = RequestMethod.POST)
    public @ResponseBody
    JSONObject accountAreaRemove(@RequestParam(value = "Authorization") String clientToken, 
    		@RequestParam(value = "phone") String phone,
    		HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		AdminPO adminPO = JWT.unsign(clientToken, AdminPO.class);
		if (adminPO == null) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", 0);
			return jsonObject;
		}
		if (!adminService.authConfirm(adminPO.getType(), "/account/area/station/remove")) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", 0);
			return jsonObject;
		}
		JSONObject jsonObject = new JSONObject();
		int result = accountService.removeAccountArea(adminPO.getName(), phone);
		jsonObject.put("status", result);
		return jsonObject;
    }
}
