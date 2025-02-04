package com.example.demo;

public class ServiceModel {

	public String serviceId;
	public String displayName;
	public String sNo;

	public String getsNo() {
		return sNo;
	}

	public void setsNo(String sNo) {
		this.sNo = sNo;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public ServiceModel(String sNo, String serviceId, String displayName) {
		super();
		this.sNo = sNo;
		this.serviceId = serviceId;
		this.displayName = displayName;
	}

}