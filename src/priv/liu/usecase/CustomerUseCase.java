package priv.liu.usecase;

import java.util.List;

import priv.liu.dao.ProductDao;
import priv.liu.entity.Cart;
import priv.liu.entity.Product;
import priv.liu.exception.ProductNotExistException;
import priv.liu.factory.ProductDaoFactory;

public class CustomerUseCase {
	
	public void addToCart(Product product, Cart cart) {
		cart.add(product);
	}
	
	public void removeFromCart(Product product, Cart cart) throws ProductNotExistException {
		cart.remove(product.getName());
	}
}
