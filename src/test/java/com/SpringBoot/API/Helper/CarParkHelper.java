package com.SpringBoot.API.Helper;

import com.SpringBoot.API.model.CarPark;
import com.SpringBoot.API.model.ParkingStatus;

public class CarParkHelper {

	public static CarPark ValidCarParkModel() {
		
		CarPark model = new CarPark();
		model.setID(1);
		model.setNumberOfHours(4);
		model.setCarNumber("KA 1111");
		model.setStatus(ParkingStatus.ALLOCATED);
		model.setTicketNumber("A45678");
		model.setMessage("Parking slot is allocated");
		return model;
	}
	
   public static CarPark ReallocateValidCarParkModel() {
		
		CarPark model = new CarPark();
		model.setID(1);
		model.setNumberOfHours(4);
		model.setCarNumber("KA 1111");
		model.setStatus(ParkingStatus.REALLOCATED);
		model.setTicketNumber("A45679");
		model.setMessage("Parking slot is reallocated");
		return model;
	}
   
   public static CarPark RemoveparkingSlot() {
		
		CarPark model = new CarPark();
		model.setStatus(ParkingStatus.AMENDED);
		model.setMessage("Parking slot is available");
		return model;
	}   
}
