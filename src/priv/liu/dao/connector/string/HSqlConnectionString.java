package priv.liu.dao.connector.string;

public class HSqlConnectionString extends DatabaseConnectionString {

	public HSqlConnectionString() {
		super("org.hsqldb.jdbcDriver"
				, "jdbc:hsqldb:mem:tmp"
				, "sa"
				, "");
	}

}
