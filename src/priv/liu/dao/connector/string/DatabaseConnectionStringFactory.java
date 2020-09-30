package priv.liu.dao.connector.string;

import priv.liu.Config;

public class DatabaseConnectionStringFactory {
	public DatabaseConnectionString create() {
		DatabaseConnectionString connectionString;
		switch(Config.databaseCategory.toLowerCase()) {
		default:
		case "mysql":
			connectionString = new MySqlConnectionString();
			break;
		case "postgresql":
			connectionString = new PostgreSqlConnectionString();
			break;
		}
		return connectionString;
	}
}
