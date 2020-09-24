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
	private ServletContext _ctx;
	private Map<String, Object> _ctxAttributes;
	

	@Before
	public void setUp() throws Exception {
		_customerUseCase = mock(CustomerUseCase.class);
		_customerServlet = new CustomerServlet(_customerUseCase);
		_request = mock(HttpServletRequest.class);
		_response = mock(HttpServletResponse.class);
		
		_ctxAttributes = new HashMap<String, Object>();
		_ctx = mock(ServletContext.class);
		doReturn(_ctx).when(_request).getServletContext();
		doAnswer(new Answer<Object>() {
			@Override
			public Object answer(InvocationOnMock invocation) throws Throwable {
				String key = (String) invocation.getArguments()[0];
				Object value = invocation.getArguments()[1];
				_ctxAttributes.put(key, value);
				return null;
			}
		}).when(_ctx).setAttribute(anyString(), any());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void viewProductsInStock_Success() throws ServletException, IOException {
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
		ProductAsserter.assertProductsEqual(expectedProducts, (List<Product>)_ctxAttributes.get("products"));
	}
	
	// TODO: refactor those testFunct below
	@Test
	public void addAProductToCart_Success() throws ServletException, IOException {
		doReturn("addToCart").when(_request).getParameter("action");
		
		// setup cart from session
		Cart cart = new Cart();
		HttpSession session = mock(HttpSession.class);
		doReturn(session).when(_request).getSession();
		doReturn(cart).when(session).getAttribute("cart");
		
		// set request param {pName, pPrice}
		doReturn("apple").when(_request).getParameter("pName");
		doReturn("10").when(_request).getParameter("pPrice");
		
		// define use case addToCart
		doAnswer(new Answer<Object>() {
			@Override
			public Object answer(InvocationOnMock invocation) throws Throwable {
				Product invProduct = (Product) invocation.getArguments()[0];
				Cart invCart = (Cart) invocation.getArguments()[1];
				invCart.add(invProduct);
				return null;
			}
		}).when(_customerUseCase).addToCart(any(), any());
		
		_customerServlet.doGet(_request, _response);
		
		List<Product> expected = new ArrayList<Product>();
		expected.add(new Product("apple", 10));
		ProductAsserter.assertProductsEqual(expected, cart.getProducts());
	}

	@Test
	public void addDuplicativeProductsToCart_Fail() throws ServletException, IOException {
		doReturn("addToCart").when(_request).getParameter("action");
		
		// setup cart from session
		Cart cart = new Cart();
		HttpSession session = mock(HttpSession.class);
		doReturn(session).when(_request).getSession();
		doReturn(cart).when(session).getAttribute("cart");
		
		// set request param {pName, pPrice}
		doReturn("apple").when(_request).getParameter("pName");
		doReturn("10").when(_request).getParameter("pPrice");
		
		// define use case addToCart
		doAnswer(new Answer<Object>() {
			@Override
			public Object answer(InvocationOnMock invocation) throws Throwable {
				Product invProduct = (Product) invocation.getArguments()[0];
				Cart invCart = (Cart) invocation.getArguments()[1];
				invCart.add(invProduct);
				return null;
			}
		}).when(_customerUseCase).addToCart(any(), any());
		
		_customerServlet.doGet(_request, _response);
		_customerServlet.doGet(_request, _response);
		
		List<Product> expected = new ArrayList<Product>();
		expected.add(new Product("apple", 10));
		ProductAsserter.assertProductsEqual(expected, cart.getProducts());
	}
	
	@Test
	public void removeProductFromCart_Success() throws ProductNotExistException, ServletException, IOException {
		doReturn("addToCart").when(_request).getParameter("action");

		// setup cart from session
		Cart cart = new Cart();
		HttpSession session = mock(HttpSession.class);
		doReturn(session).when(_request).getSession();
		doReturn(cart).when(session).getAttribute("cart");
		
		// set request param {pName, pPrice}
		doReturn("apple").when(_request).getParameter("pName");
		doReturn("10").when(_request).getParameter("pPrice");
		
		// define use case addToCart
		doAnswer(new Answer<Object>() {
			@Override
			public Object answer(InvocationOnMock invocation) throws Throwable {
				Product invProduct = (Product) invocation.getArguments()[0];
				Cart invCart = (Cart) invocation.getArguments()[1];
				invCart.add(invProduct);
				return null;
			}
		}).when(_customerUseCase).addToCart(any(), any());
		
		_customerServlet.doGet(_request, _response);
		
		List<Product> expected = new ArrayList<Product>();
		expected.add(new Product("apple", 10));
		ProductAsserter.assertProductsEqual(expected, cart.getProducts());
		
		
		
		
		
		doReturn("removeFromCart").when(_request).getParameter("action");
		
		// define use case removeFromCart
		doAnswer(new Answer<Object>() {
			@Override
			public Object answer(InvocationOnMock invocation) throws Throwable {
				Product invoProduct = (Product) invocation.getArguments()[0];
				Cart invoCart = (Cart) invocation.getArguments()[1];
				invoCart.remove(invoProduct.getName());
				return null;
			}
		}).when(_customerUseCase).removeFromCart(any(), any());
		
		_customerServlet.doGet(_request, _response);
		
		expected.clear();
		assertTrue(cart.size() == 0);
		ProductAsserter.assertProductsEqual(expected, cart.getProducts());
	}
	
	@Test
	public void removeNonExistProductFromCart_Fail() throws ServletException, IOException, ProductNotExistException {
		doReturn("removeFromCart").when(_request).getParameter("action");
		
		// setup cart from session
		Cart cart = new Cart();
		HttpSession session = mock(HttpSession.class);
		doReturn(session).when(_request).getSession();
		doReturn(cart).when(session).getAttribute("cart");
		
		// set request param {pName, pPrice}
		doReturn("apple").when(_request).getParameter("pName");
		doReturn("10").when(_request).getParameter("pPrice");
		
		// define use case removeFromCart
		doAnswer(new Answer<Object>() {
			@Override
			public Object answer(InvocationOnMock invocation) throws Throwable {
				Product invoProduct = (Product) invocation.getArguments()[0];
				Cart invoCart = (Cart) invocation.getArguments()[1];
				invoCart.remove(invoProduct.getName());
				return null;
			}
		}).when(_customerUseCase).removeFromCart(any(), any());
		
		// define session setAttribute
		Map<String, Object> sessionAttributes = new HashMap<String, Object>();
		doAnswer(new Answer<Object>() {
			@Override
			public Object answer(InvocationOnMock invocation) throws Throwable {
				String key = (String) invocation.getArguments()[0];
				Object value = invocation.getArguments()[1];
				sessionAttributes.put(key, value);
				return null;
			}
		}).when(session).setAttribute(anyString(), any());
		
		_customerServlet.doGet(_request, _response);
		
		List<Product> expected = new ArrayList<Product>();
		assertTrue(cart.size() == 0);
		ProductAsserter.assertProductsEqual(expected, cart.getProducts());
		
		assertTrue(sessionAttributes.get("errMsg").equals("No Such Product Can Be Removed!!!"));
	}
}
