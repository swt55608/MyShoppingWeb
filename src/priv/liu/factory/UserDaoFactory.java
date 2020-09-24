package priv.liu.factory;

import priv.liu.Config;
import priv.liu.dao.MySqlUserDao;
import priv.liu.dao.UserDao;

public class UserDaoFactory {
	public UserDao create() {
		UserDao userDao;
		switch(Config.daoCategory) {
		default:
		case "mysql":
			userDao = new MySqlUserDao();
			break;
//		case "postgresql":
//			userDao = new PostgreSqlUserDao();
//			break;
		}
		return userDao;
	}
}
