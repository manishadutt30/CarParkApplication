package com.SpringBoot.API.Interface;

import com.SpringBoot.API.model.CarPark;

public interface IAllocateAndReAllocateParking {

	String AllocateParking(CarPark carPark) throws Exception;
	CarPark ReAllocateParking(CarPark carPark) throws Exception;
	String RemoveSlot(int ParkingSlotID) throws Exception;;
}
