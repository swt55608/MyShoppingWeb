package priv.liu.factory;

import priv.liu.Config;
import priv.liu.dao.MySqlProductDao;
import priv.liu.dao.ProductDao;

public class ProductDaoFactory {
	public ProductDao create() {
		ProductDao productDao;
		switch(Config.daoCategory) {
		default:
		case "mysql":
			productDao = new MySqlProductDao();
			break;
//		case "postgresql":
//			productDao = new PostgreSqlProductDao();
//			break;
		}
		return productDao;
	}
}
