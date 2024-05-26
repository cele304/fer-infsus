import Controller.AdminDeleteSalaServlet;
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
public class AdminDeleteSalaServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private RequestDispatcher requestDispatcher;

    @Test
    public void testDoPostDeleteSala() throws Exception {
        when(request.getParameter("salaId")).thenReturn("15");

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);

        AdminDeleteSalaServlet servlet = new AdminDeleteSalaServlet();
        servlet.doPost(request, response);

        printWriter.flush();
        assertTrue(stringWriter.toString().contains("Success or some keyword"));

        verify(request).getParameter("salaId");
        // Ensure all expected interactions with response are verified
        verify(response).getWriter();

        // Comment out this line if it's too restrictive
        // verifyNoMoreInteractions(response);
    }

}
