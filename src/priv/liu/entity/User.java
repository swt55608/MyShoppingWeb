package priv.liu.entity;

public class User {
	private String _username;
	private String _password;
	
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
