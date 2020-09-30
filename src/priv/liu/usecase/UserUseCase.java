package priv.liu.usecase;

import priv.liu.dao.UserDao;
import priv.liu.entity.User;

public class UserUseCase {
	private UserDao _userDao;

	public UserUseCase() {
		_userDao = new UserDao();
	}
	
	public UserUseCase(UserDao userDao) {
		_userDao = userDao;
	}

	public boolean register(String username, String password) {
		boolean isRegister = false;
		if (isNullOrEmpty(username) || isNullOrEmpty(password))
			isRegister = false;
		else if (isUserExisting(username))
			isRegister = false;
		else
			isRegister = _userDao.register(new User(username, password));
		return isRegister;
	}

	public boolean login(String username, String password) {
		boolean isLogin = false;
		if (isNullOrEmpty(username) || isNullOrEmpty(password))
			isLogin = false;
		else
			isLogin = _userDao.login(new User(username, password));
		return isLogin;
	}
	
	private boolean isNullOrEmpty(String input) {
		return input == null || input.isEmpty();
	}

	private boolean isUserExisting(String username) {
		return _userDao.isUserExisting(new User(username, null));
	}
}
