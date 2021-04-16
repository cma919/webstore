package com.cwpark.webstore.service;

import java.util.List;

import com.cwpark.webstore.domain.Customer;
import com.cwpark.webstore.domain.Customers;

public interface CustomerService {
	List<Customers> getAllCustomers();
	void addCustomer(Customers customer);
	
	List<Customer> getAllCustomer();
	long saveCustomer(Customer customer);
	Customer getCustomer(String customerId);
	Boolean isCustomerExist(String customerId);	
}