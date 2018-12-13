package com.primetgi.org.crm.dao;

import java.util.List;

import com.primetgi.org.crm.entity.Customer;

public interface CustomerDAO {

	public List<Customer> getCustomers();

	public void saveCustomer(Customer theCustomer);

	public Customer getCustomer(int theId);

}
