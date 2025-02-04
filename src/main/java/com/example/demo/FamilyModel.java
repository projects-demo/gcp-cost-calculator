package com.example.demo;

import java.util.HashSet;

public class FamilyModel {
	String familyName;
	boolean isSelected;
	HashSet<String> resources;

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public HashSet<String> getResources() {
		return resources;
	}

	public void setResources(HashSet<String> resources) {
		this.resources = resources;
	}

}
