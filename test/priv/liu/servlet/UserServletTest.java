package priv.liu.servlet;

import static org.junit.Assert.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import priv.liu.dao.MySqlUserDao;
import priv.liu.usecase.UserUseCase;

import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserServletTest {

	private UserServlet _userServlet;
	private UserUseCase _userUseCase;
	private HttpServletRequest _request;
	private HttpServletResponse _response;
	private HttpSession _session;
	private Map<String, Object> _sessionAttributes;
	
	@Before
	public void setUp() throws Exception {
		_userUseCase = mock(UserUseCase.class);
		_userServlet = new UserServlet(_userUseCase);
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
	}
	
	private void setRequestParameters(String action, String username, String password) {
		doReturn(action).when(_request).getParameter("action");
		doReturn(username).when(_request).getParameter("username");
		doReturn(password).when(_request).getParameter("password");
	}
	
	private void registerAnUser(String username, String password, boolean isMockSuccess) throws ServletException, IOException {
		setRequestParameters("register", username, password);
		doReturn(_session).when(_request).getSession();
		doReturn(isMockSuccess).when(_userUseCase).register(anyString(), anyString());
		_userServlet.doPost(_request, _response);
	}
	
	@Test
	public void registerAnUser_Success_UserNotExisting() throws ServletException, IOException {
		registerAnUser("tester", "secret", true);
		assertEquals("tester", _sessionAttributes.get("username"));
	}

	@Test
	public void registerAnUser_Fail_UserExisting() throws ServletException, IOException {
		registerAnUser("tester", "secret", true);
		assertEquals("tester", _sessionAttributes.get("username"));
		registerAnUser("tester", "secret", false);
		assertEquals(null, _sessionAttributes.get("username"));
	}
	
	@Test
	public void registerAnUser_Fail_UsernameNull() throws ServletException, IOException {
		registerAnUser(null, "secret", false);
		assertEquals(null, _sessionAttributes.get("username"));
	}
	
	@Test
	public void registerAnUser_Fail_UsernameEmpty() throws ServletException, IOException {
		registerAnUser("", "secret", false);
		assertEquals(null, _sessionAttributes.get("username"));
	}
	
	@Test
	public void registerAnUser_Fail_PasswordNull() throws ServletException, IOException {
		registerAnUser("tester", null, false);
		assertEquals(null, _sessionAttributes.get("username"));
	}
	
	@Test
	public void registerAnUser_Fail_PasswordEmpty() throws ServletException, IOException {
		registerAnUser("tester", "", false);
		assertEquals(null, _sessionAttributes.get("username"));
	}
	
	private void loginAnUser(String username, String password, boolean isMockSuccess) throws ServletException, IOException {
		doReturn("login").when(_request).getParameter("action");
		doReturn(username).when(_request).getParameter("username");
		doReturn(password).when(_request).getParameter("password");
		doReturn(_session).when(_request).getSession();
		doReturn(isMockSuccess).when(_userUseCase).login(anyString(), anyString());
		_userServlet.doPost(_request, _response);
	}
	
	@Test
	public void loginAnUser_Success_UsernamePasswordCorrect() throws ServletException, IOException {
		registerAnUser("tester", "secret", true);
		loginAnUser("tester", "secret", true);
		assertEquals("tester", _sessionAttributes.get("username"));
	}
	
	@Test
	public void loginAnUser_Fail_UsernameInCorrect() throws ServletException, IOException {
		registerAnUser("tester", "secret", true);
		loginAnUser("newTester", "secret", false);
		assertEquals(null, _sessionAttributes.get("username"));
	}
	
	@Test
	public void loginAnUser_Fail_PasswordInCorrect() throws ServletException, IOException {
		registerAnUser("tester", "secret", true);
		loginAnUser("tester", "newSecret", false);
		assertEquals(null, _sessionAttributes.get("username"));
	}
	
	@Test
	public void loginAnUser_Fail_UsernameNull() throws ServletException, IOException {
		loginAnUser(null, "secret", false);
		assertEquals(null, _sessionAttributes.get("username"));
	}
	
	@Test
	public void loginAnUser_Fail_UsernameEmpty() throws ServletException, IOException {
		loginAnUser("", "secret", false);
		assertEquals(null, _sessionAttributes.get("username"));
	}
	
	@Test
	public void loginAnUser_Fail_PasswordNull() throws ServletException, IOException {
		loginAnUser("tester", null, false);
		assertEquals(null, _sessionAttributes.get("username"));
	}
	
	@Test
	public void loginAnUser_Fail_PasswordEmpty() throws ServletException, IOException {
		loginAnUser("tester", "", false);
		assertEquals(null, _sessionAttributes.get("username"));
	}
	
	private void logout() throws ServletException, IOException {
		doReturn("logout").when(_request).getParameter("action");
		doReturn(_session).when(_request).getSession();
		_userServlet.doPost(_request, _response);
	}
	
	@Test
	public void logoutAnUser_Success_UserLogined() throws ServletException, IOException {
		loginAnUser("tester", "secret", true);
		assertEquals("tester", _sessionAttributes.get("username"));
		logout();
		assertEquals(null, _sessionAttributes.get("username"));
	}
}
