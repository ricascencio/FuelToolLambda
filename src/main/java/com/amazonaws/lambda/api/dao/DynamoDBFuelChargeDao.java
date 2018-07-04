package com.amazonaws.lambda.api.dao;

import java.util.HashMap;
import java.util.Map;

//import org.apache.log4j.Logger;




import com.amazonaws.lambda.api.domain.FuelCharge;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.spec.PutItemSpec;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.PutItemResult;
import com.amazonaws.services.dynamodbv2.model.QueryRequest;
import com.amazonaws.services.dynamodbv2.model.QueryResult;

public class DynamoDBFuelChargeDao implements FuelChargeDao{

    //private static final Logger log = Logger.getLogger(DynamoDBFuelChargeDao.class);

    private static final AmazonDynamoDB mapper = AmazonDynamoDBClientBuilder.defaultClient();

    private static volatile DynamoDBFuelChargeDao instance;
	
    private DynamoDBFuelChargeDao(){}
    
    public static DynamoDBFuelChargeDao instance(){
    	if(instance == null){
    		synchronized (DynamoDBFuelChargeDao.class) {
				if(instance == null){
					instance = new DynamoDBFuelChargeDao();
				}
			}
    	}
    	return instance;
    }
    
	@Override
	public FuelCharge findLastFuelChargeByCar(String car) {
		Condition hashKeyCondition = new Condition()
		.withComparisonOperator(ComparisonOperator.EQ)
		.withAttributeValueList(new AttributeValue().withS(car));
		
		Condition sortKeyCondition = new Condition()
		.withComparisonOperator(ComparisonOperator.LT)
		.withAttributeValueList(new AttributeValue().withN(System.currentTimeMillis()+""));
		
		Map<String, Condition> keyConditions = new HashMap<String, Condition>();
		keyConditions.put("car", hashKeyCondition);
		keyConditions.put("charge_date", sortKeyCondition);
		
		QueryRequest query = new QueryRequest()
		.withTableName("fuelCharges")
		.withKeyConditions(keyConditions)
		.withScanIndexForward(false);
		
		QueryResult result = mapper.query(query);
		FuelCharge fuelCharge = new FuelCharge();
		
		Map<String, AttributeValue> item = result.getItems().get(0);		
		fuelCharge.setCar(item.get("car").getS());
		fuelCharge.setChargeDate(item.get("charge_date").getN());
        fuelCharge.setChargeFormatedDate(item.get("charge_formated_date").getS());
        fuelCharge.setCreateDate(item.get("create_date").getS());
        fuelCharge.setDays(item.get("days").getN());
        fuelCharge.setKms(item.get("kms").getN());
        fuelCharge.setLts(item.get("lts").getN());
        fuelCharge.setPerformance(item.get("performance").getN());
        fuelCharge.setTotalKms(item.get("total_kms").getS());
		return fuelCharge;
	}

	@Override
	public void saveFuelCharge(FuelCharge fuelCharge) {
		Map<String, AttributeValue> attributes = new HashMap<String, AttributeValue>();
		attributes.put("car", new AttributeValue(fuelCharge.getCar()));
		attributes.put("charge_date", new AttributeValue().withN(fuelCharge.getChargeDate()));
		attributes.put("charge_formated_date", new AttributeValue(fuelCharge.getChargeFormatedDate()));
		attributes.put("create_date", new AttributeValue(fuelCharge.getCreateDate()));
		attributes.put("days", new AttributeValue(fuelCharge.getDays()));
		attributes.put("kms", new AttributeValue(fuelCharge.getKms()));
		attributes.put("lts", new AttributeValue(fuelCharge.getLts()));
		attributes.put("performance", new AttributeValue(fuelCharge.getPerformance()));
		attributes.put("total_kms", new AttributeValue(fuelCharge.getTotalKms()));
		PutItemRequest newItem = new PutItemRequest();
		newItem.setItem(attributes);
		newItem.setTableName("fuelCharges");
		PutItemResult result = mapper.putItem(newItem);
		
	}
	
	private static void printItem(Map<String, AttributeValue> attributeList) {
        for (Map.Entry<String, AttributeValue> item : attributeList.entrySet()) {
            String attributeName = item.getKey();
            AttributeValue value = item.getValue();
            System.out.println(attributeName + " "
                    + (value.getN() == null ? "" : "N=[" + value.getN() + "]")
                    + (value.getN() == null ? "" : "N=[" + value.getN() + "]")
                    + (value.getS() == null ? "" : "S=[" + value.getS() + "]")
                    + (value.getS() == null ? "" : "S=[" + value.getS() + "]")
                    + (value.getN() == null ? "" : "N=[" + value.getN() + "]")
                    + (value.getN() == null ? "" : "N=[" + value.getN() + "]")
                    + (value.getN() == null ? "" : "N=[" + value.getN() + "]")
                    + (value.getS() == null ? "" : "S=[" + value.getS() + "] \n"));
        }
    }

}
