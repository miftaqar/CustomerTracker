package com.primetgi.org.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.primetgi.org.crm.dao.CustomerDAO;
import com.primetgi.org.crm.entity.Customer;
import com.primetgi.org.crm.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	// need to inject the CustomerDAO
	// @Autowired
	// private CustomerDAO customerDAO;		--No longer needed as service layer is added
	
	//need to inject the customer service
	@Autowired
	private CustomerService customerService; 

	@GetMapping("/list")
	public String listCustomer(Model model) {

		// get the customers from the dao
		List<Customer> theCustomers = customerService.getCustomers();

		// add customers to the model
		model.addAttribute("customers", theCustomers);

		return "list-customer";
	}

}
