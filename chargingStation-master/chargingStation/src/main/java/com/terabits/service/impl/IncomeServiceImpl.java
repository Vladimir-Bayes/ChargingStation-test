package com.terabits.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.terabits.dao.AdminDAO;
import com.terabits.dao.IncomeDAO;
import com.terabits.meta.po.AdminPO;
import com.terabits.meta.po.DevicePO;
import com.terabits.meta.po.TerminalstatisticPO;
import com.terabits.service.IncomeService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class IncomeServiceImpl implements IncomeService {

	@Autowired
	private AdminDAO adminDAO;
	@Autowired
	private IncomeDAO incomeDAO;

	public JSONArray deviceJSONArray(String imei,String sitename,String adminname,String city,String beginTime,String endTime,int showStyle){
		JSONArray jsonArray=new JSONArray();
    	int totalTurnover=0;
    	int totalCount=0;
    	int wechatTurnover=0;
    	int wechatCount=0;
    	int coinTurnover=0;
    	int coinCount=0;
		//terminalstatisticPOs按照imei号已经排好序
		List<TerminalstatisticPO> terminalstatisticPOs=incomeDAO.selectStatisticPOByImei(imei, sitename, adminname, city, beginTime, endTime);
		if(terminalstatisticPOs.size()==0)
			return null;
		for(TerminalstatisticPO terminalstatisticPO:terminalstatisticPOs)
			System.out.println(terminalstatisticPO);
		if(showStyle==1){//按条显示
			for(TerminalstatisticPO terminalstatisticPO:terminalstatisticPOs){
					System.out.println(terminalstatisticPO);
					JSONObject jsonTmp=new JSONObject();
					jsonTmp.put("city", terminalstatisticPO.getCity());
	            	jsonTmp.put("adminname", terminalstatisticPO.getAdminname());
	            	System.out.println(terminalstatisticPO.getAdminname());
	            	AdminPO adminPO=incomeDAO.selectAdminPOByAdminName(terminalstatisticPO.getAdminname());
	            	System.out.println(adminPO);
	            	jsonTmp.put("adminphone", adminPO.getPhone());
	            	jsonTmp.put("site", terminalstatisticPO.getSitename());
	            	jsonTmp.put("imei", terminalstatisticPO.getImei());
	            	DevicePO devicePO=incomeDAO.selectDevicePOByImei(terminalstatisticPO.getImei());
	            	String selfnumber=devicePO.getSelfnumber()+devicePO.getType()+devicePO.getImei().substring(devicePO.getImei().length()-5);
	            	jsonTmp.put("selfnumber", selfnumber);
	    			jsonTmp.put("time", terminalstatisticPO.getGmtcreate());
	    			jsonTmp.put("totalTurnover",terminalstatisticPO.getTotalincome());
	        		jsonTmp.put("totalCount", terminalstatisticPO.getTotalnumber());
	        		jsonTmp.put("wechatTurnover",terminalstatisticPO.getWechatincome() );
	        		jsonTmp.put("wechatCount", terminalstatisticPO.getWechatnumber());
	        		jsonTmp.put("coinTurnover",terminalstatisticPO.getCoinincome());
	        		jsonTmp.put("coinCount", terminalstatisticPO.getCoinnumber());
	        		System.out.println(jsonTmp);
	        		jsonArray.add(jsonTmp);
				}
    	}else{/*order by imei,gmtcreate
    	设备一        11.01
    	设备一        11.02
    	设备一        11.03
    	设备二        11.01
    	设备二        11.02
    	设备二         11.03*/
        	String imeiTmp=terminalstatisticPOs.get(0).getImei();
        	int i=0,size=terminalstatisticPOs.size();
        	while(i<size){
        		TerminalstatisticPO terminalstatisticPO=new TerminalstatisticPO();
        		while (i<size&&terminalstatisticPOs.get(i).getImei().equals(imeiTmp)) {
            		terminalstatisticPO=terminalstatisticPOs.get(i);
        			totalTurnover+=terminalstatisticPO.getTotalincome();
            		totalCount+=terminalstatisticPO.getTotalnumber();
            		wechatTurnover+=terminalstatisticPO.getWechatincome();
            		wechatCount+=terminalstatisticPO.getWechatnumber();
            		coinTurnover+=terminalstatisticPO.getCoinincome();
            		coinCount+=terminalstatisticPO.getCoinnumber();
        			i++;
				}

        		JSONObject jsonTmp=new JSONObject();
				jsonTmp.put("city", terminalstatisticPO.getCity());
            	jsonTmp.put("adminname", terminalstatisticPO.getAdminname());
            	AdminPO adminPO=incomeDAO.selectAdminPOByAdminName(terminalstatisticPO.getAdminname());
            	jsonTmp.put("adminphone", adminPO.getPhone());
            	jsonTmp.put("site", terminalstatisticPO.getSitename());
            	jsonTmp.put("imei", terminalstatisticPO.getImei());
            	DevicePO devicePO=incomeDAO.selectDevicePOByImei(terminalstatisticPO.getImei());
            	String selfnumber=devicePO.getSelfnumber()+devicePO.getType()+devicePO.getImei().substring(devicePO.getImei().length()-5);
            	jsonTmp.put("selfnumber", selfnumber);
    			jsonTmp.put("totalTurnover",totalTurnover);
        		jsonTmp.put("totalCount", totalCount);
        		jsonTmp.put("wechatTurnover",wechatTurnover );
        		jsonTmp.put("wechatCount", wechatCount);
        		jsonTmp.put("coinTurnover",coinTurnover);
        		jsonTmp.put("coinCount",coinCount);
        		jsonArray.add(jsonTmp);

        		totalTurnover=0;
            	totalCount=0;
            	wechatTurnover=0;
            	wechatCount=0;
            	coinTurnover=0;
            	coinCount=0;
            	if(i<size){
            		imeiTmp=terminalstatisticPOs.get(i).getImei();
            	}
        	}
    	}
		return jsonArray;
	}
	
	public JSONArray siteJSONArray(String sitename,String adminname,String city,String beginTime,String endTime,int showStyle){
		int totalTurnover=0;
    	int totalCount=0;
    	int wechatTurnover=0;
    	int wechatCount=0;
    	int coinTurnover=0;
    	int coinCount=0;
		JSONArray jsonArray=new JSONArray();
		List<TerminalstatisticPO> terminalstatisticPOs=incomeDAO.selectStatisticPOBySite(sitename, adminname, city, beginTime, endTime);
		if(terminalstatisticPOs.size()==0)
			return null;
		if(showStyle==1){//按条显示
        	int i=0,size=terminalstatisticPOs.size();
			String gmtTmp=terminalstatisticPOs.get(i).getGmtcreate();
			String siteTmp=terminalstatisticPOs.get(i).getSitename();
        	while(i<size){
        		TerminalstatisticPO terminalstatisticPO=new TerminalstatisticPO();
        		while (i<size&&terminalstatisticPOs.get(i).getGmtcreate().equals(gmtTmp)&&terminalstatisticPOs.get(i).getSitename().equals(siteTmp)) {
            		terminalstatisticPO=terminalstatisticPOs.get(i);
        			totalTurnover+=terminalstatisticPO.getTotalincome();
            		totalCount+=terminalstatisticPO.getTotalnumber();
            		wechatTurnover+=terminalstatisticPO.getWechatincome();
            		wechatCount+=terminalstatisticPO.getWechatnumber();
            		coinTurnover+=terminalstatisticPO.getCoinincome();
            		coinCount+=terminalstatisticPO.getCoinnumber();
        			i++;
				}
        		System.out.println(i);
        		JSONObject jsonTmp=new JSONObject();
				jsonTmp.put("city", terminalstatisticPO.getCity());
            	jsonTmp.put("adminname", terminalstatisticPO.getAdminname());
            	AdminPO adminPO=incomeDAO.selectAdminPOByAdminName(terminalstatisticPO.getAdminname());
            	jsonTmp.put("adminphone", adminPO.getPhone());
            	jsonTmp.put("site", terminalstatisticPO.getSitename());
    			jsonTmp.put("time", terminalstatisticPO.getGmtcreate());
    			jsonTmp.put("totalTurnover",totalTurnover);
        		jsonTmp.put("totalCount", totalCount);
        		jsonTmp.put("wechatTurnover",wechatTurnover );
        		jsonTmp.put("wechatCount", wechatCount);
        		jsonTmp.put("coinTurnover",coinTurnover);
        		jsonTmp.put("coinCount",coinCount);
        		System.out.println(jsonTmp);
        		jsonArray.add(jsonTmp);

        		totalTurnover=totalCount=wechatTurnover=wechatCount=coinTurnover=coinCount=0;
            	if(i<size){
            		gmtTmp=terminalstatisticPOs.get(i).getGmtcreate();
    				siteTmp=terminalstatisticPOs.get(i).getSitename();
            	}
        	}
    	}else{

        	String Tmp=terminalstatisticPOs.get(0).getSitename();
        	int i=0,size=terminalstatisticPOs.size();
        	while(i<size){
        		TerminalstatisticPO terminalstatisticPO=new TerminalstatisticPO();
        		while (i<size&&terminalstatisticPOs.get(i).getSitename().equals(Tmp)) {
            		terminalstatisticPO=terminalstatisticPOs.get(i);
        			totalTurnover+=terminalstatisticPO.getTotalincome();
            		totalCount+=terminalstatisticPO.getTotalnumber();
            		wechatTurnover+=terminalstatisticPO.getWechatincome();
            		wechatCount+=terminalstatisticPO.getWechatnumber();
            		coinTurnover+=terminalstatisticPO.getCoinincome();
            		coinCount+=terminalstatisticPO.getCoinnumber();
        			i++;
				}

        		JSONObject jsonTmp=new JSONObject();
				jsonTmp.put("city", terminalstatisticPO.getCity());
            	jsonTmp.put("adminname", terminalstatisticPO.getAdminname());
            	AdminPO adminPO=incomeDAO.selectAdminPOByAdminName(terminalstatisticPO.getAdminname());
            	jsonTmp.put("adminphone", adminPO.getPhone());
            	jsonTmp.put("site", terminalstatisticPO.getSitename());
    			jsonTmp.put("totalTurnover",totalTurnover);
        		jsonTmp.put("totalCount", totalCount);
        		jsonTmp.put("wechatTurnover",wechatTurnover );
        		jsonTmp.put("wechatCount", wechatCount);
        		jsonTmp.put("coinTurnover",coinTurnover);
        		jsonTmp.put("coinCount",coinCount);
        		jsonArray.add(jsonTmp);

        		totalTurnover=0;
            	totalCount=0;
            	wechatTurnover=0;
            	wechatCount=0;
            	coinTurnover=0;
            	coinCount=0;
            	if(i<size){
            		Tmp=terminalstatisticPOs.get(i).getSitename();
            	}
        	}
    	}
		return jsonArray;
	}
	
	public JSONArray adminJSONArray(String adminname,String city,String beginTime,String endTime,int showStyle){
		int totalTurnover=0;
    	int totalCount=0;
    	int wechatTurnover=0;
    	int wechatCount=0;
    	int coinTurnover=0;
    	int coinCount=0;
		JSONArray jsonArray=new JSONArray();
		List<TerminalstatisticPO> terminalstatisticPOs=incomeDAO.selectStatisticPOByAdmin(adminname, city, beginTime, endTime);
		if(terminalstatisticPOs.size()==0)
			return null;
		if(showStyle==1){//按条显示
        	int i=0,size=terminalstatisticPOs.size();
			String Tmp=terminalstatisticPOs.get(i).getGmtcreate();
			String adminTmp=terminalstatisticPOs.get(i).getAdminname();
        	while(i<size){
        		TerminalstatisticPO terminalstatisticPO=new TerminalstatisticPO();
        		while (i<size&&terminalstatisticPOs.get(i).getGmtcreate().equals(Tmp)&&terminalstatisticPOs.get(i).getAdminname().equals(adminTmp)) {
            		terminalstatisticPO=terminalstatisticPOs.get(i);
        			totalTurnover+=terminalstatisticPO.getTotalincome();
            		totalCount+=terminalstatisticPO.getTotalnumber();
            		wechatTurnover+=terminalstatisticPO.getWechatincome();
            		wechatCount+=terminalstatisticPO.getCoinnumber();
            		coinTurnover+=terminalstatisticPO.getCoinincome();
            		coinCount+=terminalstatisticPO.getCoinnumber();
        			i++;
				}

        		JSONObject jsonTmp=new JSONObject();
				jsonTmp.put("city", terminalstatisticPO.getCity());
            	jsonTmp.put("adminname", terminalstatisticPO.getAdminname());
            	AdminPO adminPO=incomeDAO.selectAdminPOByAdminName(terminalstatisticPO.getAdminname());
            	jsonTmp.put("adminphone", adminPO.getPhone());
    			jsonTmp.put("time", terminalstatisticPO.getGmtcreate());
    			jsonTmp.put("totalTurnover",totalTurnover);
        		jsonTmp.put("totalCount", totalCount);
        		jsonTmp.put("wechatTurnover",wechatTurnover );
        		jsonTmp.put("wechatCount", wechatCount);
        		jsonTmp.put("coinTurnover",coinTurnover);
        		jsonTmp.put("coinCount",coinCount);
        		jsonArray.add(jsonTmp);

        		totalTurnover=totalCount=wechatTurnover=wechatCount=coinTurnover=coinCount=0;
            	if(i<size){
            		Tmp=terminalstatisticPOs.get(i).getGmtcreate();
            		adminTmp=terminalstatisticPOs.get(i).getAdminname();
            	}
        	}
    	}else{

        	String Tmp=terminalstatisticPOs.get(0).getAdminname();
        	int i=0,size=terminalstatisticPOs.size();
        	while(i<size){
        		TerminalstatisticPO terminalstatisticPO=new TerminalstatisticPO();
        		while (i<size&&terminalstatisticPOs.get(i).getAdminname().equals(Tmp)) {
            		terminalstatisticPO=terminalstatisticPOs.get(i);
        			totalTurnover+=terminalstatisticPO.getTotalincome();
            		totalCount+=terminalstatisticPO.getTotalnumber();
            		wechatTurnover+=terminalstatisticPO.getWechatincome();
            		wechatCount+=terminalstatisticPO.getWechatnumber();
            		coinTurnover+=terminalstatisticPO.getCoinincome();
            		coinCount+=terminalstatisticPO.getCoinnumber();
        			i++;
				}

        		JSONObject jsonTmp=new JSONObject();
				jsonTmp.put("city", terminalstatisticPO.getCity());
            	jsonTmp.put("adminname", terminalstatisticPO.getAdminname());
            	AdminPO adminPO=incomeDAO.selectAdminPOByAdminName(terminalstatisticPO.getAdminname());
            	jsonTmp.put("adminphone", adminPO.getPhone());
    			jsonTmp.put("totalTurnover",totalTurnover);
        		jsonTmp.put("totalCount", totalCount);
        		jsonTmp.put("wechatTurnover",wechatTurnover );
        		jsonTmp.put("wechatCount", wechatCount);
        		jsonTmp.put("coinTurnover",coinTurnover);
        		jsonTmp.put("coinCount",coinCount);
        		jsonArray.add(jsonTmp);

        		totalTurnover=0;
            	totalCount=0;
            	wechatTurnover=0;
            	wechatCount=0;
            	coinTurnover=0;
            	coinCount=0;
            	if(i<size){
            		Tmp=terminalstatisticPOs.get(i).getAdminname();
            	}
        	}
    	}
		return jsonArray;
	}
	
	public JSONArray cityJSONArray(String city,String beginTime,String endTime,int showStyle){
		int totalTurnover=0;
    	int totalCount=0;
    	int wechatTurnover=0;
    	int wechatCount=0;
    	int coinTurnover=0;
    	int coinCount=0;
		JSONArray jsonArray=new JSONArray();
		List<TerminalstatisticPO> terminalstatisticPOs=incomeDAO.selectStatisticPOByCity(city, beginTime, endTime);
		if(terminalstatisticPOs.size()==0)
			return null;
		if(showStyle==1){//按条显示
        	int i=0,size=terminalstatisticPOs.size();
			String Tmp=terminalstatisticPOs.get(i).getGmtcreate();
			String cityTmp=terminalstatisticPOs.get(i).getCity();
        	while(i<size){
        		TerminalstatisticPO terminalstatisticPO=new TerminalstatisticPO();
        		while (i<size&&terminalstatisticPOs.get(i).getGmtcreate().equals(Tmp)&&terminalstatisticPOs.get(i).getCity().equals(cityTmp)) {
            		terminalstatisticPO=terminalstatisticPOs.get(i);
        			totalTurnover+=terminalstatisticPO.getTotalincome();
            		totalCount+=terminalstatisticPO.getTotalnumber();
            		wechatTurnover+=terminalstatisticPO.getWechatincome();
            		wechatCount+=terminalstatisticPO.getCoinnumber();
            		coinTurnover+=terminalstatisticPO.getCoinincome();
            		coinCount+=terminalstatisticPO.getCoinnumber();
        			i++;
				}

        		JSONObject jsonTmp=new JSONObject();
				jsonTmp.put("city", terminalstatisticPO.getCity());
    			jsonTmp.put("time", terminalstatisticPO.getGmtcreate());
    			jsonTmp.put("totalTurnover",totalTurnover);
        		jsonTmp.put("totalCount", totalCount);
        		jsonTmp.put("wechatTurnover",wechatTurnover );
        		jsonTmp.put("wechatCount", wechatCount);
        		jsonTmp.put("coinTurnover",coinTurnover);
        		jsonTmp.put("coinCount",coinCount);
        		jsonArray.add(jsonTmp);

        		totalTurnover=totalCount=wechatTurnover=wechatCount=coinTurnover=coinCount=0;
            	if(i<size){
            		Tmp=terminalstatisticPOs.get(i).getGmtcreate();
    				cityTmp=terminalstatisticPOs.get(i).getCity();
            	}
        	}
    	}else{

        	String Tmp=terminalstatisticPOs.get(0).getCity();
        	int i=0,size=terminalstatisticPOs.size();
        	while(i<size){
        		TerminalstatisticPO terminalstatisticPO=new TerminalstatisticPO();
        		while (i<size&&terminalstatisticPOs.get(i).getCity().equals(Tmp)) {
            		terminalstatisticPO=terminalstatisticPOs.get(i);
        			totalTurnover+=terminalstatisticPO.getTotalincome();
            		totalCount+=terminalstatisticPO.getTotalnumber();
            		wechatTurnover+=terminalstatisticPO.getWechatincome();
            		wechatCount+=terminalstatisticPO.getWechatnumber();
            		coinTurnover+=terminalstatisticPO.getCoinincome();
            		coinCount+=terminalstatisticPO.getCoinnumber();
        			i++;
				}

        		JSONObject jsonTmp=new JSONObject();
				jsonTmp.put("city", terminalstatisticPO.getCity());
    			jsonTmp.put("totalTurnover",totalTurnover);
        		jsonTmp.put("totalCount", totalCount);
        		jsonTmp.put("wechatTurnover",wechatTurnover );
        		jsonTmp.put("wechatCount", wechatCount);
        		jsonTmp.put("coinTurnover",coinTurnover);
        		jsonTmp.put("coinCount",coinCount);
        		jsonArray.add(jsonTmp);

        		totalTurnover=0;
            	totalCount=0;
            	wechatTurnover=0;
            	wechatCount=0;
            	coinTurnover=0;
            	coinCount=0;
            	if(i<size){
            		Tmp=terminalstatisticPOs.get(i).getCity();
            	}
        	}
    	}
		return jsonArray;
	}
	public boolean authConfirm(int type, String url) {
		if (adminDAO.selectAuthorityByTypeAndUrl(type, url) == 1) {
			return true;
		}
		return false;
	}
}
