package priv.liu.servlet;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import priv.liu.ProductAsserter;
import priv.liu.entity.Product;
import priv.liu.usecase.SupplierUseCase;

public class SupplierServletTest {
	
	private SupplierServlet _supplierServlet;
	private SupplierUseCase _supplierUseCase;
	private HttpServletRequest _request;
	private HttpServletResponse _response;

	@Before
	public void setUp() throws Exception {
		_supplierUseCase = mock(SupplierUseCase.class);
		_supplierServlet = new SupplierServlet(_supplierUseCase);
		_request = mock(HttpServletRequest.class);
		_response = mock(HttpServletResponse.class);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void showProducts_Success() throws ServletException, IOException {
		ServletContext ctx = mock(ServletContext.class);
		Map<String, Object> ctxAttributes = new HashMap<String, Object>();
		defineServletContextSetAttribute(ctx, ctxAttributes);
		
		Product apple = new Product("apple", 10);
		Product banana = new Product("banana", 20);
		
		List<Product> expectedProducts = new ArrayList<Product>();
		expectedProducts.add(apple);
		expectedProducts.add(banana);
		
		List<Product> actualProducts = new ArrayList<Product>();
		actualProducts.add(apple);
		actualProducts.add(banana);
		doReturn(actualProducts).when(_supplierUseCase).showProducts();
		
		_supplierServlet.doGet(_request, _response);
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

}
