/*
 * 
 * DemandDraft Bean class
 * 
 */

package com.cg.bank.bean;

public class DemandDraft {
	String name;
	String mobile;
	String forName;
	float ddAmount;
	String remarks;
	int commission;
	
	public int getCommission() {
		return commission;
	}
	public void setCommission(int commission) {
		this.commission = commission;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getForName() {
		return forName;
	}
	public void setForName(String forName) {
		this.forName = forName;
	}
	
	public float getDdAmount() {
		return ddAmount;
	}
	public void setDdAmount(float ddAmount) {
		this.ddAmount = ddAmount;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	

}
