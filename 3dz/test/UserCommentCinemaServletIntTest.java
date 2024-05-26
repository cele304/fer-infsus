import Controller.DBConnector;
import Controller.UserComentaCineServlet;
import javax.servlet.http.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.io.*;
import javax.servlet.RequestDispatcher;
import java.sql.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserCommentCinemaServletIntTest {

    @Mock HttpServletRequest request;
    @Mock HttpServletResponse response;
    @Mock RequestDispatcher dispatcher;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDoPostSuccess() throws Exception {
        // Setup H2 in-memory database
        Connection conn = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        Statement stmt = conn.createStatement();
        stmt.execute("CREATE TABLE IF NOT EXISTS Comments (id INT AUTO_INCREMENT PRIMARY KEY, peliname VARCHAR(255), comentario VARCHAR(255), username VARCHAR(255))");


        // Mock DBConnector to return real Connection to H2 database
        DBConnector dbConnector = new DBConnector() {
            public Connection getConn() throws SQLException {
                return conn;
            }
        };

        // Create UserComentaCineServlet instance
        UserComentaCineServlet userComentaCineServlet = new UserComentaCineServlet();

        // Set up request parameters
        when(request.getParameter("peliname")).thenReturn("TestMovie");
        when(request.getParameter("comentario")).thenReturn("Great Movie!");
        when(request.getParameter("user")).thenReturn("testUser");

        // Set up dispatcher
        when(request.getRequestDispatcher("user_viewFilm.jsp")).thenReturn(dispatcher);

        // Perform servlet doPost method
        userComentaCineServlet.doPost(request, response);

        // Verify interactions
        verify(request).getParameter("peliname");
        verify(request).getParameter("comentario");
        verify(request).getParameter("user");
        verify(request).getRequestDispatcher("user_viewFilm.jsp");
        verify(dispatcher).forward(request, response);

        // Clean up database resources
        stmt.close();
        conn.close();
    }
}
