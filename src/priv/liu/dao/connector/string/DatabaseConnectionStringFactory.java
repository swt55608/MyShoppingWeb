package priv.liu.dao.connector.string;

import priv.liu.Config;

public class DatabaseConnectionStringFactory {
	public DatabaseConnectionString create() {
		DatabaseConnectionString connectionString;
		switch(Config.DATABASE_CATEGORY) {
		default:
		case MYSQL:
			connectionString = new MySqlConnectionString();
			break;
		case POSTGRESQL:
			connectionString = new PostgreSqlConnectionString();
			break;
		case HSQL:
			connectionString = new HSqlConnectionString();
			break;
		}
		return connectionString;
	}
}
