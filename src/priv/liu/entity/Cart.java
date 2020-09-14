package priv.liu.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart {
	private List<Product> _products;
	
	public Cart() {
		_products = new ArrayList<Product>();
	}

	public List<Product> getProducts() {
		return _products;
	}

	public void add(Product product) {
		if (!isExistingInCart(product)) {
			_products.add(product);
			_productQuantities.put(product.getName(), 1);
		} 
	}
	
	private boolean isExistingInCart(Product product) {
		boolean isExisting = false;
		for (Product productInCart : _products) {
			if (product.getName().equals(productInCart.getName()))
				isExisting = true;
		}
		return isExisting;
	}
	
	
}
