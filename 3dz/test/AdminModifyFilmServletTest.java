import Controller.AdminModifyFilmServlet;
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
public class AdminModifyFilmServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Test
    public void testDoPostModifyFilm() throws Exception {
        // Setup for modifying a film
        when(request.getParameter("filmId")).thenReturn("123");  // assuming you need to specify which film to modify
        when(request.getParameter("filmName")).thenReturn("Updated Film Name");
        when(request.getParameter("synopsis")).thenReturn("Updated Synopsis");
        when(request.getParameter("filmPage")).thenReturn("http://updatedpage.com");
        when(request.getParameter("originalTitle")).thenReturn("Updated Title");
        when(request.getParameter("genre")).thenReturn("Comedy");  // Changed genre from Drama to Comedy
        when(request.getParameter("nationality")).thenReturn("Canada");  // Changed nationality
        when(request.getParameter("duration")).thenReturn("125");  // Updated duration
        when(request.getParameter("releaseYear")).thenReturn("2023");  // Updated release year
        when(request.getParameter("distributor")).thenReturn("Updated Distributor");
        when(request.getParameter("director")).thenReturn("Updated Director");
        when(request.getParameter("actors")).thenReturn("Actor3, Actor4");  // Changed actors
        when(request.getParameter("classification")).thenReturn("R");  // Updated classification
        when(request.getParameter("otherData")).thenReturn("Updated data");

        StringWriter stringWriterFilm = new StringWriter();
        PrintWriter writerFilm = new PrintWriter(stringWriterFilm);
        when(response.getWriter()).thenReturn(writerFilm);

        // Execute doPost method for modifying a film
        AdminModifyFilmServlet servlet = new AdminModifyFilmServlet();
        servlet.doPost(request, response);

        // Asserts for modifying a film
        writerFilm.flush();
        assertTrue(stringWriterFilm.toString().contains("Success or some keyword")); // Checking if the response contains the expected output

        // Verify that all parameters are fetched correctly
        verify(request).getParameter("filmId");
        verify(request).getParameter("filmName");
        verify(request).getParameter("synopsis");
        verify(request).getParameter("filmPage");
        verify(request).getParameter("originalTitle");
        verify(request).getParameter("genre");
        verify(request).getParameter("nationality");
        verify(request).getParameter("duration");
        verify(request).getParameter("releaseYear");
        verify(request).getParameter("distributor");
        verify(request).getParameter("director");
        verify(request).getParameter("actors");
        verify(request).getParameter("classification");
        verify(request).getParameter("otherData");
    }
}
