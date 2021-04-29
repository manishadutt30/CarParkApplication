package com.SpringBoot.API.Controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBoot.API.Interface.IAllocateAndReAllocateParking;
import com.SpringBoot.API.business.AllocateAndReAllocateParking;
import com.SpringBoot.API.model.CarPark;

@RestController
public class CarParkController {
	
	@Autowired
	private IAllocateAndReAllocateParking allocateAndReAllocateParking;
	
	public CarParkController() {
		allocateAndReAllocateParking = new AllocateAndReAllocateParking();
	}

	 @PostMapping("/api/carpark/{parkingSlotID}/{NumberOfHours}/{CarNumber}")
	    public ResponseEntity<String> FindEmptySlot(@PathVariable int parkingSlotID, @PathVariable int NumberOfHours, @PathVariable String CarNumber) {
		 String validation = Validate(parkingSlotID, NumberOfHours, CarNumber);
		 if (validation != null) {
			 return new ResponseEntity<String>(validation, HttpStatus.PRECONDITION_FAILED);
		 }
		
			CarPark carParkValue = allocateAndReAllocateParking.AllocateParking(parkingSlotID, NumberOfHours, CarNumber);
		    return new ResponseEntity<>(carParkValue.getMessage(), HttpStatus.CREATED);
	 }
	 
	 private String Validate(int parkingSlotID, int numberOfHours, String carNumber) {
		if(parkingSlotID == 0 || parkingSlotID < 0) {
			return "Please select valid Parking slot."; //result.add("Please select valid Parking slot.");
		}
		if(numberOfHours < 1 || numberOfHours > 4) {
			return "Please choose parking hours for minimum 1 hour and  maximum 4 hours.";//result.add("Please choose parking hours for minimum 1 hour and  maximum 4 hours");
		}
		if(carNumber == null) {
			return "Please enter the car valid car number.";//result.add("Please enter the car valid car number");
		}
		return null;
	}

	@DeleteMapping("/api/carpark/{parkingSlotID}")
	 public ResponseEntity<String> EmptySlot(@PathVariable int parkingSlotID) {
		   if(parkingSlotID == 0 || parkingSlotID < 0) {
			   return new ResponseEntity<String>("Please select valid Parking slot.", HttpStatus.PRECONDITION_FAILED);
		   }
		   CarPark result = allocateAndReAllocateParking.RemoveSlot(parkingSlotID);
			return new ResponseEntity<String>(result.getMessage(), HttpStatus.NO_CONTENT);
	 }
	 
	 @PutMapping("/api/carpark/")
	 public ResponseEntity<String> ReallocateParkingSlot(@PathVariable int parkingSlotID) {
		 
		 if(parkingSlotID == 0 || parkingSlotID < 0) {
			   return new ResponseEntity<String>("Please select valid Parking slot.", HttpStatus.PRECONDITION_FAILED);
		   }
		   CarPark result = allocateAndReAllocateParking.RemoveSlot(parkingSlotID);
		   return new ResponseEntity<String>(result.getStatus(), HttpStatus.NO_CONTENT);		 
	 }
	 
	 @ResponseStatus(HttpStatus.NOT_FOUND)
	 public HashMap<String, String> HandleNotFound(){
		 HashMap<String, String> response = new HashMap<>();
	        response.put("status", "fail");
	        response.put("message", "This url is not valid");
	        return response;		 
	 }
}
