package org.school.studentms.controller;

import java.util.List;

import org.school.studentms.dto.AddressDTO;
import org.school.studentms.service.AddressServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/students")
public class StudentAddressController {
	
	@Autowired
	private AddressServiceClient serviceClient;
	
	@GetMapping("/addressweb/{id}")
	public AddressDTO getAddressbyIdUsingWebclient(@PathVariable long id){
		return serviceClient.getAddressByIdRest(id);
		
	}

	@GetMapping("/addressrest/{id}")
	public AddressDTO getAddressbyIdUsingResttemplate(@PathVariable long id){
		return serviceClient.getAddressByIdRest(id);
		
	}
	

}
