package com.example.demo;

import java.util.ArrayList;

public class EstimateModel {

	String skuName;
	String serviceName;
	PricingModel pricing;
	float totalCost;
	String unitsRequested;

	public String getUnitsRequested() {
		return unitsRequested;
	}

	public void setUnitsRequested(String unitsRequested) {
		this.unitsRequested = unitsRequested;
	}

	public float getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(float totalCost) {
		this.totalCost = totalCost;
	}

	public EstimateModel() {
		super();
	}

	public String getSkuName() {
		return skuName;
	}

	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public PricingModel getPricing() {
		return pricing;
	}

	public void setPricing(PricingModel pricing) {
		this.pricing = pricing;
	}


}
