package com.amazonaws.lambda.api;

public class ChargeLambdaRequest {

	private String car;
	private String kms;
	private String lts;
	private String chargeDate;
	private String createDate;
	public String getCar() {
		return car;
	}
	public void setCar(String car) {
		this.car = car;
	}
	public String getKms() {
		return kms;
	}
	public void setKms(String kms) {
		this.kms = kms;
	}
	public String getLts() {
		return lts;
	}
	public void setLts(String lts) {
		this.lts = lts;
	}
	public String getChargeDate() {
		return chargeDate;
	}
	public void setChargeDate(String chargeDate) {
		this.chargeDate = chargeDate;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
	public String toString(){
		return "ChargeLambdaRequest [car=" + car + ", kms=" + kms + ", lts=" + lts + 
				", chargeDate=" + chargeDate + ", createDate=" + createDate + "]";
	}
}
