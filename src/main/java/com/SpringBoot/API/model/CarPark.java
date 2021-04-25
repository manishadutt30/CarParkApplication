package com.SpringBoot.API.model;

import java.util.Date;

public class CarPark {

	private int ID;
	private String Status;
    private Date StartTime;
    private int NumberOfHours;
    private String CarNumber;
    private String TicketNumber;
    
    //public CarPark(int ID, Date StartTime, int NumberOfHours, String CarNumber, String TicketNumber) {
	//	this.ID = ID;
	//	this.StartTime = StartTime;
	//	this.NumberOfHours = NumberOfHours;
	//	this.CarNumber = CarNumber;
	//	this.TicketNumber = TicketNumber;
	//}
    
    public void setID(int ID) {
    	this.ID = ID;
    }
    
    public int getID() {
    	return this.ID;
    }
    
    public void setStatus(String Status) {
    	this.Status = Status;
    }
    
    public String getStatus() {
    	return this.Status;
    }
    
    public void setStartTime(Date StartTime) {
    	this.StartTime = StartTime;
    }
    
    public Date getStartTime() {
    	return this.StartTime;
    }
    
    public void setNumberOfHours(int NumberOfHours) {
    	this.NumberOfHours = NumberOfHours;
    }
    
    public int getNumberOfHours() {
    	return this.NumberOfHours;
    }
    
    public void setCarNumber(String CarNumber) {
    	this.CarNumber = CarNumber;
    }
    
    public String getCarNumber() {
    	return this.CarNumber;
    }
    
    public void setTicketNumber(String TicketNumber) {
    	this.TicketNumber = TicketNumber;
    }
    
    public String getTicketNumber() {
    	return this.TicketNumber;
    }
}
