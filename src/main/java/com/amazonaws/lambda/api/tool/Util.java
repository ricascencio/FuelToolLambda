package com.amazonaws.lambda.api.tool;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public abstract class Util {
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
	private static DecimalFormat decimalFormat = new DecimalFormat("##.00");
	
	public static Date formatDate(String date){
		try {
			return dateFormat.parse(date);
		} catch (ParseException e) {			
			e.printStackTrace();
			return null;
		}
	}
	
	public static String formatDecimal(Double number){
		return decimalFormat.format(number);
	}
}
