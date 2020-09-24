package priv.liu.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import priv.liu.dao.MySqlUserDao;
import priv.liu.entity.User;
import priv.liu.usecase.UserUseCase;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserUseCase _userUseCase;

	public UserServlet() {
		super();
		_userUseCase = new UserUseCase();
	}
	
	public UserServlet(UserUseCase userUseCase) {
		super();
		_userUseCase = userUseCase;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		switch(action) {
		case "register":
			register(request, response);
			break;
		case "login":
			login(request, response);
			break;
		case "logout":
			logout(request, response);
			break;
		default:
//			throw new NoSuchActionException();
		}
	}
	
	private void register(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String value = (_userUseCase.register(username, password)) ? username : null;
		session.setAttribute("username", value);
	}
	
	private void login(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String value = (_userUseCase.login(username, password)) ? username : null;
		session.setAttribute("username", value);
	}
	
	private void logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.removeAttribute("username");
		session.invalidate();
	}

}
