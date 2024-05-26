import Controller.AdminDeleteSalaServlet;
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
public class AdminDeleteSalaServletIntTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Before
    public void setUp() throws Exception {
        // Setup H2 in-memory database
        Connection conn = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        Statement stmt = conn.createStatement();
        stmt.execute("CREATE TABLE IF NOT EXISTS Sala (salaId INT AUTO_INCREMENT PRIMARY KEY)");

        // Insert test data into Sala table
        PreparedStatement ps = conn.prepareStatement("INSERT INTO Sala (salaId) VALUES (?)");
        ps.setInt(1, 15);  // Inserting sala with salaId 15
        ps.executeUpdate();
    }

    @Test
    public void testDoPostDeleteSala() throws Exception {
        when(request.getParameter("salaId")).thenReturn("15");

        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);

        // Execute doPost method for deleting a sala
        AdminDeleteSalaServlet servlet = new AdminDeleteSalaServlet();
        servlet.doPost(request, response);

        // Asserts for deleting a sala
        printWriter.flush();
        assertTrue(stringWriter.toString().contains("Success or some keyword")); // Checking if the response contains the expected output

        // Ensure all expected interactions with request are verified
        verify(request).getParameter("salaId");

        // Ensure all expected interactions with response are verified
        verify(response).getWriter();
    }
}
