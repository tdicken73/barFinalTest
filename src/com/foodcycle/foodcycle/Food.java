package com.foodcycle.foodcycle;

import java.util.Date;

public class Food {
	private int id;	//unique ID for each food item added to inventory
    private String type; //type of food item (name)
    private int count; //number food item
    private Date insertDate; //date food item is inserted into database
 
    public Food(){}
 
    public Food(String type, int count) {
        super();
        this.type = type;
        this.count = count;
        this.insertDate = new Date();
    }
    
    public int getId() {
    	return this.id;
    }
    
    public String getType() {
    	return this.type;
    }
    
    public int getCount() {
    	return this.count;
    }
    
    public Date getInsertionDate()
    {
    	return this.insertDate;
    }
    
    public void setId(int value) {
    	this.id = value;
    }
    
    public void setType(String value) {
    	this.type = value;
    }
    
    public void setCount(int value) {
    	this.count = value;
    }
    
    public void setInsertionDate(Date d)
    {
    	this.insertDate = d;
    }
 
    //getters & setters
 
    @Override
    public String toString() {
        return "Food [id=" + id + ", type=" + type + ", count=" + count
                + "]";
    }
}
