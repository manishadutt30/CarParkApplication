package com.SpringBoot.API.Interface;

import com.SpringBoot.API.model.CarPark;

public interface IAllocateAndReAllocateParking {

	CarPark AllocateParking(int parkingSlotID, int NumberOfHours, String CarNumber);
	CarPark ReAllocateParking(int ParkingSlotID);
	CarPark RemoveSlot(int ParkingSlotID);
}
