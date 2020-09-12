package priv.liu.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import priv.liu.entity.User;

public class UserRepository {
	private Connection _con;
	
	public UserRepository() {
		String driverName = "com.mysql.jdbc.Driver";
		String dbUrl = "jdbc:mysql://localhost/myshoppingweb?serverTimezone=UTC";
		String dbUser = "root";
		String dbPassword = "test1234";
		_con = createConnection(driverName, dbUrl, dbUser, dbPassword);
	}
	
	private Connection createConnection(String driverName, String dbUrl, String dbUser, String dbPassword) {
		Connection con = null;
		try {
			Class.forName(driverName);
			con = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
		} catch (ClassNotFoundException e) {
			System.out.println("Cannot find this driver name!");
		} catch (SQLException e) {
			System.out.println("Connection Failure!");
		}
		return con;
	}
	
	public boolean register(User user) {
		int rowsUpdated;
		boolean isSuccussful = false;
		try {
			String sql = "INSERT INTO users (username, password)"
					+ " VALUES (?, ?);";
			PreparedStatement prestmt = _con.prepareStatement(sql);
			prestmt.setString(1, user.getUsername());
			prestmt.setString(2, user.getPassword());
			rowsUpdated = prestmt.executeUpdate();
			isSuccussful = rowsUpdated > 0;
		} catch (SQLException e) {
			System.out.println("Registration Failure!");
		}
		return isSuccussful;
	}
}
