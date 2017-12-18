package com.terabits.meta.po;

public class DevicePO {
	private int gid;
	private String imei;
	private String deviceid;
	private String ip;
	private int status;
	private int activation;
	private int siteid;
	private String selfnumber;
	private String type;
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
	public String getDeviceid() {
		return deviceid;
	}
	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getSiteid() {
		return siteid;
	}
	public void setSiteid(int siteid) {
		this.siteid = siteid;
	}
	public int getActivation() {
		return activation;
	}
	public void setActivation(int activation) {
		this.activation = activation;
	}
	
	public String getSelfnumber() {
		return selfnumber;
	}
	public void setSelfnumber(String selfnumber) {
		this.selfnumber = selfnumber;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "DevicePO [gid=" + gid + ", imei=" + imei + ", deviceid=" + deviceid + ", ip=" + ip + ", status="
				+ status + ", activation=" + activation + ", siteid=" + siteid + ", selfnumber=" + selfnumber
				+ ", type=" + type + "]";
	}
}