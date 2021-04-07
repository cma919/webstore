package com.jbpark.webstore.domain.repository;

import java.util.List;
import java.util.Map;

import com.jbpark.webstore.domain.Product;

public interface ProductRepository {
	void addProduct(Product product);
	Product getProductById(String productID);
	List<Product> getAllProducts(String...args);
	void updateStock(String productId, long noOfUnits);
	List<Product> getProductsByCategory(String category);
	List<Product> getProductsByFilter(Map<String, List<String>> filterParams);
	List<Product> getProdsByMultiFilter(
			String productCategory,
			Map<String, String> price, String brand);	
}
