package priv.liu.dao.connector.string;

import priv.liu.Config;

public class HibernateDialectFactory {
	public String create() {
		String dialect = "org.hibernate.dialect.";
		switch(Config.DATABASE_CATEGORY) {
		default:
		case MYSQL:
			dialect += "MySQLDialect";
			break;
		case POSTGRESQL:
			dialect += "PostgreSQLDialect";
			break;
		case HSQL:
			dialect += "HSQLDialect";
			break;
		}
		return dialect;
	}
}
