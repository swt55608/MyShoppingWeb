package priv.liu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@Column(name = "id")
	private int _id;
	@Column(name = "username")
	private String _username;
	@Column(name = "password")
	private String _password;
	
	public User() {}
	
	public User(String username, String password) {
		setUsername(username);
		setPassword(password);
	}
	
	public String getUsername() {
		return _username;
	}
	
	public void setUsername(String username) {
		this._username = username;
	}
	
	public String getPassword() {
		return _password;
	}
	
	public void setPassword(String password) {
		this._password = password;
	}
}
