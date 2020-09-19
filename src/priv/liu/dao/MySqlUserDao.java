package priv.liu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import priv.liu.entity.User;

public class MySqlUserDao extends UserDao {
	private Connection _con;
	
	public MySqlUserDao() {
		_con = DatabaseConnector.createConnection();
	}
	
	@Override
	public boolean register(User user) {
		int rowsUpdated = 0;
		boolean isSuccussful = false;
		try {
			String sql = "INSERT INTO users (username, password)"
					+ " VALUES (?, ?);";
			PreparedStatement prestmt = _con.prepareStatement(sql);
			prestmt.setString(1, user.getUsername());
			prestmt.setString(2, user.getPassword());
			rowsUpdated = prestmt.executeUpdate();
			isSuccussful = rowsUpdated > 0;
			prestmt.close();		
		} catch (SQLException e) {
//			e.printStackTrace();
			isSuccussful = false;
		}
		return isSuccussful;
	}
	
	@Override
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
	
	@Override
	public boolean isUserExisting(User user) {
		boolean ret = false;
		try {
			String sql = "SELECT * FROM users"
					+ " WHERE username=?;";
			PreparedStatement prestmt = _con.prepareStatement(sql);
			prestmt.setString(1, user.getUsername());
			ResultSet rs = prestmt.executeQuery();
			ret = rs.next();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	public boolean delete(User user) {
		int rowsUpdated = 0;
		boolean ret = false;
		try {
			String sql = "DELETE FROM users"
					+ " WHERE username=? AND password=?;";
			PreparedStatement prestmt = _con.prepareStatement(sql);
			prestmt.setString(1, user.getUsername());
			prestmt.setString(2, user.getPassword());
			rowsUpdated = prestmt.executeUpdate();
			ret = rowsUpdated == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

	
}
