package priv.liu.servlet;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import priv.liu.dao.UserDao;
import priv.liu.entity.User;
import priv.liu.factory.UserDaoFactory;
import priv.liu.usecase.UserUseCase;

public class UserServletIntegrationTest {

	private UserServlet _userServlet;
//	private UserUseCase _userUseCase;
	private UserDao _userDao;
	private List<User> _toDeleteUsers;
	
	private HttpServletRequest _request;
	private HttpServletResponse _response;
	private HttpSession _session;
	private Map<String, Object> _sessionAttributes;
	
	@Before
	public void setUp() throws Exception {
//		_userUseCase = mock(UserUseCase.class);
//		_userServlet = new UserServlet(_userUseCase);
		_userServlet = new UserServlet();
		_userDao = new UserDaoFactory().create();
		_toDeleteUsers = new ArrayList<User>();
		
		_request = mock(HttpServletRequest.class);
		_response = mock(HttpServletResponse.class);
		_session = mock(HttpSession.class);
		_sessionAttributes = new HashMap<String, Object>();
		
		Answer<Object> answerToSetSessionAttribute = new Answer<Object>() {
			@Override
			public Object answer(InvocationOnMock invocation) throws Throwable {
				String key = (String) invocation.getArguments()[0];
				Object value = invocation.getArguments()[1];
				_sessionAttributes.put(key, value);
				return null;
			}
		};
		doAnswer(answerToSetSessionAttribute).when(_session).setAttribute(anyString(), any());
		
		Answer<Object> answerToRemoveSessionAttribute = new Answer<Object>() {
			@Override
			public Object answer(InvocationOnMock invocation) throws Throwable {
				String key = (String) invocation.getArguments()[0];
				_sessionAttributes.remove(key);
				return null;
			}
		};
		doAnswer(answerToRemoveSessionAttribute).when(_session).removeAttribute(anyString());
	}

	@After
	public void tearDown() throws Exception {
		for(User user : _toDeleteUsers) {
			_userDao.delete(user);
		}
		_toDeleteUsers.clear();
	}
	
	private void setRequestParameters(String action, String username, String password) {
		doReturn(action).when(_request).getParameter("action");
		doReturn(username).when(_request).getParameter("username");
		doReturn(password).when(_request).getParameter("password");
	}
	
	private void registerAnUser(String username, String password) throws ServletException, IOException {
		setRequestParameters("register", username, password);
		doReturn(_session).when(_request).getSession();
//		doReturn(isMockSuccess).when(_userUseCase).register(anyString(), anyString());
		_userServlet.doPost(_request, _response);
	}
	
	@Test
	public void registerAnUser_Success_UserNotExisting() throws ServletException, IOException {
		String username = "tester", password = "secret";
		registerAnUser(username, password);
		_toDeleteUsers.add(new User(username, password));
		assertEquals(username, _sessionAttributes.get("username"));
	}

	@Test
	public void registerAnUser_Fail_UserExisting() throws ServletException, IOException {
		String username = "tester", password = "secret";
		registerAnUser(username, password);
		_toDeleteUsers.add(new User(username, password));
		assertEquals(username, _sessionAttributes.get("username"));
		registerAnUser(username, password);
		assertEquals(null, _sessionAttributes.get("username"));
	}
	
	@Test
	public void registerAnUser_Fail_UsernameNull() throws ServletException, IOException {
		registerAnUser(null, "secret");
		assertEquals(null, _sessionAttributes.get("username"));
	}
	
	@Test
	public void registerAnUser_Fail_UsernameEmpty() throws ServletException, IOException {
		registerAnUser("", "secret");
		assertEquals(null, _sessionAttributes.get("username"));
	}
	
	@Test
	public void registerAnUser_Fail_PasswordNull() throws ServletException, IOException {
		registerAnUser("tester", null);
		assertEquals(null, _sessionAttributes.get("username"));
	}
	
	@Test
	public void registerAnUser_Fail_PasswordEmpty() throws ServletException, IOException {
		registerAnUser("tester", "");
		assertEquals(null, _sessionAttributes.get("username"));
	}
	
	private void loginAnUser(String username, String password) throws ServletException, IOException {
		doReturn("login").when(_request).getParameter("action");
		doReturn(username).when(_request).getParameter("username");
		doReturn(password).when(_request).getParameter("password");
		doReturn(_session).when(_request).getSession();
//		doReturn(isMockSuccess).when(_userUseCase).login(anyString(), anyString());
		_userServlet.doPost(_request, _response);
	}
	
	@Test
	public void loginAnUser_Success_UsernamePasswordCorrect() throws ServletException, IOException {
		String username = "tester", password = "secret";
		registerAnUser(username, password);
		_toDeleteUsers.add(new User(username, password));
		loginAnUser(username, password);
		assertEquals(username, _sessionAttributes.get("username"));
	}
	
	@Test
	public void loginAnUser_Fail_UsernameInCorrect() throws ServletException, IOException {
		String username = "tester", password = "secret";
		registerAnUser(username, password);
		_toDeleteUsers.add(new User(username, password));
		loginAnUser("newTester", password);
		assertEquals(null, _sessionAttributes.get("username"));
	}
	
	@Test
	public void loginAnUser_Fail_PasswordInCorrect() throws ServletException, IOException {
		String username = "tester", password = "secret";
		registerAnUser(username, password);
		_toDeleteUsers.add(new User(username, password));
		loginAnUser(username, "newSecret");
		assertEquals(null, _sessionAttributes.get("username"));
	}
	
	@Test
	public void loginAnUser_Fail_UsernameNull() throws ServletException, IOException {
		loginAnUser(null, "secret");
		assertEquals(null, _sessionAttributes.get("username"));
	}
	
	@Test
	public void loginAnUser_Fail_UsernameEmpty() throws ServletException, IOException {
		loginAnUser("", "secret");
		assertEquals(null, _sessionAttributes.get("username"));
	}
	
	@Test
	public void loginAnUser_Fail_PasswordNull() throws ServletException, IOException {
		loginAnUser("tester", null);
		assertEquals(null, _sessionAttributes.get("username"));
	}
	
	@Test
	public void loginAnUser_Fail_PasswordEmpty() throws ServletException, IOException {
		loginAnUser("tester", "");
		assertEquals(null, _sessionAttributes.get("username"));
	}
	
	private void logout() throws ServletException, IOException {
		doReturn("logout").when(_request).getParameter("action");
		doReturn(_session).when(_request).getSession();
		_userServlet.doPost(_request, _response);
	}
	
	@Test
	public void logoutAnUser_Success_UserLogined() throws ServletException, IOException {
		String username = "tester", password = "secret";
		registerAnUser(username, password);
		_toDeleteUsers.add(new User(username, password));
		loginAnUser(username, password);
		assertEquals(username, _sessionAttributes.get("username"));
		logout();
		assertEquals(null, _sessionAttributes.get("username"));
	}

}
