package com.amazonaws.lambda.api.domain;

import java.io.Serializable;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "fuelCharges")
public class FuelCharge implements Serializable {
	
	private static final long serialVersionUID = 1L;
	public static final String CAR = "Car-Index";
	public static final String CHARGE_DATE = "Charge_Date-Index";
	
	@DynamoDBHashKey
	private String car;
	
	@DynamoDBRangeKey
	private String chargeDate;
	
	@DynamoDBAttribute
	private String chargeFormatedDate;
	
	@DynamoDBAttribute
	private String createDate;
	
	@DynamoDBAttribute
	private String days;
	
	@DynamoDBAttribute
	private String kms;
	
	@DynamoDBAttribute
	private String lts;
	
	@DynamoDBAttribute
	private String performance;
	
	public FuelCharge(){}
	
	public FuelCharge(String car, String chargeDate, String chargeFormatedDate, String createDate, String days, String kms, String lts, String performance){
		this.car = car;
		this.chargeDate = chargeDate;
		this.chargeFormatedDate = chargeFormatedDate;
		this.createDate = createDate;
		this.days = days;
		this.kms = kms;
		this.lts = lts;
		this.performance = performance;
	}

	public String getCar() {
		return car;
	}

	public void setCar(String car) {
		this.car = car;
	}

	public String getChargeDate() {
		return chargeDate;
	}

	public void setChargeDate(String chargeDate) {
		this.chargeDate = chargeDate;
	}

	public String getChargeFormatedDate() {
		return chargeFormatedDate;
	}

	public void setChargeFormatedDate(String chargeFormatedDate) {
		this.chargeFormatedDate = chargeFormatedDate;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
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

	public String getPerformance() {
		return performance;
	}

	public void setPerformance(String performance) {
		this.performance = performance;
	}	
}
