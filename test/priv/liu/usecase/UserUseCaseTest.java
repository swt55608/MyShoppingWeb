package priv.liu.usecase;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import priv.liu.dao.user.UserDao;
import priv.liu.entity.User;
import priv.liu.factory.UserDaoFactory;

public class UserUseCaseTest {
	
	private UserUseCase _userUseCase;
	private UserDao _userDao;
	
	@Before
	public void setUp() throws Exception {
		_userDao = mock(UserDao.class);
		_userUseCase = new UserUseCase(_userDao);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void isRegister_True_UsernamePasswordCorrect() {
		doReturn(true).when(_userDao).register(any());
		assertTrue(_userUseCase.register("tester", "secret"));
	}
	
	@Test
	public void isRegister_False_UsernameExisting() {
		String username = "tester";
		String password = "secret";
		doReturn(true).when(_userDao).register(any());
		assertTrue(_userUseCase.register(username, password));
		verify(_userDao, times(1)).register(any());
		doReturn(false).when(_userDao).register(any());
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
		String username = "tester";
		String password = "secret";
		_userUseCase.register(username, password);
		doReturn(true).when(_userDao).login(any());
		assertTrue(_userUseCase.login(username, password));
	}
	
	@Test
	public void isLogin_False_UsernameIncorrect() {
		String username = "tester";
		String password = "secret";
		_userUseCase.register(username, password);
		doReturn(false).when(_userDao).login(any());
		String aNewUsername = "newTester";
		assertFalse(_userUseCase.login(aNewUsername, password));
	}
	
	@Test
	public void isLogin_False_PasswordIncorrect() {
		String username = "tester";
		String password = "secret";
		_userUseCase.register(username, password);
		doReturn(false).when(_userDao).login(any());
		String aNewPassword = "newSecret";
		assertFalse(_userUseCase.login(username, aNewPassword));
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
