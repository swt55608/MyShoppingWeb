package priv.liu.dao.connector.string;

public class DatabaseConnectionString {
	private String _driver;
	private String _url;
	private String _user;
	private String _password;
	
	protected DatabaseConnectionString(String driver, String url, String user, String password) {
		_driver = driver;
		_url = url;
		_user = user;
		_password = password;
	}

	public String getDriver() {
		return _driver;
	}

	public String getUrl() {
		return _url;
	}

	public String getUser() {
		return _user;
	}

	public String getPassword() {
		return _password;
	}
}
