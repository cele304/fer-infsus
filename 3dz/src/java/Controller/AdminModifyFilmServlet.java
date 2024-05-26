//servlet dobar jos jsp rijesiti

package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminModifyFilmServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Accept data from the form
        int filmId = Integer.parseInt(request.getParameter("filmId"));
        String filmName = request.getParameter("filmName");
        String synopsis = request.getParameter("synopsis");
        String filmPage = request.getParameter("filmPage");
        String originalTitle = request.getParameter("originalTitle");
        String genre = request.getParameter("genre");
        String nationality = request.getParameter("nationality");
        int duration = Integer.parseInt(request.getParameter("duration"));
        int releaseYear = Integer.parseInt(request.getParameter("releaseYear"));
        String distributor = request.getParameter("distributor");
        String director = request.getParameter("director");
        String actors = request.getParameter("actors");
        String classification = request.getParameter("classification");
        String otherData = request.getParameter("otherData");

        try (Connection connection = DBConnector.getConnection()) {
            // SQL query for updating movie
            String sql = "UPDATE Film SET film_name = ?, synopsis = ?, film_page = ?, original_title = ?, "
                    + "genre = ?, nationality = ?, duration = ?, release_year = ?, distributor = ?, director = ?, "
                    + "actors = ?, classification = ?, other_data = ? WHERE film_id = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, filmName);
                statement.setString(2, synopsis);
                statement.setString(3, filmPage);
                statement.setString(4, originalTitle);
                statement.setString(5, genre);
                statement.setString(6, nationality);
                statement.setInt(7, duration);
                statement.setInt(8, releaseYear);
                statement.setString(9, distributor);
                statement.setString(10, director);
                statement.setString(11, actors);
                statement.setString(12, classification);
                statement.setString(13, otherData);
                statement.setInt(14, filmId);

                // Execute the update query
                statement.executeUpdate();
            }

            // Redirect the user to the page with the list of films after the update
            response.sendRedirect("admin_gestor_peliculas.jsp");
            response.getWriter().write("Success or some keyword");
        } catch (SQLException e) {
            e.printStackTrace();
            // Handling error during movie update

            // Set an error message as an attribute for display on the same page
            request.setAttribute("error_message", "Se ha encontrado un error. Detalles: " + e);

            // Display the error message on the same page
            request.getRequestDispatcher("admin_gestor_peliculas.jsp").forward(request, response);
        }
    }
}
