package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminDeleteEntradaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Accept data from the form
        int ticketId = Integer.parseInt(request.getParameter("ticketId"));
        
        

        try (Connection connection = DBConnector.getConnection()) {
            // SQL query to delete a ticket
            String sql = "DELETE FROM TICKET WHERE ticket_id = ?";
            
            String sql2 = "DELETE FROM RESERVATION WHERE ticket_id = ?";

            try (PreparedStatement statement1 = connection.prepareStatement(sql2)) {
                statement1.setInt(1, ticketId);

                // Execute the deletion query
                statement1.executeUpdate();
            }

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, ticketId);

                // Execute the deletion query
                statement.executeUpdate();
            }
           
            

            // Redirect the user to the page with the list of tickets after deletion
            response.sendRedirect("admin_gestor_entradas.jsp");
        } catch (SQLException e) {
            e.printStackTrace();

            // Handling error while deleting a ticket

            // Set the error message as an attribute to display on the same page
            request.setAttribute("error_message", "Error deleting ticket. Details: " + e.getMessage());

            // Display the error message on the same page
            request.getRequestDispatcher("admin_gestor_entradas.jsp").forward(request, response);
        }
    }
}
