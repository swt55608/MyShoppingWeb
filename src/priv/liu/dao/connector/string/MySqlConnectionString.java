package priv.liu.dao.connector.string;

public class MySqlConnectionString extends DatabaseConnectionString {
	public MySqlConnectionString() {
		super("com.mysql.jdbc.Driver"
				, "jdbc:mysql://localhost:3306/myshoppingweb?serverTimezone=UTC"
				, "root"
				, "test1234");
	}
}
