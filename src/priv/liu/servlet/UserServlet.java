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

	public UserServlet() {
		super();
		_userRepository = new UserRepository();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		System.out.println("username: " + username + ", password: " + password);
		
		HttpSession session = request.getSession();
		if (username.isEmpty() || username == null || password.isEmpty() || password == null) {
			System.out.println("There're should NOT be empty in your username or password.");
		} else {
			User user = new User(username, password);
			boolean isSuccussful = false;
			switch (action) {
			case "register":
				isSuccussful = register(user);
				System.out.println(isSuccussful + " registration");			
				break;
			case "login":
				isSuccussful = login(user);
				System.out.println(isSuccussful + " login");
				break;
			default:
				System.out.println("Undefined Action.");
				break;
			}
			
			if (isSuccussful) {
				session.setAttribute("username", user.getUsername());
//				request.getRequestDispatcher("testview.jsp").forward(request, response);
			}
				
		}
	}
	
	private boolean register(User user) {
		return _userRepository.register(user);
	}
	
	private boolean login(User user) {
		return _userRepository.login(user);
	}

}
