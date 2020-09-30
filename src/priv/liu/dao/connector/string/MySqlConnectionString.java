package priv.liu.dao.connector.string;

public class MySqlConnectionString extends DatabaseConnectionString {
	public MySqlConnectionString() {
		super("com.mysql.jdbc.Driver"
				, "jdbc:mysql://localhost/myshoppingweb?serverTimezone=UTC"
				, "root"
				, "test1234");
	}
}
