package com.SpringBoot.API.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import com.SpringBoot.API.Interface.ICarParkingList;
import com.SpringBoot.API.model.CarPark;
import com.SpringBoot.API.model.ParkingStatus;

public class CarParkingList implements ICarParkingList {

	private List<CarPark> carparkinglists;
	Date date = new Date();
	
	public CarParkingList(){
		CarParkList();
	}
	
	public void CarParkList(){
		carparkinglists = new ArrayList<CarPark>();
		CarPark value1 = new CarPark();
		value1.setID(1);
		value1.setNumberOfHours(4);
		value1.setCarNumber("KA 1111");
		value1.setStatus(ParkingStatus.ALLOCATED);
		value1.setTicketNumber("A12345");
		
		CarPark value2 = new CarPark();
		value2.setID(2);;
		value2.setNumberOfHours(4);
		value2.setCarNumber("KA 2222");
		value2.setStatus(ParkingStatus.ALLOCATED);
		value2.setTicketNumber("B12345");
		
		CarPark value3 = new CarPark();
		value3.setID(3);
		value3.setNumberOfHours(5);
		value3.setCarNumber("KA 3333");
		value3.setStatus(ParkingStatus.ALLOCATED);
		value3.setTicketNumber("C12345");
		
		carparkinglists.add(value1);
		carparkinglists.add(value2);
		carparkinglists.add(value3);
	}
	
	public List<CarPark> getCarPArlingList(){
		return this.carparkinglists;
	}

	public CarPark GenerateParkingTicket(int parkingSlotID, int NumberOfHours, String CarNumber) {
		
		CarPark carPark = new CarPark();
		carPark.setID(parkingSlotID);
		carPark.setNumberOfHours(NumberOfHours);
		carPark.setCarNumber(CarNumber);
		carPark.setStatus(ParkingStatus.ALLOCATED);
		carPark.setTicketNumber("A45678");
		carPark.setMessage("Parking slot is allocated");
		return carPark;
	}

	public CarPark ResetParkingDetail(int ID){
		CarPark carPark = carparkinglists.stream().filter(list -> list.getID() == ID).findAny().orElse(null);
		if(carPark == null) {
			return null;
		}
		carPark.setNumberOfHours(0);
		carPark.setCarNumber(null);
		carPark.setStatus(ParkingStatus.AMENDED);
		carPark.setTicketNumber(null);
		carPark.setMessage("Parking slot is available");
		return carPark;
	}
	
	public CarPark GenerateParkingTicketForReAllocation(int ParkingSlotID) {
		
		CarPark carPark = carparkinglists.stream().filter(list -> list.getID() == ParkingSlotID).findAny().orElse(null);
		if(carPark == null) {
			return carPark;
		}
		carPark.setStatus(ParkingStatus.REALLOCATED);
		carPark.setTicketNumber("A45679");
		carPark.setMessage("Parking slot is reallocated");
		
		return carPark;		
	}
	
}
