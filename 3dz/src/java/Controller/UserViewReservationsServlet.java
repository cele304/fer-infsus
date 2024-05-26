/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;


import Model.Film;
import Model.Reservation;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;



@WebServlet("/user_viewReservations")
public class UserViewReservationsServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Retrieve username from the session
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        // Check if username is not available in the session, you may want to handle this case
        if (username == null || username.isEmpty()) {
            // Redirect to login page or handle the situation as appropriate
            response.sendRedirect("login.jsp");
            return;
        }

        // Retrieve reservations from the database based on the username
        List<Reservation> reservations = getReservationsByUsername(username);

        // Set reservations as an attribute for JSP
        request.setAttribute("reservations", reservations);

        // Forward to the JSP page for displaying reservations
        RequestDispatcher dispatcher = request.getRequestDispatcher("user_viewReservations.jsp");
        dispatcher.forward(request, response);
    }

    private List<Reservation> getReservationsByUsername(String username) {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT * FROM Reservation "
                   + "JOIN Users ON Reservation.user_id = Users.user_id "
                   + "JOIN Ticket ON Reservation.ticket_id = Ticket.ticket_id "
                   + "JOIN Film ON Ticket.film_id = Film.film_id "
                   + "WHERE Users.username = ?";

        try (Connection connection = DBConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Reservation reservation = new Reservation();
                    // Set properties of the reservation object from the result set
                    // For example:
                    reservation.setReservationId(resultSet.getInt("reservation_id"));

                    // Fetch film information from the result set
                    Film film = new Film();
                    film.setFilmName(resultSet.getString("film_name"));
                    // Set other relevant properties of the Film class

                    // Set the Film object in the Reservation class
                    reservation.setFilm(film);

                    reservations.add(reservation);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception or handle it appropriately
        }

        return reservations;
    }
}