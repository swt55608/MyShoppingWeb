package priv.liu.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import priv.liu.entity.User;
import priv.liu.repository.UserRepository;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = new User(username, password);
		String isRegisterSuccussful = register(user) ? "successful" : "failed";
		System.out.println(isRegisterSuccussful + " registration");
	}
	
	private boolean register(User user) {
		UserRepository userRepository = new UserRepository();
		return userRepository.register(user);
	}

}
