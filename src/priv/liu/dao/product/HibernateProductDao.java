package priv.liu.dao.product;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import priv.liu.dao.connector.DatabaseHibernateConnector;
import priv.liu.entity.Product;

public class HibernateProductDao extends ProductDao {
	private Session _databaseSession;
	
	public HibernateProductDao() {
		_databaseSession = new DatabaseHibernateConnector().createSession(Product.class);
	}
	
	@Override
	public List<Product> getProducts() {
		_databaseSession.getTransaction().begin();
		SQLQuery q = _databaseSession.createSQLQuery("SELECT * FROM products");
		q.addEntity(Product.class);
		List<Product> products = q.list();
		_databaseSession.getTransaction().commit();
		return products;
	}

}
