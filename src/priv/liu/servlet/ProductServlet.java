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

import priv.liu.entity.Product;
import priv.liu.repository.ProductRepository;

@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Product> products = getProducts();
		ServletContext ctx = getServletContext();
		ctx.setAttribute("products", products);
		
		request.getRequestDispatcher("testproductview.jsp").forward(request, response);
	}
	
	private List<Product> getProducts() {
		ProductRepository productRepository = new ProductRepository();
		return productRepository.getProducts();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
