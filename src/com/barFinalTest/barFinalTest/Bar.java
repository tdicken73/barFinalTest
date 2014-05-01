package com.barFinalTest.barFinalTest;

import java.net.URL;

public class Bar{
	private int id;	//unique ID for each food item added to inventory
	private String name; //name of a bar
	private String type; //type of bar
    private String address; //address of bar
    private String website;
 
    public Bar(){}
 
    public Bar(String name, String type, String address, String website) {
        super();
        this.name=name;
        this.type = type;
        this.address = address;
        this.website = website;
    }
    
    public int getId() {
    	return this.id;
    }
    
    public String getType() {
    	return this.type;
    }
    
    public String getAddress() {
    	return this.address;
    }
    
    public String getName()
    {
    	return this.name;
    }
    
    public String getWebsite() {
    	return this.website;
    }
    
    public void setId(int value) {
    	this.id = value;
    }
    
    public void setType(String value) {
    	this.type = value;
    }
    
    public void setAddress(String value) {
    	this.address = value;
    }
    
    public void setName(String value)
    {
    	this.name = value;
    }
    
    public void setWebsite(String value) {
    	this.website = value;
    }
 
    //getters & setters
 
    @Override
    public String toString() {
        return "Bar [id=" + id + ", name=" +name+ ", type=" + type + ", address=" + address
                + "]";
    }
}
