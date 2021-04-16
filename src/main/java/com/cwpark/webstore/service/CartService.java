package com.cwpark.webstore.service;

import com.cwpark.webstore.domain.Cart;
import com.cwpark.webstore.dto.CartDto;

public interface CartService {
	void create(CartDto cartDto);
	Cart read(String cartId);
	void update(String cartId, CartDto cartDto);
	void delete(String id);
	void addItem(String cartId, String productId);
	void removeItem(String cartId, String productId);
	Cart validate(String cartId);
	void clearCart(String cartId);
}