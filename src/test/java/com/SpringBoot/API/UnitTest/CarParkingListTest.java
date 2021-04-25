package com.SpringBoot.API.UnitTest;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.SpringBoot.API.Interface.ICarParkingList;
import com.SpringBoot.API.model.CarPark;
import com.SpringBoot.API.model.ParkingStatus;
import com.SpringBoot.API.services.CarParkingList;

public class CarParkingListTest {

	private ICarParkingList _carParkingList;
	private Date date = new Date();
	
	@Before
	public void setUp() throws Exception {
		_carParkingList = new CarParkingList();
	}
	
	@Test
	public void WhenUserbookFirstTimeParkingSlotAndCarParkModelIsEmpty() {
		//Given: When user allocate the parking space
		CarPark carpark = null;
		
		//when: call method to allocate the car park
		CarPark expected = _carParkingList.GenerateParkingTicket(carpark);
		
		//then: Parking status should be changed and Ticket number should be generated
		assertEquals(null, expected);
	}
	
	@Test
	public void WhenUserBookFirstTimeParkingSlotAndCarParkModelIsNotEmpty() {
		//Given: When user allocate the parking space
		CarPark carpark = new CarPark();
		carpark.setID(1);
		carpark.setNumberOfHours(4);
		carpark.setCarNumber("KA 1111");
		
		//when: call method to allocate the car park
		CarPark expected = _carParkingList.GenerateParkingTicket(carpark);
		
		//then: Parking status should be changed and Ticket number should be generated
		assertEquals(ParkingStatus.ALLOCATED, expected.getStatus());
	}
	
	@Test
	public void WhenParkingSlotIsAvailable() throws Exception {
		//Given: When user allocate the parking space
		int parkingSlot = 1;
		
		//when: call method to allocate the car park
		CarPark expected = _carParkingList.ResetParkingDetail(parkingSlot);
		
		//then: Parking status should be changed and Ticket number should be generated
		assertEquals(ParkingStatus.AMENDED, expected.getStatus());
		assertEquals(0, expected.getNumberOfHours());
		assertEquals(null, expected.getCarNumber());
		assertEquals(null, expected.getTicketNumber());
	}
	
	@Test
	public void WhenUserReAllocateParkingSlot() {
		//Given: When user reallocate the same parking slot
		CarPark carpark = new CarPark();
		carpark.setID(1);
		carpark.setNumberOfHours(3);
		carpark.setCarNumber("KA 1111");
		carpark.setTicketNumber("A45678");
		
		//When: Call method to reallocate the same parking slot
		CarPark expected = _carParkingList.GenerateParkingTicketForReAllocation(carpark);
		
		//then: User can able to reallocated the same spot
		assertEquals(ParkingStatus.REALLOCATED, expected.getStatus());
		assertEquals("A45679", expected.getTicketNumber());
	}

}
