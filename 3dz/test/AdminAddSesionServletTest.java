import Controller.AdminAddSesionServlet;
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
public class AdminAddSesionServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    //@Mock
    //private RequestDispatcher requestDispatcher;

    @Test
    public void testDoPostAddSesion() throws Exception {
        // Setup request parameters
        when(request.getParameter("cinemaId")).thenReturn("1");
        when(request.getParameter("date")).thenReturn("2024-05-12");
        when(request.getParameter("time")).thenReturn("14:00:00");
        when(request.getParameter("salaId")).thenReturn("1");
        when(request.getParameter("filmId")).thenReturn("1");

        // Mocking the request dispatcher
        //when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);

        // Execute doPost method of the servlet
        AdminAddSesionServlet servlet = new AdminAddSesionServlet();
        servlet.doPost(request, response);

        // Check conditions to determine if redirect should happen
        // Let's say we expect redirect under certain conditions
        // verify(response).sendRedirect("admin_gestor_sesiones.jsp"); // Expected if certain conditions are met

        printWriter.flush();
        assertTrue(stringWriter.toString().contains("Success or some keyword")); // Check if output contains success message

        // Verify all interactions with mock objects
        verify(request).getParameter("cinemaId");
        verify(request).getParameter("date");
        verify(request).getParameter("time");
        verify(request).getParameter("salaId");
        verify(request).getParameter("filmId");
    }

}
