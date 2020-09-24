package priv.liu.usecase;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import priv.liu.ProductAsserter;
import priv.liu.dao.MySqlProductDao;
import priv.liu.dao.ProductDao;
import priv.liu.entity.Cart;
import priv.liu.entity.Product;
import priv.liu.exception.ProductNotExistException;

public class CustomerUseCaseTest {
	
	private CustomerUseCase _customerUseCase;
	private ProductDao _productDao;

	@Before
	public void setUp() throws Exception {
		_productDao = mock(MySqlProductDao.class);
		_customerUseCase = new CustomerUseCase(_productDao);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void viewAllKindsProductsInStock_Success() {
		List<Product> expected = new ArrayList<Product>();
		expected.add(new Product("apple", 10));
		expected.add(new Product("banana", 20));
		doReturn(expected).when(_productDao).getProducts();
		List<Product> actual = _customerUseCase.viewProductsInStock();
		ProductAsserter.assertProductsEqual(expected, actual);
	}
	
	@Test
	public void addAProductToCart_Success() {
		Cart cart = new Cart();
		Product product = new Product("apple", 10);
		_customerUseCase.addToCart(product, cart);
		assertTrue(cart.size() == 1);
		List<Product> expected = new ArrayList<Product>();
		expected.add(product);
		ProductAsserter.assertProductsEqual(expected, cart.getProducts());
	}
	
	@Test
	public void addDuplicativeProductsToCart_Fail() {
		Cart cart = new Cart();
		Product product = new Product("apple", 10);
		_customerUseCase.addToCart(product, cart);
		assertTrue(cart.size() == 1);
		_customerUseCase.addToCart(product, cart);
		assertTrue(cart.size() == 1);
		Product duplicativeProduct = new Product("apple", 10);
		_customerUseCase.addToCart(duplicativeProduct, cart);
		assertTrue(cart.size() == 1);
		List<Product> expected = new ArrayList<Product>();
		expected.add(product);
		ProductAsserter.assertProductsEqual(expected, cart.getProducts());
	}

	@Test
	public void removeProductFromCart_Success() throws ProductNotExistException {
		Cart cart = new Cart();
		Product apple = new Product("apple", 10);
		Product banana = new Product("banana", 20);
		_customerUseCase.addToCart(apple, cart);
		_customerUseCase.addToCart(banana, cart);
		assertTrue(cart.size() == 2);
		_customerUseCase.removeFromCart(apple, cart);
		assertTrue(cart.size() == 1);
		List<Product> expected = new ArrayList<Product>();
		expected.add(banana);
		ProductAsserter.assertProductsEqual(expected, cart.getProducts());
	}
	
	@Test(expected = ProductNotExistException.class)
	public void removeNonExistProductFromCart_Fail() throws ProductNotExistException {
		Cart cart = new Cart();
		Product apple = new Product("apple", 10);
		_customerUseCase.removeFromCart(apple, cart);
	}

}
