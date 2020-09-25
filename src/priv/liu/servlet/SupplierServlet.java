package priv.liu.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import priv.liu.entity.Product;
import priv.liu.usecase.SupplierUseCase;

@WebServlet("/SupplierServlet")
public class SupplierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private SupplierUseCase _supplierUseCase;
       
    public SupplierServlet() {
        super();
        _supplierUseCase = new SupplierUseCase();
    }
    
    public SupplierServlet(SupplierUseCase supplierUseCase) {
    	super();
    	_supplierUseCase = supplierUseCase;
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		showProducts(request, response);
	}
	
	private void showProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Product> products = _supplierUseCase.showProducts();
		ServletContext ctx = request.getServletContext();
		ctx.setAttribute("products", products);
		response.sendRedirect("index.jsp");
	}

}
