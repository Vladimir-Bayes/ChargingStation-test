package com.terabits.meta.po;

public class ConsumerecordPO {
	private int gid;
	private String deviceid;//�豸���
	private int type;//�������ͣ�΢��֧��/Ͷ��
	private double payment;//���ѽ��
	private int status;
	private String gmtcreate;
	private String gmtmodified;
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	public String getDeviceid() {
		return deviceid;
	}
	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public double getPayment() {
		return payment;
	}
	public void setPayment(double payment) {
		this.payment = payment;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getGmtcreate() {
		return gmtcreate;
	}
	public void setGmtcreate(String gmtcreate) {
		this.gmtcreate = gmtcreate;
	}
	public String getGmtmodified() {
		return gmtmodified;
	}
	public void setGmtmodified(String gmtmodified) {
		this.gmtmodified = gmtmodified;
	}
	@Override
	public String toString() {
		return "ConsumerecordPO [gid=" + gid + ", deviceid=" + deviceid + ", type=" + type + ", payment=" + payment
				+ ", status=" + status + ", gmtcreate=" + gmtcreate + ", gmtmodified=" + gmtmodified + "]";
	}
	
	
}
