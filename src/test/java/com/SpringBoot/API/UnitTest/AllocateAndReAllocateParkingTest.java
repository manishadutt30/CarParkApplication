package com.SpringBoot.API.UnitTest;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import com.SpringBoot.API.Interface.IAllocateAndReAllocateParking;
import com.SpringBoot.API.Interface.ICarParkingList;
import com.SpringBoot.API.business.AllocateAndReAllocateParking;
import com.SpringBoot.API.model.CarPark;
import com.SpringBoot.API.model.ParkingStatus;
import com.SpringBoot.API.services.CarParkingList;

public class AllocateAndReAllocateParkingTest {
	
	private IAllocateAndReAllocateParking _allocateAndReAllocateParkingTest;
	private Date date = new Date();
	
	private ICarParkingList _carParkingList; 
    
	@Before
	public void setUp() throws Exception {
		_allocateAndReAllocateParkingTest = new AllocateAndReAllocateParking();	
		_carParkingList = Mockito.mock(CarParkingList.class);
	}
	
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();
	
	@Test
	public void WhenParkingSlotNumberIsInvalid() throws Exception {
		//Given: when ParkingSlot Number Is Invalid
		CarPark carPark = new CarPark();
		carPark.setID(420);
		carPark.setStartTime(date);
		carPark.setNumberOfHours(4);
		carPark.setCarNumber("KA 1111");
		
		//When: Check whether the selected slot number is Valid Or No
		//Then: throw exception.
		exceptionRule.expect(Exception.class);
		exceptionRule.expectMessage("Invalid Parking Slot");
		
		_allocateAndReAllocateParkingTest.AllocateParking(carPark);
	}
	
	@Test
	public void WhenParkingSlotNumberIsNotSelected() throws Exception {
		//Given: when ParkingSlot Number Is not selected
		CarPark carPark = new CarPark();
		carPark.setID(0);
		carPark.setStartTime(date);
		carPark.setNumberOfHours(4);
		carPark.setCarNumber("KA 1111");
		
		//When: Check whether carPark slot number is selected or not
		//Then: throw exception.
		exceptionRule.expect(Exception.class);
		exceptionRule.expectMessage("Please select a parking slot");
		
		_allocateAndReAllocateParkingTest.AllocateParking(carPark);
	}
	
	@Test
	public void WhenParkingHourISMoreThanFourHours() throws Exception {
		//Given: when Parking hours are more than 4 hours
		CarPark carPark = new CarPark();
		carPark.setID(1);
		carPark.setStartTime(date);
		carPark.setNumberOfHours(5);
		carPark.setCarNumber("KA 1111");
		
		//When: Check whether parking hours is more than 4 hours
		//Then: throw exception.
		exceptionRule.expect(Exception.class);
		exceptionRule.expectMessage("Please choose parking hours for maximum 4 hours");
		
		_allocateAndReAllocateParkingTest.AllocateParking(carPark);
	}
	
	@Test
	public void WhenParkingDetailsAreCorrect() throws Exception {
		//Given: when Parking Details are correct
		
		CarPark carPark = new CarPark();
		carPark.setID(1);
		carPark.setStartTime(date);
		carPark.setNumberOfHours(4);
		carPark.setCarNumber("KA 1111");
		
		CarPark actualResult = new CarPark();
		actualResult.setID(1);
		actualResult.setStartTime(date);
		actualResult.setNumberOfHours(4);
		actualResult.setCarNumber("KA 1111");
		actualResult.setStatus(ParkingStatus.ALLOCATED);
		actualResult.setTicketNumber("A45678");
		
		//When: Check whether parking details are correct or not and then assign ticket number
		Mockito.when(_carParkingList.GenerateParkingTicket(carPark)).thenReturn(actualResult);
		String expectedresult = _allocateAndReAllocateParkingTest.AllocateParking(carPark);
		
		//then: PArking ticket should be generated
		assertEquals(actualResult.getTicketNumber(), expectedresult);
	}
	
	@Test
	public void WhenParkingSpaceIsAvailableAgain() throws Exception {
		//Given: when car is removed.
		CarPark carPark = new CarPark();
		carPark.setID(1);
		carPark.setStartTime(date);
		carPark.setNumberOfHours(4);
		carPark.setCarNumber("KA 1111");
		carPark.setStatus(ParkingStatus.ALLOCATED);
		carPark.setTicketNumber("A45678");
		
		CarPark mockResult = new CarPark();
		mockResult.setID(1);
		mockResult.setNumberOfHours(0);
		mockResult.setCarNumber(null);
		mockResult.setStatus(ParkingStatus.AMENDED);
		mockResult.setTicketNumber(null);
		
		String actualResult = "Parking Space is available";
		
		//When: call removeSlot method 
		Mockito.when(_carParkingList.ResetParkingDetail(carPark.getID())).thenReturn(mockResult);
		String expectedresult = _allocateAndReAllocateParkingTest.RemoveSlot(carPark.getID());
		
		//Then: Check if parking space is available or not
		assertEquals(actualResult, expectedresult);
	}

	@Test
	public void WhenParkingSlotIsRellocated() throws Exception {
		//Given: User want to reallocate the parking slot
		CarPark carPark = new CarPark();
		carPark.setID(1);
		carPark.setStartTime(date);
		carPark.setNumberOfHours(4);
		carPark.setCarNumber("KA 1111");
		carPark.setStatus(ParkingStatus.ALLOCATED);
		carPark.setTicketNumber("A45678");
		
		CarPark mockResult = new CarPark();
		mockResult.setID(1);
		mockResult.setNumberOfHours(3);
		mockResult.setCarNumber("KA 1111");
		mockResult.setStatus(ParkingStatus.REALLOCATED);
		mockResult.setTicketNumber("A45679");
		
		//When: Remove the current parking slot and reassigned to same car
		Mockito.when(_carParkingList.GenerateParkingTicketForReAllocation(carPark)).thenReturn(mockResult);
		CarPark expectedResult = _allocateAndReAllocateParkingTest.ReAllocateParking(carPark);
		
		//Then: Slot is ReAllocated
		assertEquals(ParkingStatus.REALLOCATED, expectedResult.getStatus());
		assertEquals("A45679", expectedResult.getTicketNumber());
		
	}
}
