package priv.liu.entity;

import java.util.ArrayList;
import java.util.List;

public class Cart {
	List<Product> _products;
	
	public Cart() {
		_products = new ArrayList<Product>();
	}

	public List<Product> getProducts() {
		return _products;
	}

	public void add(Product product) {
		_products.add(product);
	}
	
	
}
