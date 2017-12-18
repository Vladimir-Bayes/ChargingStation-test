package com.terabits.service;



import net.sf.json.JSONArray;

public interface UserService {
	
	public long getItemsCount(String tablename, String beginTime, String endTime, String phone);
	
	public JSONArray getRecharges(String beginTime, String endTime, String phone, int pagesize, int offset);

	public JSONArray getConsumes(String beginTime, String endTime, String phone, int pagesize, int offset);
	
	public int insertPresent(String phone, int present, String comments, String adminname);
	
	public JSONArray getUserInfo(String phone);
}
