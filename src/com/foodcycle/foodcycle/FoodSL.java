package com.foodcycle.foodcycle;

public class FoodSL {
	private int id;
    private String type;
    private int count;
 
    public FoodSL(){}
 
    public FoodSL(String type, int count) {
        super();
        this.type = type;
        this.count = count;
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
    
    public void setId(int value) {
    	this.id = value;
    }
    
    public void setType(String value) {
    	this.type = value;
    }
    
    public void setCount(int value) {
    	this.count = value;
    }
    
 
    //getters & setters
 
    @Override
    public String toString() {
        return "Food [id=" + id + ", type=" + type + ", count=" + count
                + "]";
    }
}
