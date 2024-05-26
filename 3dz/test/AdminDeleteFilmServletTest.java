import Controller.AdminDeleteFilmServlet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import static org.junit.Assert.assertTrue;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AdminDeleteFilmServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Test
    public void testDoPostDeleteFilm() throws Exception {
        // Setup for deleting a film
        when(request.getParameter("filmId")).thenReturn("123");

        StringWriter stringWriterFilm = new StringWriter();
        PrintWriter writerFilm = new PrintWriter(stringWriterFilm);
        when(response.getWriter()).thenReturn(writerFilm);

        // Execute delete operation
        new AdminDeleteFilmServlet().doPost(request, response);

        // Assert that the film was deleted successfully
        writerFilm.flush();
        assertTrue(stringWriterFilm.toString().contains("Success or some keyword"));
        
        // Verify that the servlet correctly received the film ID parameter
        verify(request).getParameter("filmId");
    }

    @Test
    public void testDoPostDeleteFilmTwo() throws Exception {
        // Setup for deleting another film
        when(request.getParameter("filmId")).thenReturn("456");

        StringWriter stringWriterFilm = new StringWriter();
        PrintWriter writerFilm = new PrintWriter(stringWriterFilm);
        when(response.getWriter()).thenReturn(writerFilm);

        // Execute delete operation
        new AdminDeleteFilmServlet().doPost(request, response);

        // Assert that the film was deleted successfully
        writerFilm.flush();
        assertTrue(stringWriterFilm.toString().contains("Success or some keyword"));
        
        // Verify that the servlet correctly received the film ID parameter
        verify(request).getParameter("filmId");
    }
}
