package com.cwpark.webstore.domain.repository.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cwpark.webstore.domain.Cart;
import com.cwpark.webstore.domain.CartItem;
import com.cwpark.webstore.domain.Product;
import com.cwpark.webstore.domain.repository.CartRepository;
import com.cwpark.webstore.dto.CartDto;
import com.cwpark.webstore.dto.CartItemDto;
import com.cwpark.webstore.service.ProductService;

@Repository
public class MariaCartRepository implements CartRepository {
	@Autowired
	private NamedParameterJdbcTemplate jdbcTempleate;
	@Autowired
	private ProductService productService;

	void insertCartItem(CartItemDto cartItemDto, String cartId) {
		Product productById = productService.getProductById(cartItemDto.getProductId());
		String INSERT_CART_ITEM_SQL = "INSERT INTO " + "CART_ITEM(ID,PRODUCT_ID,CART_ID,QUANTITY) "
				+ "VALUES (:id, :product_id, :cart_id, :quantity)";

		Map<String, Object> cartItemsParams = new HashMap<String, Object>();
		cartItemsParams.put("id", cartItemDto.getId());
		cartItemsParams.put("product_id", productById.getProductId());
		cartItemsParams.put("cart_id", cartId);
		cartItemsParams.put("quantity", cartItemDto.getQuantity());
		jdbcTempleate.update(INSERT_CART_ITEM_SQL, cartItemsParams);
	}
	
	@Override
	public void create(CartDto cartDto) {
		String INSERT_CART_SQL = "INSERT INTO CART(ID) VALUES (:id)";
		Map<String, Object> cartParams = new HashMap<String, Object>();

		cartParams.put("id", cartDto.getId());
		jdbcTempleate.update(INSERT_CART_SQL, cartParams);
		cartDto.getCartItems().stream().forEach(cartItemDto -> {
			insertCartItem(cartItemDto, cartDto.getId());
		});
	}

	@Override
	public Cart read(String id) {
		String SQL = "SELECT * FROM CART WHERE ID = :id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		CartMapper cartMapper = new CartMapper(jdbcTempleate, productService);
		try {
			return jdbcTempleate.queryForObject(SQL, params, cartMapper);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	// @formatter:off
	/**
	 * 
	 */
	@Override
	public void update(String id, CartDto cartDto) 
			throws DataAccessException {
		List<CartItemDto> cartItems = cartDto.getCartItems();
		for (CartItemDto cartItem : cartItems) {
			String SQL = "select count(*) from cart_item ci "
					+ "where ci.ID = :ID";
			Map<String, Object> params = new HashMap<String, Object>();
			
			params.put("ID", cartItem.getId());
			
			int count = jdbcTempleate.queryForObject(SQL, params, Integer.class);
			
			if (jdbcTempleate.queryForObject(SQL, params, Integer.class) == 0) {
				insertCartItem(cartItem, id);
			} else {
				SQL = "UPDATE CART_ITEM SET "
						+ "QUANTITY = QUANTITY + :quantity, " 
						+ "PRODUCT_ID = :productId "
						+ "WHERE ID = :id AND CART_ID = :cartId";
				params.clear();
				params.put("id", cartItem.getId());
				params.put("quantity", cartItem.getQuantity());
				params.put("productId", cartItem.getProductId());
				params.put("cartId", id);
				jdbcTempleate.update(SQL, params);
			}
		}
	}
	// @formatter:on

	@Override
	public void delete(String id) {
		String SQL_DELETE_CART_ITEM = "DELETE FROM CART_ITEM WHERE CART_ID = :id";
		String SQL_DELETE_CART = "DELETE FROM CART WHERE ID = :id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		jdbcTempleate.update(SQL_DELETE_CART_ITEM, params);
		jdbcTempleate.update(SQL_DELETE_CART, params);
	}

	@Override
	public void addItem(String cartId, String productId) {
		String SQL = null;
		Cart cart = null;
		cart = read(cartId);
		if (cart == null) {
			CartItemDto newCartItemDto = new CartItemDto();
			newCartItemDto.setId(cartId + productId);
			newCartItemDto.setProductId(productId);
			newCartItemDto.setQuantity(1);
			CartDto newCartDto = new CartDto(cartId);
			newCartDto.addCartItem(newCartItemDto);
			create(newCartDto);
			return;
		}
		Map<String, Object> cartItemsParams = new HashMap<String, Object>();
		if (cart.getItemByProductId(productId) == null) {
			SQL = "INSERT INTO CART_ITEM " + "(ID, PRODUCT_ID, CART_ID, QUANTITY) VALUES "
					+ "(:id, :productId, :cartId, :quantity)";
			cartItemsParams.put("id", cartId + productId);
			cartItemsParams.put("quantity", 1);
		} else {
			SQL = "UPDATE CART_ITEM SET QUANTITY = :quantity " + "WHERE CART_ID = :cartId AND "
					+ "PRODUCT_ID = :productId";
			CartItem existingItem = cart.getItemByProductId(productId);
			cartItemsParams.put("id", existingItem.getId());
			cartItemsParams.put("quantity", existingItem.getQuantity() + 1);
		}
		cartItemsParams.put("productId", productId);
		cartItemsParams.put("cartId", cartId);
		jdbcTempleate.update(SQL, cartItemsParams);
	}

	@Override
	public void removeItem(String cartId, String productId) {
		String delQry = "DELETE FROM CART_ITEM WHERE ";
		delQry += "PRODUCT_ID = :productId AND CART_ID = :id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", cartId);
		params.put("productId", productId);
		jdbcTempleate.update(delQry, params);
	}

	@Override
	public void clearCart(String cartId) {
		String SQL_DELETE_CART_ITEM = "DELETE FROM CART_ITEM WHERE CART_ID = :id";
		Map<String, Object> params = new HashMap<>();
		params.put("id", cartId);
		jdbcTempleate.update(SQL_DELETE_CART_ITEM, params);
	}

}