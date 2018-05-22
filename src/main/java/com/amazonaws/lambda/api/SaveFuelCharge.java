package com.amazonaws.lambda.api;

import java.util.concurrent.TimeUnit;

import com.amazonaws.lambda.api.dao.DynamoDBFuelChargeDao;
import com.amazonaws.lambda.api.domain.FuelCharge;
import com.amazonaws.lambda.api.tool.Util;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class SaveFuelCharge implements RequestHandler<ChargeLambdaRequest, String> {
	
	private static final DynamoDBFuelChargeDao fuelChargeDao = DynamoDBFuelChargeDao.instance();

    @Override
    public String handleRequest(ChargeLambdaRequest input, Context context) {
    	// get last charge by car    	
    	FuelCharge fuelCharge = fuelChargeDao.findLastFuelChargeByCar(input.getCar());
               
    	long diffInMillies = Math.abs(System.currentTimeMillis() - Long.parseLong(fuelCharge.getChargeDate()));
    	long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    	Double performance = Double.parseDouble(input.getKms())/Double.parseDouble(input.getLts());    	    	
    	
    	fuelCharge.setChargeFormatedDate(input.getChargeDate());
    	fuelCharge.setChargeDate(String.valueOf(Util.formatDate(input.getChargeDate()).getTime()));
    	fuelCharge.setCreateDate(input.getCreateDate());
    	fuelCharge.setDays(String.valueOf(diff));    	
    	fuelCharge.setKms(input.getKms());
    	fuelCharge.setLts(input.getLts());    	        	
    	fuelCharge.setPerformance(Util.formatDecimal(performance));    	
    	
    	fuelChargeDao.saveFuelCharge(fuelCharge);
    	
    	ChargeLambdaResponse response = new ChargeLambdaResponse(fuelCharge.getCar(), fuelCharge.getKms(), 
    			fuelCharge.getLts(), fuelCharge.getDays(), fuelCharge.getPerformance());
    	
        return response.getMessage();
    }

}
