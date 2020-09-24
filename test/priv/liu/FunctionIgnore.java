package priv.liu;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class FunctionIgnore {
	public static void ignoreRequestDispatcherForward(HttpServletRequest request) throws ServletException, IOException {
		RequestDispatcher rd = mock(RequestDispatcher.class);
		doReturn(rd).when(request).getRequestDispatcher(anyString());
		doNothing().when(rd).forward(any(), any());
	}
}
