package priv.liu.servlet;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import priv.liu.ProductAsserter;
import priv.liu.entity.Cart;
import priv.liu.entity.Product;
import priv.liu.exception.ProductNotExistException;
import priv.liu.usecase.CustomerUseCase;

public class CustomerServletTest {
	
	private CustomerServlet _customerServlet;
	private CustomerUseCase _customerUseCase;
	private HttpServletRequest _request;
	private HttpServletResponse _response;
	private	Cart _cart;
	private HttpSession _session;
	

	@Before
	public void setUp() throws Exception {
		_customerUseCase = mock(CustomerUseCase.class);
		_customerServlet = new CustomerServlet(_customerUseCase);
		_request = mock(HttpServletRequest.class);
		_response = mock(HttpServletResponse.class);
		
		// setup cart from session
		_cart = new Cart();
		_session = mock(HttpSession.class);
		doReturn(_session).when(_request).getSession();
		doReturn(_cart).when(_session).getAttribute("cart");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void viewProductsInStock_Success() throws ServletException, IOException {
		ServletContext ctx = mock(ServletContext.class);
		Map<String, Object> ctxAttributes = new HashMap<String, Object>();
		defineServletContextSetAttribute(ctx, ctxAttributes);
		
		doReturn("viewProducts").when(_request).getParameter("action");
		
		Product apple = new Product("apple", 10);
		Product banana = new Product("banana", 20);
		
		List<Product> expectedProducts = new ArrayList<Product>();
		expectedProducts.add(apple);
		expectedProducts.add(banana);
		
		List<Product> actualProducts = new ArrayList<Product>();
		actualProducts.add(apple);
		actualProducts.add(banana);
		doReturn(actualProducts).when(_customerUseCase).viewProductsInStock();
		
		_customerServlet.doGet(_request, _response);
		ProductAsserter.assertProductsEqual(expectedProducts, (List<Product>) ctxAttributes.get("products"));
	}

	private void defineServletContextSetAttribute(ServletContext ctx, Map<String, Object> ctxAttributes) {
		doReturn(ctx).when(_request).getServletContext();
		doAnswer(new Answer<Object>() {
			@Override
			public Object answer(InvocationOnMock invocation) throws Throwable {
				String key = (String) invocation.getArguments()[0];
				Object value = invocation.getArguments()[1];
				ctxAttributes.put(key, value);
				return null;
			}
		}).when(ctx).setAttribute(anyString(), any());
	}
	
	// TODO: refactor those testFunct below 
	// (Should extract which methods to setUp()?)
	// (Should make viewProductsInStock() a member of SupplierServlet and SupplierUseCase? (which are not created))
	@Test
	public void addAProductToCart_Success() throws ServletException, IOException {
		// set request param {pName, pPrice}
		doReturn("apple").when(_request).getParameter("pName");
		doReturn("10").when(_request).getParameter("pPrice");
		
		doReturn("addToCart").when(_request).getParameter("action");
		defineUseCaseAddToCart();
		_customerServlet.doGet(_request, _response);
		
		List<Product> expected = new ArrayList<Product>();
		expected.add(new Product("apple", 10));
		ProductAsserter.assertProductsEqual(expected, _cart.getProducts());
	}

	@Test
	public void addDuplicativeProductsToCart_Fail() throws ServletException, IOException {
		// set request param {pName, pPrice}
		doReturn("apple").when(_request).getParameter("pName");
		doReturn("10").when(_request).getParameter("pPrice");
		
		doReturn("addToCart").when(_request).getParameter("action");
		defineUseCaseAddToCart();
		_customerServlet.doGet(_request, _response);
		_customerServlet.doGet(_request, _response);
		
		List<Product> expected = new ArrayList<Product>();
		expected.add(new Product("apple", 10));
		ProductAsserter.assertProductsEqual(expected, _cart.getProducts());
	}
	
	private void defineUseCaseAddToCart() {
		doAnswer(new Answer<Object>() {
			@Override
			public Object answer(InvocationOnMock invocation) throws Throwable {
				Product invProduct = (Product) invocation.getArguments()[0];
				Cart invCart = (Cart) invocation.getArguments()[1];
				invCart.add(invProduct);
				return null;
			}
		}).when(_customerUseCase).addToCart(any(), any());
	}
	
	@Test
	public void removeProductFromCart_Success() throws ProductNotExistException, ServletException, IOException {
		// set request param {pName, pPrice}
		doReturn("apple").when(_request).getParameter("pName");
		doReturn("10").when(_request).getParameter("pPrice");
		
		doReturn("addToCart").when(_request).getParameter("action");
		defineUseCaseAddToCart();
		_customerServlet.doGet(_request, _response);
		
		List<Product> expected = new ArrayList<Product>();
		expected.add(new Product("apple", 10));
		ProductAsserter.assertProductsEqual(expected, _cart.getProducts());
		
		doReturn("removeFromCart").when(_request).getParameter("action");
		
		defineUseCaseRemoveFromCart();
		_customerServlet.doGet(_request, _response);
		
		expected.clear();
		assertTrue(_cart.size() == 0);
		ProductAsserter.assertProductsEqual(expected, _cart.getProducts());
	}
	
	@Test
	public void removeNonExistProductFromCart_Fail() throws ServletException, IOException, ProductNotExistException {
		// set request param {pName, pPrice}
		doReturn("apple").when(_request).getParameter("pName");
		doReturn("10").when(_request).getParameter("pPrice");
		
		doReturn("removeFromCart").when(_request).getParameter("action");
		Map<String, Object> sessionAttributes = new HashMap<String, Object>();
		defineSessionSetAttribute(_session, sessionAttributes);
		
		defineUseCaseRemoveFromCart();
		_customerServlet.doGet(_request, _response);
		
		List<Product> expected = new ArrayList<Product>();
		assertTrue(_cart.size() == 0);
		ProductAsserter.assertProductsEqual(expected, _cart.getProducts());
		assertTrue(sessionAttributes.get("errMsg").equals("No Such Product Can Be Removed!!!"));
	}

	private void defineSessionSetAttribute(HttpSession session, Map<String, Object> sessionAttributes) {
		doAnswer(new Answer<Object>() {
			@Override
			public Object answer(InvocationOnMock invocation) throws Throwable {
				String key = (String) invocation.getArguments()[0];
				Object value = invocation.getArguments()[1];
				sessionAttributes.put(key, value);
				return null;
			}
		}).when(session).setAttribute(anyString(), any());
	}
	
	private void defineUseCaseRemoveFromCart() throws ProductNotExistException {
		doAnswer(new Answer<Object>() {
			@Override
			public Object answer(InvocationOnMock invocation) throws Throwable {
				Product invoProduct = (Product) invocation.getArguments()[0];
				Cart invoCart = (Cart) invocation.getArguments()[1];
				invoCart.remove(invoProduct.getName());
				return null;
			}
		}).when(_customerUseCase).removeFromCart(any(), any());
	}
}
