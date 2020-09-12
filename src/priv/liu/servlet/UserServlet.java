package priv.liu.servlet;

import java.io.IOException;
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

	public UserServlet() {
		super();
		_userRepository = new UserRepository();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		User user = new User(username, password);
		switch (action) {
		case "register":
			String isRegisterSuccussful = register(user) ? "successful" : "Failed";
			System.out.println(isRegisterSuccussful + " registration");			
			break;
		case "login":
			String isLoginSuccussful = login(user) ? "Successful" : "Failed";
			System.out.println(isLoginSuccussful + " login");
			break;
			
		default:
			
			break;
		}
	}
	
	private boolean register(User user) {
		return _userRepository.register(user);
	}
	
	private boolean login(User user) {
		return _userRepository.login(user);
	}

}
