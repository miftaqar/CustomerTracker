package com.primetgi.org.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.primetgi.org.crm.dao.CustomerDAO;
import com.primetgi.org.crm.entity.Customer;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	// need to inject the CustomerDAO
	@Autowired
	private CustomerDAO customerDAO;

	@RequestMapping("/list")
	public String listCustomer(Model model) {

		// get the customers from the dao
		List<Customer> theCustomers = customerDAO.getCustomers();

		// add customers to the model
		model.addAttribute("customers", theCustomers);

		return "list-customer";
	}

}
