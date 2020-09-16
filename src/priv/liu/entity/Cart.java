package priv.liu.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Cart {
	private List<Product> _products;
	private Map<String, Integer> _productQuantities;
	
	public Cart() {
		_products = new ArrayList<Product>();
		_productQuantities = new HashMap<String, Integer>();
	}

	public List<Product> getProducts() {
		return _products;
	}

	public void add(Product product) {
		if (!isExistingInCart(product)) {
			_products.add(product);
			_productQuantities.put(product.getName(), 1);
		} else {
			int newQuantity = _productQuantities.get(product.getName()) + 1;
			_productQuantities.put(product.getName(), newQuantity);
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
	
	public Map<String, Integer> getProductQuantities() {
		return _productQuantities;
	}
	
	public void removeProduct(String productName) {
		for (Product product : _products) {
			if (product.getName().equals(productName)) { 
				_products.remove(product);
				break;
			}
		}
		_productQuantities.remove(productName);
	}
	
}
