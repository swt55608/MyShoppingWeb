package priv.liu.dao;

import priv.liu.entity.User;

public abstract class UserDao {
	public abstract boolean register(User user);
	public abstract boolean login(User user);
	public abstract boolean isUserExisting(User user);
	public abstract boolean delete(User user);
}
