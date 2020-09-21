package priv.liu.usecase;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import priv.liu.dao.MySqlProductDao;
import priv.liu.dao.ProductDao;
import priv.liu.entity.Product;

public class SupplierUseCaseTest {
	
	private SupplierUseCase _supplierUseCase;
	private ProductDao _productDao;

	@Before
	public void setUp() throws Exception {
		_productDao = mock(MySqlProductDao.class);
		_supplierUseCase = new SupplierUseCase(_productDao);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	private void assertSameProducts(List<Product> expected, List<Product> actual) {
		assertTrue(expected.size() == actual.size());
		Product pInExepected, pInActual;
		Iterator expectedIterator = expected.iterator();
		Iterator actualIterator = actual.iterator();
		while (expectedIterator.hasNext() && actualIterator.hasNext()) {
			pInExepected = (Product) expectedIterator.next();
			pInActual = (Product) actualIterator.next();
			assertProducts(pInExepected, pInActual);
		}
	}
	
	private void assertProducts(Product expected, Product actual) {
		assertTrue(expected.getName() == actual.getName());
		assertTrue(expected.getPrice() == actual.getPrice());
	}

	@Test
	public void testGetProducts() {
		List<Product> expected = new ArrayList<Product>();
		expected.add(new Product("apple", 10));
		expected.add(new Product("banana", 20));
		doReturn(expected).when(_productDao).getProducts();
		List<Product> actual = _supplierUseCase.getProducts();
		assertSameProducts(expected, actual);
	}

}
