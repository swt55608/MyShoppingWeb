package priv.liu.entity;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import priv.liu.ProductAsserter;
import priv.liu.exception.ProductNotExistException;

public class CartTest {
	
	private Cart _cart;

	@Before
	public void setUp() throws Exception {
		_cart = new Cart();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void addGetAProduct_OnlyOneProductWithQuantityOne() {
		Product apple = new Product("apple", 10);
		_cart.add(apple);
		List<Product> expected = new ArrayList<Product>();
		expected.add(apple);
		ProductAsserter.assertProductsEqual(expected, _cart.getProducts());
		Map<String, Integer> productQuantities = _cart.getProductQuantities();
		assertTrue(productQuantities.get(apple.getName()) == 1);
		assertTrue(_cart.size() == 1);
	}
	
	@Test
	public void addAnotherSameKindsProduct_OnlyOneProductWithQuantityTwo() {
		Product apple = new Product("apple", 10);
		_cart.add(apple);
		_cart.add(apple);
		List<Product> expected = new ArrayList<Product>();
		expected.add(apple);
		ProductAsserter.assertProductsEqual(expected, _cart.getProducts());
		Map<String, Integer> productQuantities = _cart.getProductQuantities();
		assertTrue(productQuantities.get(apple.getName()) == 2);
		assertTrue(_cart.size() == 1);
	}
	
	@Test
	public void removeExistingProduct() throws ProductNotExistException {
		Product apple = new Product("apple", 10);
		_cart.add(apple);
		_cart.remove(apple.getName());
		assertTrue(_cart.size() == 0);
	}
	
	@Test(expected = ProductNotExistException.class)
	public void removeNonExistingProduct() throws ProductNotExistException {
		_cart.remove("apple");
	}

}
