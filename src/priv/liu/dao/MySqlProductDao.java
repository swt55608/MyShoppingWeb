package priv.liu.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import priv.liu.entity.Product;

public class MySqlProductDao extends ProductDao {
	
	private Connection _con;
	
	public MySqlProductDao() {
		String driverName = "com.mysql.jdbc.Driver";
		String dbUrl = "jdbc:mysql://localhost/myshoppingweb?serverTimezone=UTC";
		_con = new DatabaseConnector().createConnection(driverName, dbUrl);
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
			while (rs.next()) {
				name = rs.getString("name");
				price = Integer.parseInt(rs.getString("price"));
				product = new Product(name, price);
				products.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return products;
	}
}
