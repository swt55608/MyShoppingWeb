package priv.liu.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		boolean isRegister = _userUseCase.register(username, password);
		username = (isRegister) ? username : null;
		String page = (isRegister) ? "index.jsp" : "register.jsp";
		session.setAttribute("username", username);
		session.setAttribute("errMsg", "Username already registered. Please use other username!");
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
