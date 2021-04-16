package com.cwpark.webstore.domain.repository;

import java.util.List;

import com.cwpark.webstore.domain.Customer;
import com.cwpark.webstore.domain.Customers;

public interface CustomerRepository {
	List<Customers> getAllCustomers();
	void addCustomer(Customers customer);
	List<Customer> getAllCustomer();
	Customer getAcustomer(String customerId);
	long saveCustomer(Customer customer);
	Boolean isCustomerExist(String customerId);
}