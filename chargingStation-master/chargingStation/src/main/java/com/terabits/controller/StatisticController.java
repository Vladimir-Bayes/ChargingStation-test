package com.terabits.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.terabits.dao.IncomeDAO;
import com.terabits.meta.po.AdminPO;
import com.terabits.meta.po.DevicePO;
import com.terabits.meta.po.SitePO;
import com.terabits.service.IncomeService;
import com.terabits.utils.JWT;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class StatisticController {
	
	@Autowired
	private IncomeDAO incomeDao;
	@Autowired
	private IncomeService incomeService;
	
	@RequestMapping(value="/income/box",method = RequestMethod.POST)
	@ResponseBody
	public JSONObject terminalIncome(@RequestParam("Authorization") String token,
			                         //@RequestParam("testPara") String testPara,
									 @RequestParam(value="city",required=false) String city,
									 @RequestParam(value="adminName",required=false) String adminName,
									 @RequestParam(value="site",required=false) String site,
									 @RequestParam(value="imei",required=false) String imei,
									 @RequestParam(value="showStyle",required=false) int showStyle,
									 @RequestParam(value="beginTime",required=false) String beginTime,
									 @RequestParam(value="endTime",required=false) String endTime)throws Exception{
		//***********************************************************************************
		System.out.println(imei);
		JSONObject jsonObject=new JSONObject();
		AdminPO adminPO = JWT.unsign(token, AdminPO.class);
        System.out.println("adminPO:"+adminPO);
        if (adminPO == null){ //token锟斤拷锟斤拷锟矫伙拷校锟斤拷失锟斤拷
            jsonObject.put("status", 0);
            return jsonObject;
        }
        int type=adminPO.getType();
        String url="/income/box";
        if(!incomeService.authConfirm(type, url)){//3锟斤拷锟矫伙拷没锟叫革拷权锟斤拷
        	jsonObject.put("status", 2);
        	return jsonObject;
        }
        
        //********************************************************************************************
        //                         锟叫达拷锟斤拷锟�:锟斤拷锟饺拷锟斤拷锟斤拷狻拷锟絪ite锟斤拷imei锟斤拷锟矫伙拷锟街讹拷锟斤拷锟斤拷
        boolean flag=true;
        if(type==2||type==4){
        	List<SitePO> sitePOs=incomeDao.selectSitePOByCity(adminPO.getCity());
        	boolean f2=false;
        	for(SitePO sitePO:sitePOs){
        		if(site!=""&&sitePO.getName().equals(site))
        			f2=true;
        	}
        	if(site==""||site==null)
        		f2=true;
        	boolean f3=false;
        	for(SitePO sitePO:sitePOs){
        		List<DevicePO> devicePOs=incomeDao.selectDevicePOBySite(sitePO.getGid());
        		for(DevicePO devicePO:devicePOs){
        			if(imei!=""&&devicePO.getImei().equals(imei))
        				f3=true;
        		}
        	}
        	if(imei==""||imei==null)
        		f3=true;
            System.out.println("f2:"+f2+"  f3"+f3);
            flag=flag&&f2&&f3;
        }
        if(type==5){
        	List<SitePO> sitePOs=incomeDao.selectSitePOByAdminphone(adminPO.getName());
        	boolean f2=false;
        	for(SitePO sitePO:sitePOs){
        		if(site!=""&&sitePO.getName().equals(site))
        			f2=true;
        	}
        	if(site==""||site==null)
        		f2=true;
        	boolean f3=false;
        	for(SitePO sitePO:sitePOs){
        		List<DevicePO> devicePOs=incomeDao.selectDevicePOBySite(sitePO.getGid());
        		for(DevicePO devicePO:devicePOs){
        			if(imei!=""&&devicePO.getImei().equals(imei))
        				f3=true;
        		}
        	}
        	if(imei==""||imei==null)
        		f3=true;
            System.out.println("f2:"+f2+"  f3"+f3);
            flag=flag&&f2&&f3;
        }
        System.out.println(flag);
        if(!flag)
        {
        	jsonObject.put("status", 0);
        	return jsonObject;
        }
        
        
        JSONArray jsonArray=incomeService.deviceJSONArray(imei, site, adminName, city, beginTime, endTime, showStyle);
        if(jsonArray==null)
        	jsonObject.put("status", 0);
        else{
        	jsonObject.put("status", 1);
        	jsonObject.put("info", jsonArray);
        }
		return jsonObject;
	}
	
	@PostMapping("/income/station")
	@ResponseBody
	public JSONObject siteManager(@RequestParam("Authorization") String token,
			//@RequestParam("testPara") String testPara,
			 @RequestParam(value="city",required=false) String city,
			 @RequestParam(value="adminName",required=false) String adminName,
			 @RequestParam(value="site",required=false) String site,
			 @RequestParam(value="showStyle",required=false) int showStyle,
			 @RequestParam(value="beginTime",required=false) String beginTime,
			 @RequestParam(value="endTime",required=false) String endTime) throws Exception{
		JSONObject jsonObject=new JSONObject();
		AdminPO adminPO = JWT.unsign(token, AdminPO.class);
		//AdminPO adminPO=incomeDao.selectAdminPOByAdminName(testPara);
        System.out.print(adminPO);
        if (adminPO == null){ //token锟斤拷锟斤拷锟矫伙拷校锟斤拷失锟斤拷
            jsonObject.put("status", 0);
            return jsonObject;
        }
        int type=adminPO.getType();
        String url="/income/station";
        if(!incomeService.authConfirm(type, url)){//3锟斤拷锟矫伙拷没锟叫革拷权锟斤拷
        	jsonObject.put("status", 2);
        	return jsonObject;
        }
        
        
        //********************************************************************************************
        //                         锟叫达拷锟斤拷锟�
        boolean flag=true;
        if(type==2||type==4){
        	List<SitePO> sitePOs=incomeDao.selectSitePOByCity(adminPO.getCity());
        	boolean f2=false;
        	for(SitePO sitePO:sitePOs){
        		if(site!=""&&sitePO.getName().equals(site))
        			f2=true;
        	}
        	if(site==""||site==null)
        		f2=true;
            System.out.println("f2:"+f2);
            flag=flag&&f2;
        }
        if(type==5){
        	List<SitePO> sitePOs=incomeDao.selectSitePOByAdminphone(adminPO.getName());
        	boolean f2=false;
        	for(SitePO sitePO:sitePOs){
        		if(site!=""&&sitePO.getName().equals(site))
        			f2=true;
        	}
        	if(site==""||site==null)
        		f2=true;
            System.out.println("f2:"+f2);
            flag=flag&&f2;
        }
        if(!flag)
        {
        	jsonObject.put("status", 0);
        	return jsonObject;
        }
        
        
        JSONArray jsonArray=incomeService.siteJSONArray(site, adminName, city, beginTime, endTime, showStyle);
        if(jsonArray==null)
        	jsonObject.put("status", 0);
        else{
        	jsonObject.put("status", 1);
        	jsonObject.put("info", jsonArray);
        }
		return jsonObject;
	}
	
	@PostMapping("/income/area")
	@ResponseBody
	public JSONObject adminManager(@RequestParam("Authorization") String token,
			 //@RequestParam("testPara") String testPara,
			 @RequestParam(value="city",required=false) String city,
			 @RequestParam(value="adminName",required=false) String adminName,
			 @RequestParam(value="showStyle",required=false) int showStyle,
			 @RequestParam(value="beginTime",required=false) String beginTime,
			 @RequestParam(value="endTime",required=false) String endTime) throws Exception{
		JSONObject jsonObject=new JSONObject();
		AdminPO adminPO = JWT.unsign(token, AdminPO.class);
		//AdminPO adminPO=incomeDao.selectAdminPOByAdminName(testPara);
        System.out.print(adminPO);
        if (adminPO == null){ //token锟斤拷锟斤拷锟矫伙拷校锟斤拷失锟斤拷
            jsonObject.put("status", 0);
            return jsonObject;
        }
        int type=adminPO.getType();
        String url="/income/area";
        if(!incomeService.authConfirm(type, url)){//3锟斤拷锟矫伙拷没锟叫革拷权锟斤拷
        	jsonObject.put("status", 2);
        	return jsonObject;
        }
        JSONArray jsonArray=incomeService.adminJSONArray(adminName, city, beginTime, endTime, showStyle);
        if(jsonArray==null)
        	jsonObject.put("status", 0);
        else{
        	jsonObject.put("status", 1);
        	jsonObject.put("info", jsonArray);
        }
        return jsonObject;
	}
	
	@PostMapping("/income/city")
	@ResponseBody
	public JSONObject cityManager(@RequestParam("Authorization") String token,
			 //@RequestParam("testPara") String testPara,
			 @RequestParam(value="city",required=false) String city,
			 @RequestParam(value="showStyle",required=false) int showStyle,
			 @RequestParam(value="beginTime",required=false) String beginTime,
			 @RequestParam(value="endTime",required=false) String endTime) throws Exception{
		JSONObject jsonObject=new JSONObject();
		AdminPO adminPO = JWT.unsign(token, AdminPO.class);
		//AdminPO adminPO=incomeDao.selectAdminPOByAdminName(testPara);
        System.out.print(adminPO);
        if (adminPO == null){ //token锟斤拷锟斤拷锟矫伙拷校锟斤拷失锟斤拷
            jsonObject.put("status", 0);
            return jsonObject;
        }
        int type=adminPO.getType();
        String url="/income/city";
        if(!incomeService.authConfirm(type, url)){//3锟斤拷锟矫伙拷没锟叫革拷权锟斤拷
        	jsonObject.put("status", 2);
        	return jsonObject;
        }
        JSONArray jsonArray=incomeService.cityJSONArray(city, beginTime, endTime, showStyle);
        if(jsonArray==null)
        	jsonObject.put("status", 0);
        else{
        	jsonObject.put("status", 1);
        	jsonObject.put("info", jsonArray);
        }
        return jsonObject;
	}
}
