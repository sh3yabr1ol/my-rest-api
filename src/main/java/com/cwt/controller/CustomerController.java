package com.cwt.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cwt.entities.Customer;
import com.cwt.service.CustomerService;
import com.cwt.service.ValidatorService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private ValidatorService validatorService;
	
	@GetMapping("/{custId}")
	public Customer getCustomer(@PathVariable Integer custId) {
	
		return customerService.findCustomerById(custId);
		
	}
	
	@GetMapping("/all")
	public List<Customer> getAllCustomers(){
		return customerService.findAllCustomers();
	}
	
	@PostMapping("/create")
	public ResponseEntity<Object> create(@Valid @RequestBody Customer customer, BindingResult bindingRes){
		
		Map<String, String> validationMap = validatorService.validate(bindingRes);
		
		if(validationMap.isEmpty()) {
			customerService.createCustomerRecord(customer);
			return new ResponseEntity<>(customer, HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>(validationMap, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/update/{custId}")
	public ResponseEntity<Object> update(@PathVariable Integer custId, @Valid @RequestBody Customer customer, BindingResult bindingRes){
		
		Map<String, String> validationMap = validatorService.validate(bindingRes);
		
		if(validationMap.isEmpty()) {
			Customer updatedCustomer = customerService.updateCustomerRecord(custId, customer);
			return new ResponseEntity<>(updatedCustomer, HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<>(validationMap, HttpStatus.BAD_REQUEST);	
		}
		
	}
	
	@DeleteMapping("/delete/{custId}")
	public ResponseEntity<Integer> delete(@PathVariable Integer custId){
		
		customerService.deleteCustomerRecord(custId);
		
		return new ResponseEntity<>(custId, HttpStatus.ACCEPTED);
	}
	
}
