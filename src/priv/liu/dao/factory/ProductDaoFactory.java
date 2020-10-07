package priv.liu.dao.factory;

import priv.liu.Config;
import priv.liu.dao.product.HibernateProductDao;
import priv.liu.dao.product.JdbcProductDao;
import priv.liu.dao.product.ProductDao;

public class ProductDaoFactory {
	public ProductDao create() {
		ProductDao productDao;
		switch(Config.DATABASE_CONNECTOR) {
		default:
		case JDBC:
			productDao = new JdbcProductDao(); 
			break;
		case HIBERNATE:
			productDao = new HibernateProductDao(); 
			break;
		}
		return productDao;
	}
}
