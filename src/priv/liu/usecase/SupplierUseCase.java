package priv.liu.usecase;

import java.util.List;

import priv.liu.dao.ProductDao;
import priv.liu.entity.Product;

public class SupplierUseCase {
	private ProductDao _productDao;
	
	public SupplierUseCase() {
		_productDao = new ProductDao();
	}
	
	public SupplierUseCase(ProductDao productDao) {
		_productDao = productDao;
	}
	
	public List<Product> showProducts() {
		return _productDao.getProducts();
	}
}
