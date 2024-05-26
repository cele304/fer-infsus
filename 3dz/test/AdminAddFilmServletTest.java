
import Controller.AdminAddFilmServlet;
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
public class AdminAddFilmServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

        @Test
    public void testDoPostAddFilm() throws Exception {
        // Setup for adding a film
        when(request.getParameter("filmName")).thenReturn("Test Film");
        when(request.getParameter("synopsis")).thenReturn("Test Synopsis");
        when(request.getParameter("filmPage")).thenReturn("http://testpage.com");
        when(request.getParameter("originalTitle")).thenReturn("Test Title");
        when(request.getParameter("genre")).thenReturn("Drama");
        when(request.getParameter("nationality")).thenReturn("USA");
        when(request.getParameter("duration")).thenReturn("120");
        when(request.getParameter("releaseYear")).thenReturn("2021");
        when(request.getParameter("distributor")).thenReturn("Test Distributor");
        when(request.getParameter("director")).thenReturn("Test Director");
        when(request.getParameter("actors")).thenReturn("Actor1, Actor2");
        when(request.getParameter("classification")).thenReturn("PG");
        when(request.getParameter("otherData")).thenReturn("Some data");
        
        // Execute the servlet method
        new AdminAddFilmServlet().doPost(request, response);

        // Verify the response redirected to the specified page
        verify(response).sendRedirect("admin_gestor_peliculas.jsp");

        /*
        StringWriter stringWriterFilm = new StringWriter();
        PrintWriter writerFilm = new PrintWriter(stringWriterFilm);
        when(response.getWriter()).thenReturn(writerFilm);

        // Execute for adding a film
        new AdminAddFilmServlet().doPost(request, response);

        // Assert for adding a film
        writerFilm.flush();
        assertTrue(stringWriterFilm.toString().contains("Success or some keyword"));
        */
    }
    
    // Test method for adding a film
    @Test
    public void testDoPostAddFilmTwo() throws Exception {
        // Setup for adding a film
        when(request.getParameter("filmName")).thenReturn("Test Film2");
        when(request.getParameter("synopsis")).thenReturn("Test Synopsis2");
        when(request.getParameter("filmPage")).thenReturn("http://testpage2.com");
        when(request.getParameter("originalTitle")).thenReturn("Test Title2");
        when(request.getParameter("genre")).thenReturn("Krimi");
        when(request.getParameter("nationality")).thenReturn("USA");
        when(request.getParameter("duration")).thenReturn("110");
        when(request.getParameter("releaseYear")).thenReturn("2022");
        when(request.getParameter("distributor")).thenReturn("Test Distributor2");
        when(request.getParameter("director")).thenReturn("Test Director2");
        when(request.getParameter("actors")).thenReturn("Actor1, Actor2");
        when(request.getParameter("classification")).thenReturn("PG");
        when(request.getParameter("otherData")).thenReturn("Some data");
        
        // Execute the servlet method
        new AdminAddFilmServlet().doPost(request, response);

        // Verify the response redirected to the specified page
        verify(response).sendRedirect("admin_gestor_peliculas.jsp");
        /*
        StringWriter stringWriterFilm = new StringWriter();
        PrintWriter writerFilm = new PrintWriter(stringWriterFilm);
        when(response.getWriter()).thenReturn(writerFilm);

        // Execute for adding a film
        new AdminAddFilmServlet().doPost(request, response);

        // Assert for adding a film
        writerFilm.flush();
        assertTrue(stringWriterFilm.toString().contains("Success or some keyword"));
        */
    }
}
