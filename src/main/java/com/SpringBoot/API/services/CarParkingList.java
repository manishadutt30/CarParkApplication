package com.SpringBoot.API.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import com.SpringBoot.API.Interface.ICarParkingList;
import com.SpringBoot.API.model.CarPark;
import com.SpringBoot.API.model.ParkingStatus;

public class CarParkingList implements ICarParkingList {

	private List<CarPark> carparkinglist;
	Date date = new Date();
	
	public CarParkingList(){
		CarParkList();
	}
	
	public void CarParkList(){
		carparkinglist = new ArrayList<CarPark>();
		CarPark value1 = new CarPark();
		value1.setID(1);
		value1.setStartTime(date);
		value1.setNumberOfHours(4);
		value1.setCarNumber("KA 1111");
		value1.setStatus(ParkingStatus.ALLOCATED);
		value1.setTicketNumber("A12345");
		
		CarPark value2 = new CarPark();
		value2.setID(2);
		value2.setStartTime(date);
		value2.setNumberOfHours(4);
		value2.setCarNumber("KA 2222");
		value2.setStatus(ParkingStatus.ALLOCATED);
		value2.setTicketNumber("B12345");
		
		CarPark value3 = new CarPark();
		value3.setID(3);
		value3.setStartTime(date);
		value3.setNumberOfHours(5);
		value3.setCarNumber("KA 3333");
		value3.setStatus(ParkingStatus.ALLOCATED);
		value3.setTicketNumber("C12345");
		
		carparkinglist.add(value1);
		carparkinglist.add(value2);
		carparkinglist.add(value3);
	}
	
	public List<CarPark> getCarPArlingList(){
		return this.carparkinglist;
	}

	public CarPark GenerateParkingTicket(CarPark carPark) {
		if(carPark == null) {
			return carPark;
		}
		carPark.setStatus(ParkingStatus.ALLOCATED);
		carPark.setTicketNumber("A45678");
		return carPark;
	}

	public CarPark ResetParkingDetail(int ID) throws Exception {
		CarPark carPark = null;
		if(ID<0 && ID > 400) {
			throw new Exception("Invalid Parking Slot");
		}
		if(ID != 0) {
			carPark = new CarPark();
			carPark = carparkinglist.stream().filter(list -> list.getID() == ID).findAny().orElse(null);
			carPark.setNumberOfHours(0);
			carPark.setCarNumber(null);
			carPark.setStatus(ParkingStatus.AMENDED);
			carPark.setTicketNumber(null);
		}
		return carPark;
	}
	
	public CarPark GenerateParkingTicketForReAllocation(CarPark carPark) {
		Date date = new Date();
		carPark.setStatus(ParkingStatus.REALLOCATED);
		carPark.setTicketNumber("A45679");
		carPark.setStartTime(date);
		
		return carPark;		
	}
	
}
