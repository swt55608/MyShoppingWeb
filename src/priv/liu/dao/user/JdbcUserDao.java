package priv.liu.dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import priv.liu.dao.connector.DatabaseJdbcConnector;
import priv.liu.entity.User;

public class JdbcUserDao extends UserDao {
	
	private Connection _con;
	
	public JdbcUserDao() {
		_con = new DatabaseJdbcConnector().createConnection();
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
		boolean isExist = false;
		try {
			String sql = "SELECT * FROM users"
					+ " WHERE username=?;";
			PreparedStatement prestmt = _con.prepareStatement(sql);
			prestmt.setString(1, user.getUsername());
			ResultSet rs = prestmt.executeQuery();
			isExist = rs.next();
			rs.close();
			prestmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return isExist;
	}
	
	@Override
	public boolean delete(User user) {
		int rowsUpdated = 0;
		boolean isDeleted = false;
		try {
			String sql = "DELETE FROM users"
					+ " WHERE username=? AND password=?;";
			PreparedStatement prestmt = _con.prepareStatement(sql);
			prestmt.setString(1, user.getUsername());
			prestmt.setString(2, user.getPassword());
			rowsUpdated = prestmt.executeUpdate();
			isDeleted = rowsUpdated == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isDeleted;
	}
}
