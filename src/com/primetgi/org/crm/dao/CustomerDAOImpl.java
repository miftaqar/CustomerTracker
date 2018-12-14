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
	// @Transactional() --No longer needed as @Transactional is defined in
	// serviceImpl layer
	public List<Customer> getCustomers() {

		// get the current Hibernate Session
		Session currentSession = sessionFactory.getCurrentSession();

		// create the query...and sort by lastName
		Query<Customer> query = currentSession.createQuery("from Customer order by lastName", Customer.class);

		// execute the query and get the list
		List<Customer> customers = query.getResultList();

		// return the results
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {

		// get the current Hibernate Session
		Session currentSession = sessionFactory.getCurrentSession();

		// save/update the customer
		// currentSession.save(theCustomer); --Modified when Update functionality is
		// implemented
		currentSession.saveOrUpdate(theCustomer);

	}

	@Override
	public Customer getCustomer(int theId) {
		// get the current Hibernate Session
		Session currentSession = sessionFactory.getCurrentSession();

		// now retrieve/read from database using the primary key
		Customer customer = currentSession.get(Customer.class, theId);

		return customer;
	}

	@Override
	public void deleteCustomer(int theId) {

		// get the current Hibernate Session
		Session currentSession = sessionFactory.getCurrentSession();

		// delete object with primary key
		Query<Customer> theQuery = currentSession.createQuery("delete from Customer where customerId=:id");

		theQuery.setParameter("id", theId);

		theQuery.executeUpdate();
	}

	@Override
	public List<Customer> searchCustomer(String theCustomerName) {
		
		// get the current Hibernate Session
		Session currentSession = sessionFactory.getCurrentSession();

		Query<Customer> query = null;

		// only search the name if the search Name is not empty... case insensitive

		if (theCustomerName != null && theCustomerName.trim().length() > 0) {
			query = currentSession.createQuery(
					"from Customer where lower(firstName) like:theName or lower(lastName) like:theName",
					Customer.class);
			query.setParameter("theName", "%" + theCustomerName.toLowerCase() + "%");
		} else {
			// theSearchName is empty ... so just get all customers
			query = currentSession.createQuery("from Customer", Customer.class);
		}

		// execute the query and get the result
		List<Customer> customers = query.getResultList();
		
		return customers;
	}

}
