package com.primetgi.org.crm.service;

import java.util.List;

import com.primetgi.org.crm.entity.Customer;

public interface CustomerService {

	public List<Customer> getCustomers();

	public void saveCustomer(Customer theCustomer);
}
