import Controller.AdminModifySalaServlet;
import Controller.DBConnector;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;


import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AdminModifySalaServletIntTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private RequestDispatcher requestDispatcher;

    @Before
    public void setUp() throws Exception {
        // Setup H2 in-memory database
        Connection conn = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        Statement stmt = conn.createStatement();
        stmt.execute("CREATE TABLE IF NOT EXISTS Sala (salaId INT AUTO_INCREMENT PRIMARY KEY, rowsCount INT, columnsCount INT, theaterName VARCHAR(255))");

        // Mock DBConnector to return real Connection to H2 database
        DBConnector dbConnector = new DBConnector() {
            public Connection getConn() throws SQLException {
                return conn;
            }
        };

        // Assign mocked request dispatcher
        //when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
    }

    @Test
    public void testDoPostModifySala() throws Exception {
        // Setup request parameters for modification
        when(request.getParameter("salaId")).thenReturn("1");  // Assuming there is a unique identifier for each sala
        when(request.getParameter("rowsCount")).thenReturn("12");  // New number of rows
        when(request.getParameter("columnsCount")).thenReturn("15");  // New number of columns
        when(request.getParameter("theaterName")).thenReturn("Updated Sala 3");  // New name for the sala

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
        verify(request).getParameter("rowsCount");
        verify(request).getParameter("columnsCount");
        verify(request).getParameter("theaterName");
    }
}
