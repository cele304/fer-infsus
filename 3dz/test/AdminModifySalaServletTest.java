import Controller.AdminModifySalaServlet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AdminModifySalaServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    //@Mock
    //private RequestDispatcher requestDispatcher;

    @Test
    public void testDoPostModifySala() throws Exception {
        // Setup request parameters for modification
        when(request.getParameter("salaId")).thenReturn("1");  // Assuming there is a unique identifier for each sala
        //when(request.getParameter("cinemaId")).thenReturn("1");  // Example of changing the cinema affiliation
        when(request.getParameter("rowsCount")).thenReturn("12");  // New number of rows
        when(request.getParameter("columnsCount")).thenReturn("15");  // New number of columns
        when(request.getParameter("theaterName")).thenReturn("Updated Sala 3");  // New name for the sala

        // Mocking the request dispatcher in case there is forwarding involved
        //when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);

        // Execute doPost method of the servlet
        AdminModifySalaServlet servlet = new AdminModifySalaServlet();
        servlet.doPost(request, response);

        // Possible redirection or output checks
        printWriter.flush();
        assertTrue(stringWriter.toString().contains("Success or some keyword")); // Check if output contains update success message

        // Verify the parameters were accessed as expected
        verify(request).getParameter("salaId");
        //verify(request).getParameter("cinemaId");
        verify(request).getParameter("rowsCount");
        verify(request).getParameter("columnsCount");
        verify(request).getParameter("theaterName");
    }
}
