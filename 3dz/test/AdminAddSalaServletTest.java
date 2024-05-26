import Controller.AdminAddFilmServlet;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AdminAddSalaServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;


    @Mock
    private PrintWriter printWriter;

    @Test
    public void testDoPostAddFilm() throws ServletException, IOException {
        // Set up request parameters
        when(request.getParameter("filmName")).thenReturn("Test Film");
        when(request.getParameter("synopsis")).thenReturn("Test Synopsis");
        when(request.getParameter("filmPage")).thenReturn("http://testpage.com");
        when(request.getParameter("originalTitle")).thenReturn("Test Original Title");
        when(request.getParameter("genre")).thenReturn("Drama");
        when(request.getParameter("nationality")).thenReturn("USA");
        when(request.getParameter("duration")).thenReturn("120");  // Obratite pažnju na tip podatka, treba konvertirati u int u servletu
        when(request.getParameter("releaseYear")).thenReturn("2021");  // Isto kao i za duration
        when(request.getParameter("distributor")).thenReturn("Test Distributor");
        when(request.getParameter("director")).thenReturn("Test Director");
        when(request.getParameter("actors")).thenReturn("Actor1, Actor2");
        when(request.getParameter("classification")).thenReturn("PG");
        when(request.getParameter("otherData")).thenReturn("Some other data");


        // Set up the PrintWriter to return when getWriter is called on response
        //when(response.getWriter()).thenReturn(printWriter);

        // Initialize the servlet and execute the doPost method
        new AdminAddFilmServlet().doPost(request, response);

        // Verify the response redirected to the expected page
        verify(response).sendRedirect("admin_gestor_peliculas.jsp");

        // Verify that getWriter was called (you can check interactions with printWriter if necessary)
        //verify(response).getWriter();
        // Ensure no text was written if not intended
        verify(printWriter, never()).write(anyString());
    }
}
