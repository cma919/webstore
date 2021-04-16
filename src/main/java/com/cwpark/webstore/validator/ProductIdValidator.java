package com.cwpark.webstore.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cwpark.webstore.domain.Product;
import com.cwpark.webstore.exception.ProductNotFoundException;
import com.cwpark.webstore.service.ProductService;

@Component
public class ProductIdValidator implements ConstraintValidator<ProductId, String> {
	@Autowired
	private ProductService productService;

	public void initialize(ProductId constraintAnnotation) {
		/**
		 * Intentionally left blank; this is the place to initialize the constraint
		 * annotation for any sensible default values.
		 */
	}

	public boolean isValid(String value, ConstraintValidatorContext context) {
		Product product;
		try {
			product = productService.getProductById(value);
		} catch (ProductNotFoundException e) {
			return true;
		}
		if (product != null) {
			return false;
		}
		return true;
	}
}