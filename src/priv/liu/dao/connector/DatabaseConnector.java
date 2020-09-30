package priv.liu.dao.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import priv.liu.dao.connector.string.DatabaseConnectionString;
import priv.liu.dao.connector.string.DatabaseConnectionStringFactory;

public class DatabaseConnector {
	
	private DatabaseConnectionString _connectionString;
	
	public DatabaseConnector() {
		_connectionString = new DatabaseConnectionStringFactory().create();
	}
	
	public Connection createConnection() {
		Connection con = null;
		try {
			Class.forName(_connectionString.getDriver());
			con = DriverManager.getConnection(_connectionString.getUrl(), _connectionString.getUser(), _connectionString.getPassword());
		} catch (ClassNotFoundException e) {
			System.out.println("Cannot find this driver name!");
		} catch (SQLException e) {
			System.out.println("Connection Failure!");
		}
		return con;
	}
}
