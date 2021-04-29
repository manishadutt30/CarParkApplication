package com.SpringBoot.API.UnitTest;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.SpringBoot.API.Helper.CarParkHelper;
import com.SpringBoot.API.Interface.ICarParkingList;
import com.SpringBoot.API.model.CarPark;
import com.SpringBoot.API.model.ParkingStatus;
import com.SpringBoot.API.services.CarParkingList;

public class CarParkingListTest {

	private ICarParkingList carParkingList;
	
	@Before
	public void setUp() throws Exception {
		carParkingList = new CarParkingList();
	}
	
	@Test
	public void WhenUserBookFirstTimeParkingSlotAndCarParkModelIsNotEmpty() {
		//Given: When user allocate the parking space
		int parkingSlotID = 1;
		int NumberOfHours = 4; 
		String CarNumber = "A12345";
		CarPark acutalResult = CarParkHelper.ValidCarParkModel();
		
		//when: call method to allocate the car park
		CarPark expectedResult = carParkingList.GenerateParkingTicket(parkingSlotID, NumberOfHours, CarNumber);
		
		//then: Parking status should be changed and Ticket number should be generated
		assertNotNull(expectedResult);
		assertEquals(acutalResult.getStatus(), expectedResult.getStatus());
	}
	
	@Test
	public void WhenResetParkingDetail() throws Exception {
		//Given: When user allocate the parking space
		int parkingSlot = 1;
		CarPark actualResult = CarParkHelper.RemoveparkingSlot();
		
		//when: call method to allocate the car park
		CarPark expectedResult = carParkingList.ResetParkingDetail(parkingSlot);
		
		//then: Parking status should be changed and Ticket number should be generated
		assertNotNull(expectedResult);
		assertEquals(actualResult.getStatus(), expectedResult.getStatus());
		assertEquals(actualResult.getMessage(), expectedResult.getMessage());
	}
	
	@Test
	public void WhenGenerateParkingTicketForReAllocation() {
		//Given: When user reallocate the same parking slot
		int parkingSlot = 1;
		CarPark actualResult = CarParkHelper.ReallocateValidCarParkModel();
		
		//When: Call method to reallocate the same parking slot
		CarPark expectedResult = carParkingList.GenerateParkingTicketForReAllocation(parkingSlot);
		
		//then: User can able to reallocated the same spot
		assertEquals(actualResult.getStatus(), expectedResult.getStatus());
		assertEquals(actualResult.getMessage(), expectedResult.getMessage());
		assertEquals(actualResult.getTicketNumber(), expectedResult.getTicketNumber());
	}

}
