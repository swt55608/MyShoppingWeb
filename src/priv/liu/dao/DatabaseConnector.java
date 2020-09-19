package priv.liu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
	String _dbUser = "root";
	String _dbPassword = "test1234";
	
	public Connection createConnection(String driverName, String dbUrl) {
		Connection con = null;
		try {
			Class.forName(driverName);
			con = DriverManager.getConnection(dbUrl, _dbUser, _dbPassword);
		} catch (ClassNotFoundException e) {
			System.out.println("Cannot find this driver name!");
		} catch (SQLException e) {
			System.out.println("Connection Failure!");
		}
		return con;
	}
}
