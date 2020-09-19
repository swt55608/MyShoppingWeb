package priv.liu.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import priv.liu.entity.Product;

public class ProductRepository {
	
	private Connection _con;
	
	public ProductRepository() {
		_con = DatabaseConnector.createConnection();
	}
	
	public List<Product> getProducts() {
		List<Product> products = new ArrayList<Product>();
		try {
			String sql = "SELECT * FROM products;";
			Statement stmt = _con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Product product;
			String img, name;
			while (rs.next()) {
				img = rs.getString("img");
				name = rs.getString("name");
				product = new Product(img, name);
				products.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return products;
	}
}
