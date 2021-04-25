package com.SpringBoot.API.business;

import org.springframework.beans.factory.annotation.Autowired;

import com.SpringBoot.API.Interface.IAllocateAndReAllocateParking;
import com.SpringBoot.API.Interface.ICarParkingList;
import com.SpringBoot.API.model.CarPark;
import com.SpringBoot.API.model.ParkingStatus;
import com.SpringBoot.API.services.CarParkingList;

public class AllocateAndReAllocateParking implements IAllocateAndReAllocateParking{

	@Autowired
	private ICarParkingList _carParkingList;
	
	public AllocateAndReAllocateParking() {
		this._carParkingList = new CarParkingList();
	}
	
	public String AllocateParking(CarPark carPark) throws Exception {		
		
		if(carPark.getNumberOfHours() > 4) {
			 throw new Exception ("Please choose parking hours for maximum 4 hours");
		}
		
		if(carPark.getID() == 0) {
			throw new Exception ("Please select a parking slot");
		}
		
		if(carPark.getID() < 0 || carPark.getID() > 400) {
			throw new Exception ("Invalid Parking Slot");
		}
		
		CarPark output = this._carParkingList.GenerateParkingTicket(carPark);
		return output.getTicketNumber();
	}

	public CarPark ReAllocateParking(CarPark carPark) throws Exception {
		
		if(carPark == null) {
			throw new Exception("Please Specify the Slot to Reallocate");
		}
		CarPark output = _carParkingList.GenerateParkingTicketForReAllocation(carPark);
		return output;
	}

	public String RemoveSlot(int ParkingSlotID) throws Exception {
        
		CarPark currentStatus = _carParkingList.ResetParkingDetail(ParkingSlotID);
        if(currentStatus.getStatus() != ParkingStatus.AMENDED) {
        	throw new Exception("Parking Space is not Available");
        }
		return "Parking Space is available";
	}
}
