package com.cwpark.webstore.domain.repository;

import com.cwpark.webstore.domain.Address;

public interface AddressRepository {
	long saveAddress(Address address);
}