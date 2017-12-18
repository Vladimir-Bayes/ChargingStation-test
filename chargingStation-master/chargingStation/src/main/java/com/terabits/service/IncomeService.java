package com.terabits.service;

import net.sf.json.JSONArray;

public interface IncomeService {
	public JSONArray deviceJSONArray(String imei,String sitename ,String adminname,String city,String beginTime,String endTime,int showStyle);
	public JSONArray siteJSONArray(String sitename ,String adminname,String city,String beginTime,String endTime,int showStyle);
	public JSONArray adminJSONArray(String adminname,String city,String beginTime,String endTime,int showStyle);
	public JSONArray cityJSONArray(String city,String beginTime,String endTime,int showStyle);
	public boolean authConfirm(int type, String url);
}
