package com.terabits.meta.bo;

public class AccountAreaInfoBO {
	private String name;
	private String phone;
	private Integer type;
	private String city;
	private Integer currentType;
	private String currentName;
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPhone() {
		return this.phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public Integer getType() {
		return this.type;
	}
	
	public void setType(Integer type) {
		this.type = type;
	}
	
	public String getCity() {
		return this.city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public Integer getCurrentType() {
		return this.currentType;
	}
	
	public void setCurrentType(Integer currentType) {
		this.currentType = currentType;
	}
	
	public String getCurrentName() {
		return this.currentName;
	}
	
	public void setCurrentName(String currentName) {
		this.currentName = currentName;
	}
}
