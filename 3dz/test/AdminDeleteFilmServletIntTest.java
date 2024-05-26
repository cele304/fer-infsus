import Controller.AdminDeleteFilmServlet;
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
import java.sql.PreparedStatement;
import java.sql.Statement;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AdminDeleteFilmServletIntTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Before
    public void setUp() throws Exception {
        // Setup H2 in-memory database
        Connection conn = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        Statement stmt = conn.createStatement();
        stmt.execute("CREATE TABLE IF NOT EXISTS Film (filmId INT AUTO_INCREMENT PRIMARY KEY, filmName VARCHAR(255))");

        // Insert test data into Film table
        PreparedStatement ps = conn.prepareStatement("INSERT INTO Film (filmId, filmName) VALUES (?, ?)");
        ps.setInt(1, 123);  // Inserting film with filmId 123
        ps.setString(2, "Test Film 123");
        ps.executeUpdate();
        
        ps.setInt(1, 456);  // Inserting film with filmId 456
        ps.setString(2, "Test Film 456");
        ps.executeUpdate();
    }

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

        // Additional verification for database state can be added here
    }

    
}
