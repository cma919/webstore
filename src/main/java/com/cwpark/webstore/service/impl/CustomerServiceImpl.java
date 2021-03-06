package com.cwpark.webstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.cwpark.webstore.domain.Customer;
import com.cwpark.webstore.domain.Customers;
import com.cwpark.webstore.domain.repository.CustomerRepository;
import com.cwpark.webstore.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public List<Customers> getAllCustomers() {
		return customerRepository.getAllCustomers();
	}

	@Override
	public void addCustomer(Customers customer) throws DataAccessException {
		customerRepository.addCustomer(customer);
	}

	@Override
	public List<Customer> getAllCustomer() {
		return customerRepository.getAllCustomer();
	}

	@Override
	public long saveCustomer(Customer customer) {
		return customerRepository.saveCustomer(customer);
	}

	@Override
	public Customer getCustomer(String customerId) {
		return customerRepository.getAcustomer(customerId);
	}

	@Override
	public Boolean isCustomerExist(String customerId) {
		return customerRepository.isCustomerExist(customerId);
	}

}