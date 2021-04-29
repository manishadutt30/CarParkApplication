package com.SpringBoot.API.model;

import java.util.Date;

public class CarPark {

	private int iD;
	private String status;;
    private int numberOfHours;
    private String carNumber;
    private String ticketNumber;
    private String message;
    
    public void setID(int ID) {
    	this.iD = ID;
    }
    
    public int getID() {
    	return this.iD;
    }
    
    public void setStatus(String Status) {
    	this.status = Status;
    }
    
    public String getStatus() {
    	return this.status;
    }
    
    public void setNumberOfHours(int NumberOfHours) {
    	this.numberOfHours = NumberOfHours;
    }
    
    public int getNumberOfHours() {
    	return this.numberOfHours;
    }
    
    public void setCarNumber(String CarNumber) {
    	this.carNumber = CarNumber;
    }
    
    public String getCarNumber() {
    	return this.carNumber;
    }
    
    public void setTicketNumber(String TicketNumber) {
    	this.ticketNumber = TicketNumber;
    }
    
    public String getTicketNumber() {
    	return this.ticketNumber;
    }
    
    public void setMessage(String Message) {
    	this.message = Message;
    }
    
    public String getMessage() {
    	return this.message;
    }
}
