import Controller.DBConnector;
import Controller.UserComentaCineServlet;
import static org.mockito.Mockito.*;
import javax.servlet.http.*;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.io.*;
import javax.servlet.RequestDispatcher;
import java.sql.*;

public class UserCommentFilmServletTest {

    @Mock HttpServletRequest request;
    @Mock HttpServletResponse response;
    @Mock RequestDispatcher dispatcher;
    @Mock Connection connection;
    //@Mock PreparedStatement preparedStatement;
    //@Mock ResultSet resultSet;
    @Mock DBConnector dbConnector;  // Mocked DBConnector

    @InjectMocks UserComentaCineServlet userComentaCineServlet;

    public UserCommentFilmServletTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDoPostSuccess() throws Exception {
        when(request.getParameter("peliname")).thenReturn("TestMovie");
        when(request.getParameter("comentario")).thenReturn("Great Movie!");
        when(request.getParameter("user")).thenReturn("testUser");

        when(request.getRequestDispatcher("user_viewFilm.jsp")).thenReturn(dispatcher);
        //when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        //when(preparedStatement.executeQuery()).thenReturn(resultSet);
        //when(resultSet.next()).thenReturn(true);
        //when(resultSet.getInt(anyString())).thenReturn(1);

        userComentaCineServlet.doPost(request, response);

        verify(request).getParameter("peliname");  // Verify interaction with the mock
        //verify(preparedStatement).executeQuery(); // Check if executeQuery was called
        //verify(preparedStatement).close();        // Verify close was called on preparedStatement
        //verify(resultSet).close();                // Verify close was called on resultSet
        verify(dispatcher).forward(request, response);  // Check if request forwarding happens
    }
}
