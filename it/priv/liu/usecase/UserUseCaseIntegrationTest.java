package priv.liu.usecase;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import priv.liu.dao.MySqlUserDao;
import priv.liu.dao.user.UserDao;
import priv.liu.entity.User;
import priv.liu.factory.UserDaoFactory;

public class UserUseCaseIntegrationTest {

	private UserUseCase _userUseCase;
	private UserDao _userDao;
	private List<User> _toDeleteUsers;
	
	@Before
	public void setUp() throws Exception {
		_userDao = new UserDaoFactory().create();
		_userUseCase = new UserUseCase(_userDao);
		_toDeleteUsers = new ArrayList<User>();
	}

	@After
	public void tearDown() throws Exception {
		for(User user : _toDeleteUsers) {
			_userDao.delete(user);
		}
		_toDeleteUsers.clear();
	}

	@Test
	public void isRegister_True_UsernamePasswordCorrect() {
		String username = "tester", password = "secret";
		assertTrue(_userUseCase.register(username, password));
		_toDeleteUsers.add(new User(username, password));
	}
	
	@Test
	public void isRegister_False_UsernameExisting() {
		String username = "tester", password = "secret";
		assertTrue(_userUseCase.register(username, password));
		_toDeleteUsers.add(new User(username, password));
		assertFalse(_userUseCase.register(username, password));
	}
	
	@Test
	public void isRegister_False_UsernameNull() {
		assertFalse(_userUseCase.register(null, "secret"));
	}
	
	@Test
	public void isRegister_False_UsernameEmpty() {
		assertFalse(_userUseCase.register("", "secret"));
	}
	
	@Test
	public void isRegister_False_PasswordNull() {
		assertFalse(_userUseCase.register("tester", null));
	}
	
	@Test
	public void isRegister_False_PasswordEmpty() {
		assertFalse(_userUseCase.register("tester", ""));
	}
	
	@Test
	public void isLogin_True_UsernamePasswordCorrect() {
		String username = "tester", password = "secret";
		_userUseCase.register(username, password);
		_toDeleteUsers.add(new User(username, password));
		assertTrue(_userUseCase.login(username, password));
	}
	
	@Test
	public void isLogin_False_UsernameIncorrect() {
		String username = "tester", password = "secret";
		_userUseCase.register(username, password);
		_toDeleteUsers.add(new User(username, password));
		assertFalse(_userUseCase.login("newTester", password));
	}
	
	@Test
	public void isLogin_False_PasswordIncorrect() {
		String username = "tester", password = "secret";
		_userUseCase.register(username, password);
		_toDeleteUsers.add(new User(username, password));
		assertFalse(_userUseCase.login(username, "newSecret"));
	}
	
	@Test
	public void isLogin_False_UsernameNull() {
		assertFalse(_userUseCase.login(null, "secret"));
	}
	
	@Test
	public void isLogin_False_UsernameEmpty() {
		assertFalse(_userUseCase.login("", "secret"));
	}
	
	@Test
	public void isLogin_False_PasswordNull() {
		assertFalse(_userUseCase.login("tester", null));
	}
	
	@Test
	public void isLogin_False_PasswordEmpty() {
		assertFalse(_userUseCase.login("tester", ""));
	}

}
