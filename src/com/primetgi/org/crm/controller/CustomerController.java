package com.primetgi.org.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.primetgi.org.crm.dao.CustomerDAO;
import com.primetgi.org.crm.entity.Customer;
import com.primetgi.org.crm.service.CustomerService;
import com.sun.javafx.sg.prism.NGShape.Mode;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	// need to inject the CustomerDAO
	// @Autowired
	// private CustomerDAO customerDAO; --No longer needed as service layer is added

	// need to inject the customer service
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

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {

		// create model attribute to bind form data
		Customer theCustomer = new Customer();

		model.addAttribute("customer", theCustomer);

		return "customer-form";
	}

	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {
		// save customer using service class
		customerService.saveCustomer(theCustomer);
		return "redirect:/customer/list";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId, Model model) {

		// get the customer from the service
		Customer theCustomer = customerService.getCustomer(theId);
		
		// set customer as a model attribute to pre-populate the data
		model.addAttribute("customer", theCustomer);

		// send over to the form

		return "customer-form";
	}
	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int theId, Model model) {
		
		//delete the customer from the service
		customerService.deleteCustomer(theId);
		
		return "redirect:/customer/list";
		
	}
}
