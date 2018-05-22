package com.amazonaws.lambda.api;

public class ChargeLambdaResponse {
	
	private String car;
	private String kms;
	private String lts;
	private String days;
	private String performance;
	private StringBuilder message;
	
	public ChargeLambdaResponse(String car, String kms, String lts, String days, String performance){
		this.car = car;
		this.kms = kms;
		this.lts = lts;
		this.days = days;
		this.performance = performance;			
		createResponse();
	}	

	private void createResponse(){		
		message = new StringBuilder();
		message.append("{\"message\":\"");
		message.append(car);
		message.append(": ");
		message.append(lts);
		message.append(" litros en ");
    	message.append(days);
    	message.append(" dias. ");    	    	
    	message.append(performance);
    	message.append(" km/l."); 
    	message.append("\"}");
	}
	
	public String getMessage(){
		return message.toString();
	}
}
