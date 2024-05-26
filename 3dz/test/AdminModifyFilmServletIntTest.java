import Controller.AdminModifyFilmServlet;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AdminModifyFilmServletIntTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Before
    public void setUp() throws Exception {
        // Setup H2 in-memory database
        Connection conn = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        Statement stmt = conn.createStatement();
        stmt.execute("CREATE TABLE IF NOT EXISTS Film (filmId INT AUTO_INCREMENT PRIMARY KEY, filmName VARCHAR(255), synopsis VARCHAR(1000), filmPage VARCHAR(255), originalTitle VARCHAR(255), genre VARCHAR(255), nationality VARCHAR(255), duration INT, releaseYear INT, distributor VARCHAR(255), director VARCHAR(255), actors VARCHAR(1000), classification VARCHAR(10), otherData VARCHAR(1000))");
    }

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
    }
}
