package com.SpringBoot.API.Controllers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.SpringBoot.API.Helper.CarParkHelper;
import com.SpringBoot.API.Interface.IAllocateAndReAllocateParking;
import com.SpringBoot.API.business.AllocateAndReAllocateParking;


@RunWith(SpringRunner.class)
@WebMvcTest(CarParkController.class)
public class ControllerTest {

	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private IAllocateAndReAllocateParking allocateAndReAllocateParking;
	
	@Before
	public void setUp(){
		allocateAndReAllocateParking = Mockito.mock(AllocateAndReAllocateParking.class);
	}
	
	@Test
	public void WhenParkingSlotIdIsNotValid() throws Exception {
	 String expectedResult = "Please select valid Parking slot.";
	 
	 mockMvc.perform(MockMvcRequestBuilders.post("/api/carpark/0/4/UP12345")).andExpect(MockMvcResultMatchers.status().is(412)).
	                      andExpect(MockMvcResultMatchers.content().string(expectedResult)); 
	}
	
	@Test
	public void WhenNumerOfHoursIsLessIsNotValid() throws Exception {
	 String expectedResult = "Please choose parking hours for minimum 1 hour and  maximum 4 hours.";
	 
	 mockMvc.perform(MockMvcRequestBuilders.post("/api/carpark/1/5/UP12345")).andExpect(MockMvcResultMatchers.status().is(412)).
	                      andExpect(MockMvcResultMatchers.content().string(expectedResult)); 
	}
	
	@Test
	public void WhenCarNumberIsValid() throws Exception {
	 String expectedResult = "Please enter the car valid car number.";
	 
	 mockMvc.perform(MockMvcRequestBuilders.post("/api/carpark/1/4/")).andExpect(MockMvcResultMatchers.status().is(412)).
	                      andExpect(MockMvcResultMatchers.content().string(expectedResult)); 
	}
	
	@Test
	public void test1() throws Exception {
	   String mock = "Parking Space is available";
		
	  Mockito.when(allocateAndReAllocateParking.RemoveSlot(1)).thenReturn(CarParkHelper.RemoveparkingSlot());
	   mockMvc.perform(MockMvcRequestBuilders.delete("/api/carpark/1")).
			  andExpect(MockMvcResultMatchers.status().is(204)).andExpect(MockMvcResultMatchers.content().string("Parking Space is available"));	   
	}
	
	@Test
	public void test2() throws Exception {
	   String mock = "Please select valid Parking slot.";
		
	   mockMvc.perform(MockMvcRequestBuilders.delete("/api/carpark/0")).
			  andExpect(MockMvcResultMatchers.status().is(412)).andExpect(MockMvcResultMatchers.content().string(mock));	   
	}

}
