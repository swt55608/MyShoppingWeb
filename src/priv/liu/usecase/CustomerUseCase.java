package priv.liu.usecase;

import priv.liu.entity.Cart;
import priv.liu.entity.Product;
import priv.liu.exception.ProductNotExistException;

public class CustomerUseCase {
	
	public void addToCart(Product product, Cart cart) {
		cart.add(product);
	}
	
	public void removeFromCart(Product product, Cart cart) throws ProductNotExistException {
		cart.remove(product.getName());
	}
}
