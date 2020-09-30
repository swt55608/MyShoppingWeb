package priv.liu.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import priv.liu.entity.Cart;
import priv.liu.entity.Product;
import priv.liu.exception.ProductNotExistException;
import priv.liu.usecase.CustomerUseCase;

@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private CustomerUseCase _customerUseCase;
	
    public CustomerServlet() {
        super();
        _customerUseCase = new CustomerUseCase();
    }
    
    public CustomerServlet(CustomerUseCase customerUseCase) {
    	super();
    	_customerUseCase = customerUseCase;
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		switch(action) {
		case "addToCart":
			addProductToCart(request, response);
			break;
		case "removeFromCart":
			removeProductFromCart(request, response);
			break;
		default:
			// throw new NoSuchActionException()
		}
	}
	
	private void addProductToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cart cart = getCart(request);
		
		String pName = request.getParameter("pName");
		int pPrice = Integer.parseInt(request.getParameter("pPrice"));
		String img = request.getParameter("pImg");
		Product product = new Product(pName, pPrice, img);
		
		_customerUseCase.addToCart(product, cart);
		response.sendRedirect("index.jsp");
	}
	
	private Cart getCart(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			session.setAttribute("cart", cart);
		}
		return cart;
	}
	
	private void removeProductFromCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cart cart = getCart(request);
		
		String pName = request.getParameter("pName");
		int pPrice = Integer.parseInt(request.getParameter("pPrice"));
		Product product = new Product(pName, pPrice);
		
		try {
			_customerUseCase.removeFromCart(product, cart);
			response.sendRedirect("cart.jsp");
		} catch(ProductNotExistException e) {
			request.getSession().setAttribute("errMsg", e.getMessage());
		}
	}
}
