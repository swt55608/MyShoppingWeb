package priv.liu.dao.connector;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import priv.liu.dao.connector.string.DatabaseConnectionString;
import priv.liu.dao.connector.string.DatabaseConnectionStringFactory;
import priv.liu.dao.connector.string.HibernateDialectFactory;

public class DatabaseHibernateConnector {
	private final String STR_HB = "hibernate.";
	private final String STR_HB_CON = STR_HB + "connection.";
	
	private DatabaseConnectionString _connectionString;
	private String _dialect;
	
	public DatabaseHibernateConnector() {
		_connectionString = new DatabaseConnectionStringFactory().create();
		_dialect = new HibernateDialectFactory().create();
	}
	
	public Session createSession(Class annotatedClass) {
		Configuration hibernateConfig = new Configuration().configure("/hibernate.cfg.xml");
		hibernateConfig.setProperty(STR_HB_CON + "driver_class", _connectionString.getDriver());
		hibernateConfig.setProperty(STR_HB_CON + "username", _connectionString.getUser());
		hibernateConfig.setProperty(STR_HB_CON + "password", _connectionString.getPassword());
		hibernateConfig.setProperty(STR_HB_CON + "url", _connectionString.getUrl());
		hibernateConfig.setProperty(STR_HB + "dialect", _dialect);
		hibernateConfig.addAnnotatedClass(annotatedClass);
		ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(hibernateConfig.getProperties()).buildServiceRegistry();
		SessionFactory sf = hibernateConfig.buildSessionFactory(reg);
		return sf.openSession();
	}
}
