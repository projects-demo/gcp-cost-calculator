package com.example.demo;

public class PricingModel {
	String startUsageAmount;
	String currencyCode;
	String finalPrice;
	String displayQuantity;
	String usageUnit;

	public PricingModel() {
		super();
	}

	public String getStartUsageAmount() {
		return startUsageAmount;
	}

	public void setStartUsageAmount(String startUsageAmount) {
		this.startUsageAmount = startUsageAmount;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getFinalPrice() {
		return finalPrice;
	}

	public void setFinalPrice(String finalPrice) {
		this.finalPrice = finalPrice;
	}

	public String getDisplayQuantity() {
		return displayQuantity;
	}

	public void setDisplayQuantity(String displayQuantity) {
		this.displayQuantity = displayQuantity;
	}

	public String getUsageUnit() {
		return usageUnit;
	}

	public void setUsageUnit(String usageUnit) {
		this.usageUnit = usageUnit;
	}
}
