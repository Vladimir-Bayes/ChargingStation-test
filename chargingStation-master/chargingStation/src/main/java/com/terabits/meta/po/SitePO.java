package com.terabits.meta.po;

public class SitePO {
	private int gid;
	private String name;
	private String city;
	private String adminname;
	private String adminphone;
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAdminname() {
		return adminname;
	}
	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}
	public String getAdminphone() {
		return adminphone;
	}
	public void setAdminphone(String adminphone) {
		this.adminphone = adminphone;
	}
	@Override
	public String toString() {
		return "SitePO [gid=" + gid + ", name=" + name + ", city=" + city + ", adminname=" + adminname + ", adminphone="
				+ adminphone + "]";
	}
	
	
}