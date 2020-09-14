package priv.liu.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import priv.liu.entity.User;

public class UserRepository {
	private Connection _con;
	
	public UserRepository() {
		_con = RepositoryConnector.createConnection();
	}
	
	public boolean register(User user) {
		int rowsUpdated;
		boolean isSuccussful = false;
		try {
			if (isUsernameExisting(user)) {
				String sql = "INSERT INTO users (username, password)"
						+ " VALUES (?, ?);";
				PreparedStatement prestmt = _con.prepareStatement(sql);
				prestmt.setString(1, user.getUsername());
				prestmt.setString(2, user.getPassword());
				rowsUpdated = prestmt.executeUpdate();
				isSuccussful = rowsUpdated > 0;
				prestmt.close();
			}			
		} catch (SQLException e) {
//			System.out.println("Registration Failure!");
			e.printStackTrace();
		}
		return isSuccussful;
	}
	
	private boolean isUsernameExisting(User user) throws SQLException {
		String sql = "SELECT * FROM users"
				+ " WHERE username=?;";
		PreparedStatement prestmt = _con.prepareStatement(sql);
		prestmt.setString(1, user.getUsername());
		ResultSet rs = prestmt.executeQuery();
		return !rs.next();
	}
	
	public boolean login(User user) {
		boolean isSuccessful = false;
		try {
			String sql = "SELECT * FROM users"
					+ " WHERE username=? AND password=?;";
			PreparedStatement prestmt = _con.prepareStatement(sql);
			prestmt.setString(1, user.getUsername());
			prestmt.setString(2, user.getPassword());
			ResultSet rs = prestmt.executeQuery();
			isSuccessful = rs.next();
			rs.close();
			prestmt.close();
		} catch (SQLException e) {
			System.out.println("Login Failure!");
		}
		return isSuccessful;
	}
}
