package priv.liu.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
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
        // TODO Auto-generated constructor stub
    }
    
    public CustomerServlet(CustomerUseCase customerUseCase) {
    	super();
    	_customerUseCase = customerUseCase;
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		switch(action) {
		case "viewProducts":
			viewProductsInStock(request, response);
			break;
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
	
	private void viewProductsInStock(HttpServletRequest request, HttpServletResponse response) {
		List<Product> products = _customerUseCase.viewProductsInStock();
		ServletContext ctx = request.getServletContext();
		ctx.setAttribute("products", products);
	}
	
	private void addProductToCart(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		
		String pName = request.getParameter("pName");
		int pPrice = Integer.parseInt(request.getParameter("pPrice"));
		Product product = new Product(pName, pPrice);
		
		_customerUseCase.addToCart(product, cart);
	}
	
	private void removeProductFromCart(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		
		String pName = request.getParameter("pName");
		int pPrice = Integer.parseInt(request.getParameter("pPrice"));
		Product product = new Product(pName, pPrice);
		
		try {
			_customerUseCase.removeFromCart(product, cart);	
		} catch(ProductNotExistException e) {
			session.setAttribute("errMsg", e.getMessage());
		}
	}
}
