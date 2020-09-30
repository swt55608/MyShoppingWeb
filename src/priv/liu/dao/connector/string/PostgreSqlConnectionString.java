package priv.liu.dao.connector.string;

public class PostgreSqlConnectionString extends DatabaseConnectionString {
	public PostgreSqlConnectionString() {
		super("org.postgresql.Driver"
				, "jdbc:postgresql://localhost:5432/myshoppingweb?serverTimezone=UTC"
				, "postgres"
				, "test1234");
	}
}
