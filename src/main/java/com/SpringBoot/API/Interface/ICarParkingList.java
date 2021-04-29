package com.SpringBoot.API.Interface;

import java.util.List;

import com.SpringBoot.API.model.CarPark;

public interface ICarParkingList {
	List<CarPark> getCarPArlingList();
	CarPark GenerateParkingTicket(int parkingSlotID, int NumberOfHours, String CarNumber);
	CarPark ResetParkingDetail(int ID);
	CarPark GenerateParkingTicketForReAllocation(int ParkingSlotID);
}
