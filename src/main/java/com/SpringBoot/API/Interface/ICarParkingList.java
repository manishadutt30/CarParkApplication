package com.SpringBoot.API.Interface;

import java.util.List;

import com.SpringBoot.API.model.CarPark;

public interface ICarParkingList {
	List<CarPark> getCarPArlingList();
	CarPark GenerateParkingTicket(CarPark carPark);
	CarPark ResetParkingDetail(int ID) throws Exception;
	CarPark GenerateParkingTicketForReAllocation(CarPark carPark);
}
