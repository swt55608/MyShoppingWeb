package priv.liu;

import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.List;

import priv.liu.entity.Product;

public class ProductAsserter {
	public static void assertProductsEqual(List<Product> expected, List<Product> actual) {
		assertTrue(expected.size() == actual.size());
		Product pInExepected, pInActual;
		Iterator expectedIterator = expected.iterator();
		Iterator actualIterator = actual.iterator();
		while (expectedIterator.hasNext() && actualIterator.hasNext()) {
			pInExepected = (Product) expectedIterator.next();
			pInActual = (Product) actualIterator.next();
			assertProductEqual(pInExepected, pInActual);
		}
	}
	
	public static void assertProductEqual(Product expected, Product actual) {
		assertTrue(expected.getName() == actual.getName());
		assertTrue(expected.getPrice() == actual.getPrice());
	}
}
