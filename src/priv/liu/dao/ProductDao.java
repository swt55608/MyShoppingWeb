package priv.liu.dao;

import java.util.List;

import priv.liu.entity.Product;

public abstract class ProductDao {
	public abstract List<Product> getProducts();
}
