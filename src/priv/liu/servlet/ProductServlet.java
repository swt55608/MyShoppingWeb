package priv.liu.servlet;

import java.io.IOException;
import java.util.ArrayList;
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
import priv.liu.repository.ProductRepository;

@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		
		Cart cart;
		switch (action) {
		case "getProducts":
			List<Product> products = getProducts();
			ServletContext ctx = getServletContext();
			ctx.setAttribute("products", products);
			request.getRequestDispatcher("testproductview.jsp").forward(request, response);
			break;
			
			
			
			
			
		// TODO: get the cart, add/delete the product into the cart
		case "getCart":
			cart = getCart(session);
			session.setAttribute("productsInCart", cart.getProducts());
			request.getRequestDispatcher("testcartview.jsp").forward(request, response);
			break;
		case "addToCart":
			String img = request.getParameter("img");
			String name = request.getParameter("name");
			Product product = new Product(img, name);
			cart = getCart(session);
			cart.add(product);
			session.setAttribute("cart", cart);
			session.setAttribute("productQuantities", cart.getProductQuantities());
			request.getRequestDispatcher("testproductview.jsp").forward(request, response);
			break;
			
			
			
		default:
			System.out.println("Undefined Action.");
			break;
		}
		
		
	}
	
	private Cart getCart(HttpSession session) {
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart == null)
			cart = new Cart();
		return cart;
	}
	
	private List<Product> getProducts() {
		ProductRepository productRepository = new ProductRepository();
		return productRepository.getProducts();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
