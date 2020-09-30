package priv.liu.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import priv.liu.dao.connector.DatabaseConnector;
import priv.liu.entity.Product;

public class ProductDao {
	private Connection _con;
	
	public ProductDao() {
		_con = new DatabaseConnector().createConnection();
	}
	
	public List<Product> getProducts() {
		List<Product> products = new ArrayList<Product>();
		try {
			String sql = "SELECT * FROM products;";
			Statement stmt = _con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			Product product;
			String name;
			int price;
			String img;
			while (rs.next()) {
				name = rs.getString("name");
				price = Integer.parseInt(rs.getString("price"));
				img = rs.getString("img");
				product = new Product(name, price, img);
				products.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return products;
	}
}
