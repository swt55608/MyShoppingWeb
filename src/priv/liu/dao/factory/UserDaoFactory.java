package priv.liu.dao.factory;

import priv.liu.Config;
import priv.liu.dao.user.HibernateUserDao;
import priv.liu.dao.user.JdbcUserDao;
import priv.liu.dao.user.UserDao;

public class UserDaoFactory {
	public UserDao create() {
		UserDao userDao;
		switch(Config.DATABASE_CONNECTOR) {
		default:
		case JDBC:
			userDao = new JdbcUserDao(); 
			break;
		case HIBERNATE:
			userDao = new HibernateUserDao(); 
			break;
		}
		return userDao;
	}
}
