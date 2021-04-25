package com.SpringBoot.API.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SpringBoot.API.Interface.IAllocateAndReAllocateParking;
import com.SpringBoot.API.business.AllocateAndReAllocateParking;
import com.SpringBoot.API.model.CarPark;

@RestController
@RequestMapping("/api/carpark")
public class CarParkController {
	
	@Autowired
	private IAllocateAndReAllocateParking _allocateAndReAllocateParking;
	
	public CarParkController() {
		_allocateAndReAllocateParking = new AllocateAndReAllocateParking();
	}

	 @GetMapping
	    public List<CarPark> findAllUsers() {
	        // Implement
		 return null;
	    }
	 
	 @PostMapping
	    public String FindEmptySlot(@RequestBody CarPark carPark) {
		 
		 String carParkValue;
		try {
			carParkValue = _allocateAndReAllocateParking.AllocateParking(carPark);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return null;
	 }
	 
	 @DeleteMapping
	 public String EmptySlot(@RequestBody int ParkingSlotID) {
		 try {
			String result = _allocateAndReAllocateParking.RemoveSlot(ParkingSlotID);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return "deleted";
	 }
	 
	 @PutMapping
	 public String ReallocateParkingSlot(@RequestBody CarPark carPark) {
		 
		 try {
			_allocateAndReAllocateParking.ReAllocateParking(carPark);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		 
	 }
}
