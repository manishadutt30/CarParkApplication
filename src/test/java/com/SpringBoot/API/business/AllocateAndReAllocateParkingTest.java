package com.SpringBoot.API.business;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import com.SpringBoot.API.Helper.CarParkHelper;
import com.SpringBoot.API.Interface.IAllocateAndReAllocateParking;
import com.SpringBoot.API.Interface.ICarParkingList;
import com.SpringBoot.API.business.AllocateAndReAllocateParking;
import com.SpringBoot.API.model.CarPark;
import com.SpringBoot.API.model.ParkingStatus;
import com.SpringBoot.API.services.CarParkingList;

public class AllocateAndReAllocateParkingTest {
	
	private IAllocateAndReAllocateParking allocateAndReAllocateParkingTest;
	
	private ICarParkingList carParkingList; 
    
	@Before
	public void setUp() throws Exception {
		allocateAndReAllocateParkingTest = new AllocateAndReAllocateParking();	
		carParkingList = Mockito.mock(CarParkingList.class);
	}
	
	@Test
	public void WhenPassValidSlot_NumberOfHours_CarNumberReturnsNullModel(){
		//Given: Valid SlotNumber, NumberOfHours, CarNumber
		int parkingSlotID = 1;
		int NumberOfHours = 4; 
		String CarNumber = "A12345";
		String actualValue = "There is some error while processing. Please try again.";
		
		//When: Calls AllocateParking method to generated ticket number
		Mockito.when(carParkingList.GenerateParkingTicket(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString())).thenReturn(null);
		CarPark expectedValue = allocateAndReAllocateParkingTest.AllocateParking(parkingSlotID, NumberOfHours, CarNumber);
		
		//Then: Parking slot is not allocated
		assertEquals(actualValue, expectedValue.getMessage());
		assertNull(expectedValue.getTicketNumber());
		assertNull(expectedValue.getStatus());
	}
	
	@Test
	public void WhenPassValidSlot_NumberOfHours_CarNumberReturnsValidModel()  {
		//Given: Valid SlotNumber, NumberOfHours, CarNumber
		int parkingSlotID = 1;
		int NumberOfHours = 4; 
		String CarNumber = "A12345";
		CarPark actualResult = CarParkHelper.ValidCarParkModel();
		
		//When: Calls AllocateParking method to generated ticket number
		Mockito.when(carParkingList.GenerateParkingTicket(Mockito.anyInt(), Mockito.anyInt(), Mockito.anyString())).thenReturn(actualResult);
		CarPark expectedValue = allocateAndReAllocateParkingTest.AllocateParking(parkingSlotID, NumberOfHours, CarNumber);
		
		//Then: ParkingSlot is Allocated
		assertEquals(actualResult.getStatus(), expectedValue.getStatus());
		assertEquals(actualResult.getMessage(), expectedValue.getMessage());
		assertNotNull(expectedValue.getTicketNumber());
	}
	
	@Test
	public void WhenParkingSlotDoesnotMatchToAlreayAllocatedSlot() throws Exception {
		//Given: ParkingSlot number for reallocation
		int parkingSlotID = 1;
		String actualResult = "ParkingSlot doesn't match to earlier parking slot.";
				
		//When: Calls ReAllocateParking method to reallocated the slot
		Mockito.when(carParkingList.GenerateParkingTicketForReAllocation(Mockito.anyInt())).thenReturn(null);
		CarPark expectedResult = allocateAndReAllocateParkingTest.ReAllocateParking(parkingSlotID);
		
		//Then: ParkingSlot is not reallocated
		assertNull(expectedResult.getTicketNumber());
		assertNull(expectedResult.getStatus());
		assertEquals(actualResult, expectedResult.getStatus());
	}
	
	@Test
	public void WhenReAllocateThePArkingSlot() throws Exception {
		//Given: ParkingSlot number for reallocation
		int parkingSlotID = 1;
		CarPark actualResult = CarParkHelper.ReallocateValidCarParkModel();
		
		//When: Calls ReAllocateParking method to reallocated the slot
		Mockito.when(carParkingList.GenerateParkingTicketForReAllocation(Mockito.anyInt())).thenReturn(actualResult);
		CarPark expectedValue = allocateAndReAllocateParkingTest.ReAllocateParking(parkingSlotID);
		
		//Then: Parking Slot is reallocated.
		assertEquals(actualResult.getStatus(), expectedValue.getStatus());
	}
	
	@Test
	public void WhenParkingSpaceIsNotReleased() throws Exception {
		//Given: ParkingSlot number for released
		int parkingSlotID = 1;
		String actualResult = "There is some error while processing. Please try again.";
		
		//When: Calls ReAllocateParking method to reallocated the slot
		Mockito.when(carParkingList.ResetParkingDetail(Mockito.anyInt())).thenReturn(new CarPark());
		CarPark expectedValue = allocateAndReAllocateParkingTest.RemoveSlot(parkingSlotID);
		
		//Then: Parking Slot is Released
		assertEquals(actualResult, expectedValue.getMessage());
	}
	
	@Test
	public void WhenParkingSpaceIsReleased() throws Exception {
		//Given: ParkingSlot number for released
		int parkingSlotID = 1;
		CarPark actualResult = CarParkHelper.RemoveparkingSlot();
		
		//When: Calls ReAllocateParking method to reallocated the slot
		Mockito.when(carParkingList.ResetParkingDetail(Mockito.anyInt())).thenReturn(actualResult);
		CarPark expectedValue = allocateAndReAllocateParkingTest.RemoveSlot(parkingSlotID);
		
		//Then: Parking Slot is Released
		assertEquals(actualResult.getStatus(), expectedValue.getStatus());
		assertEquals(actualResult.getMessage(), expectedValue.getMessage());
		assertNull(actualResult.getTicketNumber());
		assertNull(actualResult.getCarNumber());
	}

	
}
