package com.terabits.meta.po;
public class TerminalstatisticPO {
	private int gid;
	private String imei;
	private String sitename;
	private String adminname;
	private String city;
	private String gmtcreate;
	private int wechatincome;
	private int wechatnumber;
	private int coinincome;
	private int coinnumber;
	private int totalincome;
	private int totalnumber;
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getSitename() {
		return sitename;
	}
	public void setSitename(String sitename) {
		this.sitename = sitename;
	}
	public String getGmtcreate() {
		return gmtcreate;
	}
	public void setGmtcreate(String gmtcreate) {
		this.gmtcreate = gmtcreate;
	}
	public int getWechatincome() {
		return wechatincome;
	}
	public void setWechatincome(int wechatincome) {
		this.wechatincome = wechatincome;
	}
	public int getWechatnumber() {
		return wechatnumber;
	}
	public void setWechatnumber(int wechatnumber) {
		this.wechatnumber = wechatnumber;
	}
	public int getCoinincome() {
		return coinincome;
	}
	public void setCoinincome(int coinincome) {
		this.coinincome = coinincome;
	}
	public int getCoinnumber() {
		return coinnumber;
	}
	public void setCoinnumber(int coinnumber) {
		this.coinnumber = coinnumber;
	}
	public int getTotalincome() {
		return totalincome;
	}
	public void setTotalincome(int totalincome) {
		this.totalincome = totalincome;
	}
	public int getTotalnumber() {
		return totalnumber;
	}
	public void setTotalnumber(int totalnumber) {
		this.totalnumber = totalnumber;
	}
	public String getAdminname() {
		return adminname;
	}
	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Override
	public String toString() {
		return "TerminalstatisticPO [gid=" + gid + ", imei=" + imei + ", sitename=" + sitename + ", adminname="
				+ adminname + ", city=" + city + ", gmtcreate=" + gmtcreate + ", wechatincome=" + wechatincome
				+ ", wechatnumber=" + wechatnumber + ", coinincome=" + coinincome + ", coinnumber=" + coinnumber
				+ ", totalincome=" + totalincome + ", totalnumber=" + totalnumber + "]";
	}
	
	
}
