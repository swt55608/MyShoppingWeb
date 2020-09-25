package priv.liu.usecase;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import priv.liu.ProductAsserter;
import priv.liu.dao.MySqlProductDao;
import priv.liu.dao.ProductDao;
import priv.liu.entity.Product;

public class SupplierUseCaseTest {
	private ProductDao _productDao;
	private SupplierUseCase _supplierUseCase;

	@Before
	public void setUp() throws Exception {
		_productDao = mock(MySqlProductDao.class);
		_supplierUseCase = new SupplierUseCase(_productDao);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void showProducts_Success() {
		List<Product> expected = new ArrayList<Product>();
		expected.add(new Product("apple", 10));
		expected.add(new Product("banana", 20));
		doReturn(expected).when(_productDao).getProducts();
		List<Product> actual = _supplierUseCase.showProducts();
		ProductAsserter.assertProductsEqual(expected, actual);
	}

}
