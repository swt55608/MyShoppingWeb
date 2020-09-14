package priv.liu.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class RepositoryConnector {
	
	public static Connection createConnection() {
		Connection con = null;
		String driverName = "com.mysql.jdbc.Driver";
		String dbUrl = "jdbc:mysql://localhost/myshoppingweb?serverTimezone=UTC";
		String dbUser = "root";
		String dbPassword = "test1234";
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
}
