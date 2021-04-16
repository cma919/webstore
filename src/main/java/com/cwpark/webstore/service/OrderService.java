package com.cwpark.webstore.service;

import com.cwpark.webstore.domain.Order;

public interface OrderService {
	Long saveOrder(Order order);
}