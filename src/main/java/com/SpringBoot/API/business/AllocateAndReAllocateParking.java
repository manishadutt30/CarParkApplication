package com.SpringBoot.API.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SpringBoot.API.Interface.IAllocateAndReAllocateParking;
import com.SpringBoot.API.Interface.ICarParkingList;
import com.SpringBoot.API.model.CarPark;
import com.SpringBoot.API.model.ParkingStatus;
import com.SpringBoot.API.services.CarParkingList;

public class AllocateAndReAllocateParking implements IAllocateAndReAllocateParking{

	@Autowired
	private ICarParkingList carParkingList;
	
	public AllocateAndReAllocateParking() {
		this.carParkingList = new CarParkingList();
	}
	
	public CarPark AllocateParking(int ParkingSlotID, int NumberOfHours, String CarNumber) {		
			
		CarPark carParkValue = carParkingList.GenerateParkingTicket(ParkingSlotID, NumberOfHours, CarNumber);
		if(carParkValue == null) {
			carParkValue.setMessage("There is some error while processing. Please try again.");
		}
		return carParkValue;
	}

	public CarPark ReAllocateParking(int ParkingSlotID) {
		
		CarPark carParkValue = carParkingList.GenerateParkingTicketForReAllocation(ParkingSlotID);
		if(carParkValue == null) {
			carParkValue.setMessage("ParkingSlot doesn't match to earlier parking slot.");
		}
		return carParkValue;
	}

	public CarPark RemoveSlot(int ParkingSlotID) {
        
		CarPark currentStatus = carParkingList.ResetParkingDetail(ParkingSlotID);
		if(currentStatus == null) {
			currentStatus.setMessage("There is some error while processing. Please try again.");
		}
		return currentStatus;
	}
}
