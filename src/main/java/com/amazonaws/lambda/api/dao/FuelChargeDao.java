package com.amazonaws.lambda.api.dao;


import com.amazonaws.lambda.api.domain.FuelCharge;

public interface FuelChargeDao {

	FuelCharge findLastFuelChargeByCar(String car);
	
	void saveFuelCharge(FuelCharge fuelCharge);
}
