package com.terabits.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.terabits.meta.po.AdminPO;
import com.terabits.service.AdminService;
import com.terabits.service.UserService;
import com.terabits.utils.JWT;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@Controller
public class UserController {
	@Autowired
	private AdminService adminService;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/user/consumption/query", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject user_consumption(
//			@RequestParam(value="Authorization") String clientToken, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {		
//		
//		AdminPO adminPO = JWT.unsign(clientToken, AdminPO.class);
//		
//		if (adminPO == null) {
//			JSONObject jsonObject = new JSONObject();
//			jsonObject.put("status", 0);
//			return jsonObject;
//		}
//		if (!adminService.authConfirm(adminPO.getType(), "/user/consumption")) {
//			JSONObject jsonObject = new JSONObject();
//			jsonObject.put("status", 0);
//			return jsonObject;
//		}
		String phoneString = request.getParameter("phone");
		String beginTimeString = request.getParameter("beginTime");
		String endTimeString = request.getParameter("endTime");
		String currentPageString = request.getParameter("currentPage");
		String pageSizeString = request.getParameter("pageSize");
		
		int currentPage=0;
		int pageSize=0;
		int offset = 0;
		
		if (currentPageString!=null && !currentPageString.equals("")) {
			currentPage = Integer.valueOf(currentPageString);
		}
		if (pageSizeString!=null && !pageSizeString.equals("")) {
			pageSize = Integer.valueOf(pageSizeString);
		}
		if (currentPage<=0) {
			offset=-1;
		} else {
			offset = (currentPage-1)*pageSize;
		}
		
		JSONArray jsonArray = userService.getConsumes(beginTimeString, endTimeString, phoneString, pageSize, offset);
		long itemnum = userService.getItemsCount("t_charge_consumerecord", beginTimeString, endTimeString, phoneString);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", 1);
		jsonObject.put("itemcount", itemnum);
		jsonObject.put("info", jsonArray);
		return jsonObject;
				
	}
	
	@RequestMapping(value="/user/recharge/query", method=RequestMethod.POST)
	@ResponseBody
	public JSONObject user_recharge(
//			@RequestParam(value="Authorization") String clientToken, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
//		AdminPO adminPO = JWT.unsign(clientToken, AdminPO.class);
//		
//		if (adminPO == null) {
//			JSONObject jsonObject = new JSONObject();
//			jsonObject.put("status", 0);
//			return jsonObject;
//		}
//		if (!adminService.authConfirm(adminPO.getType(), "/user/recharge")) {
//			JSONObject jsonObject = new JSONObject();
//			jsonObject.put("status", 0);
//			return jsonObject;
//		}
		String phoneString = request.getParameter("phone");
		String beginTimeString = request.getParameter("beginTime");
		String endTimeString = request.getParameter("endTime");
		String currentPageString = request.getParameter("currentPage");
		String pageSizeString = request.getParameter("pageSize");
		int currentPage=0;
		int pageSize=0;
		int offset = 0;
		
		if (currentPageString!=null && !currentPageString.equals("")) {
			currentPage = Integer.valueOf(currentPageString);
		}
		
		if (pageSizeString!=null && !pageSizeString.equals("")) {
			pageSize = Integer.valueOf(pageSizeString);
		}
		
		
		if (currentPage<=0) {
			offset=-1;
		} else {
			offset = (currentPage-1)*pageSize;
		}

		JSONArray jsonArray= userService.getRecharges(beginTimeString, endTimeString, phoneString, pageSize, offset);
		long itemnum = userService.getItemsCount("t_charge_rechargerecord", beginTimeString, endTimeString, phoneString);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", 1);
		jsonObject.put("itemcount", itemnum);
		jsonObject.put("info", jsonArray);
		return jsonObject;
	}

	@RequestMapping(value="/user/recharge/check", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject user_recharge_check(
//			@RequestParam(value="Authorization") String clientToken,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
//		AdminPO adminPO = JWT.unsign(clientToken, AdminPO.class);	
//		if (adminPO == null) {
//			JSONObject jsonObject = new JSONObject();
//			jsonObject.put("status", 0);
//			return jsonObject;
//		}
//		if (!adminService.authConfirm(adminPO.getType(), "/user/recharge")) {
//			JSONObject jsonObject = new JSONObject();
//			jsonObject.put("status", 0);
//			return jsonObject;
//		}
		
		JSONObject jsonObject = new JSONObject();
		
		
		String phone = request.getParameter("phone");
		JSONArray jsonArray = new JSONArray();
		jsonArray = userService.getUserInfo(phone);
		if (jsonArray.isEmpty()) {
			jsonObject.put("status", 2);
			return jsonObject;
		}
		jsonObject.put("status", 1);
		jsonObject.put("info", jsonArray);
		return jsonObject;
	}
	
	
	@RequestMapping(value="/user/recharge", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject user_recharge_post(
			@RequestParam(value="Authorization") String clientToken,
			@RequestParam(value="money") int payment,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		

		AdminPO adminPO = JWT.unsign(clientToken, AdminPO.class);
		if (adminPO == null) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", 0);
			return jsonObject;
		}
		if (!adminService.authConfirm(adminPO.getType(), "/user/recharge")) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("status", 0);
			return jsonObject;
		}
		String phoneString = request.getParameter("phone");		
		String comments = request.getParameter("comments");
		String adminname = adminPO.getName();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("status", userService.insertPresent(phoneString, payment, comments, adminname));
		return jsonObject;
	}
}
