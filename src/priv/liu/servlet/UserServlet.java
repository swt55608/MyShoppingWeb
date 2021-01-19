package priv.liu.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import priv.liu.exception.EmptyInputException;
import priv.liu.exception.InvalidPasswordException;
import priv.liu.exception.UserExistException;
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
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
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
	
	private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String page = "register.jsp";
		String errMsg = null;
		try {
			_userUseCase.register(username, password);
			page = "index.jsp";
		} catch (EmptyInputException emptyInputEx) {
			username = null;
			errMsg = emptyInputEx.getMessage();
		} catch (UserExistException userExistEx) {
			username = null;
			errMsg = userExistEx.getMessage();
		} catch (InvalidPasswordException invalidPasswordEx) {
			username = null;
			errMsg = invalidPasswordEx.getMessage();
		}
		session.setAttribute("username", username);
		session.setAttribute("errMsg", errMsg);
		response.sendRedirect(page);
	}
	
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		boolean isLogin = _userUseCase.login(username, password);
		username = (isLogin) ? username : null;
		String page = (isLogin) ? "index.jsp" : "login.jsp";
		session.setAttribute("username", username);
		session.setAttribute("errMsg", "Invalid Username or Password");
		response.sendRedirect(page);
	}
	
	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("username");
		session.invalidate();
		response.sendRedirect("index.jsp");
	}

}
