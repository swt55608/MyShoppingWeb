package priv.liu.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import priv.liu.entity.User;
import priv.liu.repository.UserRepository;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserRepository _userRepository;
	private boolean _isUserLogin;

	public UserServlet() {
		super();
		_userRepository = new UserRepository();
		_isUserLogin = false;
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		logout(session);
		System.out.println(!_isUserLogin + " logout");
		
		request.getRequestDispatcher("testview.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		System.out.println("username: " + username + ", password: " + password);
		
		HttpSession session = request.getSession();
		if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
			System.out.println("There're should NOT be empty in your username or password.");
		} else {
			User user = new User(username, password);
			switch (action) {
			case "register":
				register(user, session);
				System.out.println(_isUserLogin + " registration");			
				break;
			case "login":
				login(user, session);
				System.out.println(_isUserLogin + " login");
				break;
			default:
				System.out.println("Undefined Action.");
				break;
			}
		}
		request.getRequestDispatcher("testview.jsp").forward(request, response);
	}
	
	private boolean register(User user, HttpSession session) {
		if (_userRepository.register(user)) {
			session.setAttribute("username", user.getUsername());
			_isUserLogin = true;
		}
		return _isUserLogin;
	}
	
	private boolean login(User user, HttpSession session) {
		if (_userRepository.login(user)) {
			session.setAttribute("username", user.getUsername());
			_isUserLogin = true;
		}
		return _isUserLogin;
	}
	
	private boolean logout(HttpSession session) {
		if (_isUserLogin) {
			session.removeAttribute("username");
			session.invalidate();
			_isUserLogin = false;
		}
		return !_isUserLogin;
	}

}
