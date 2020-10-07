package priv.liu.dao.user;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import priv.liu.dao.connector.DatabaseHibernateConnector;
import priv.liu.entity.User;

public class HibernateUserDao extends UserDao {
	private Session _databaseSession;
	
	public HibernateUserDao() {
		_databaseSession = new DatabaseHibernateConnector().createSession(User.class);
	}
	
	@Override
	public boolean register(User user) {
		boolean isRegister = false;
		try {			
			_databaseSession.getTransaction().begin();
			_databaseSession.save(user);
			_databaseSession.getTransaction().commit();
			isRegister = true;
		} catch(Exception e) {
			isRegister = false;
		}
		return isRegister;
	}

	@Override
	public boolean login(User user) {
		_databaseSession.getTransaction().begin();
    	SQLQuery q = _databaseSession.createSQLQuery("SELECT * FROM users WHERE username=:un AND password=:pw");
    	q.setParameter("un", user.getUsername());
    	q.setParameter("pw", user.getPassword());
    	Object userFromDb = q.uniqueResult();
    	_databaseSession.getTransaction().commit();
    	return userFromDb != null;
	}

	@Override
	public boolean isUserExisting(User user) {
		_databaseSession.getTransaction().begin();
		SQLQuery q = _databaseSession.createSQLQuery("SELECT * FROM users WHERE username=:un");
    	q.setParameter("un", user.getUsername());
    	Object userFromDb = q.uniqueResult();
    	_databaseSession.getTransaction().commit();
    	return userFromDb != null;
	}

	@Override
	public boolean delete(User user) {
		_databaseSession.getTransaction().begin();
		SQLQuery q = _databaseSession.createSQLQuery("DELETE FROM users WHERE username=:un");
		q.setParameter("un", user.getUsername());
		int numDeleted = q.executeUpdate();
		_databaseSession.getTransaction().commit();
		return numDeleted == 1;
	}

}
