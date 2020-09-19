package priv.liu.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import priv.liu.entity.User;

public class MySqlUserDaoTest {

	private UserDao _userDao;
	private List<User> _toDeleteUsers;
	
	@Before
	public void setUp() throws Exception {
		_userDao = new MySqlUserDao();
		_toDeleteUsers = new ArrayList<User>();
	}

	@After
	public void tearDown() throws Exception {
		for(User user : _toDeleteUsers) {
			_userDao.delete(user);
		}
		_toDeleteUsers.clear();
	}
	
	private User register(User user) {
		assertTrue(_userDao.register(user));
		_toDeleteUsers.add(user);
		return user;
	}

	@Test
	public void isRegister_True_UserNotExisting() {
		User user = new User("tester", "secret");
		assertFalse(_userDao.isUserExisting(user));
		register(user);
		assertTrue(_userDao.isUserExisting(user));
	}
	
	@Test
	public void isRegister_False_UserExisting() {		
		User user = new User("tester", "secret");
		register(user);
		assertFalse(_userDao.register(user));
	}
	
	@Test
	public void isRegister_False_UsernameNull() {
		User user = new User(null, "secret");
		assertFalse(_userDao.register(user));
	}
	
	@Test
	public void isRegister_False_UsernameEmpty() {
		User user = new User("", "secret");
		assertFalse(_userDao.register(user));
	}
	
	@Test
	public void isRegister_False_PasswordNull() {
		User user = new User("tester", null);
		assertFalse(_userDao.register(user));
	}
	
	@Test
	public void isRegister_False_PasswordEmpty() {
		User user = new User("tester", "");
		assertFalse(_userDao.register(user));
	}
	
	@Test
	public void isLogin_True_UsernamePasswordCorrect() {
		User user = register(new User("tester", "secret"));
		assertTrue(_userDao.login(user));
	}
	
	@Test
	public void isLogin_False_UsernameIncorrect() {
		register(new User("tester", "secret"));
		User toLoginUser = new User("newTester", "secret");
		assertFalse(_userDao.login(toLoginUser));
	}
	
	@Test
	public void isLogin_False_PasswordIncorrect() {
		register(new User("tester", "secret"));
		User toLoginUser = new User("tester", "newSecret");
		assertFalse(_userDao.login(toLoginUser));
	}
	
	@Test
	public void isLogin_False_UsernameNull() {
		User user = new User(null, "secret");
		assertFalse(_userDao.login(user));
	}
	
	@Test
	public void isLogin_False_UsernameEmpty() {
		User user = new User("", "secret");
		assertFalse(_userDao.login(user));
	}
	
	@Test
	public void isLogin_False_PasswordNull() {
		User user = new User("tester", null);
		assertFalse(_userDao.login(user));
	}
	
	@Test
	public void isLogin_False_PasswordEmpty() {
		User user = new User("tester", "");
		assertFalse(_userDao.login(user));
	}

}
