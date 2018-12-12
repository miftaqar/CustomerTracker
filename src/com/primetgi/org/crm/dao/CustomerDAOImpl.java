package com.primetgi.org.crm.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.primetgi.org.crm.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	// need to inject Hibernate Sessin Factory

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional()
	public List<Customer> getCustomers() {

		// get the current Hibernate Session
		Session currentSession = sessionFactory.getCurrentSession();

		// create the query
		Query<Customer> query = currentSession.createQuery("from Customer", Customer.class);

		// execute the query and get the list
		List<Customer> customers = query.getResultList();

		// return the results
		return customers;
	}

}