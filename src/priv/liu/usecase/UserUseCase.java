package priv.liu.usecase;

import priv.liu.dao.factory.UserDaoFactory;
import priv.liu.dao.user.UserDao;
import priv.liu.entity.User;
import priv.liu.exception.EmptyInputException;
import priv.liu.exception.InvalidPasswordException;
import priv.liu.exception.UserExistException;

public class UserUseCase {
	private UserDao _userDao;

	public UserUseCase() {
		_userDao = new UserDaoFactory().create();
	}
	
	public UserUseCase(UserDao userDao) {
		_userDao = userDao;
	}

	public void register(String username, String password) throws EmptyInputException, UserExistException, InvalidPasswordException {
		if (isNullOrEmpty(username) || isNullOrEmpty(password))
			throw new EmptyInputException();
		else if (isUserExisting(username))
			throw new UserExistException();
		else if (isPasswordInvalid(password)) 
			throw new InvalidPasswordException();
		else
			_userDao.register(new User(username, password));
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
	
	private boolean isPasswordInvalid(String password) {
		return !(password.length() >= 6 && password.length() <= 15 &&
                password.matches(".*[a-zA-Z]+.*"));
	}
}
