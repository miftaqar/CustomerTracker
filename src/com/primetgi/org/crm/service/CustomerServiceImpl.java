package com.primetgi.org.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.primetgi.org.crm.dao.CustomerDAO;
import com.primetgi.org.crm.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	// need to inject CustomerDAO
	@Autowired
	private CustomerDAO customerDAO;

	@Override
	@Transactional
	public List<Customer> getCustomers() {
		return customerDAO.getCustomers();
	}

}
