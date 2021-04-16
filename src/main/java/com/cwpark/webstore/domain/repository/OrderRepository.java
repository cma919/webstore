package com.cwpark.webstore.domain.repository;

import com.cwpark.webstore.domain.Order;

public interface OrderRepository {
	long saveOrder(Order order);
}
