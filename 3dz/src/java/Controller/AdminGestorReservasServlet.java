/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */


package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/adminGestorReservas")
public class AdminGestorReservasServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<String> reservas = new ArrayList<>();

        try (Connection connection = DBConnector.getConnection()) {
            // Dohvati informacije o rezervacijama iz baze podataka
            reservas = getReservasFromDatabase(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            // Dodajte poruku o greÅ¡ki u sluÄaju problema s bazom podataka
            request.setAttribute("greskaBaza", "GreÅ¡ka pri dohvaÄ‡anju rezervacija iz baze podataka. Detalji: " + e.getMessage());
        }

        HttpSession session = request.getSession();
        session.setAttribute("reservas", reservas);

        request.getRequestDispatcher("admin_gestor_reservas.jsp").forward(request, response);
    }

    private List<String> getReservasFromDatabase(Connection connection) throws SQLException {
        List<String> reservas = new ArrayList<>();

        String sql = "SELECT r.reservation_id, u.username, t.ticket_id " +
                     "FROM Reservation r " +
                     "JOIN Users u ON r.user_id = u.user_id " +
                     "JOIN Ticket t ON r.ticket_id = t.ticket_id";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int reservationId = resultSet.getInt("reservation_id");
                String username = resultSet.getString("username");
                String ticketId = resultSet.getString("ticket_id");

                // Prilagodite formatiranje prema vaÅ¡im potrebama
                String reservaInfo = "ID: " + reservationId + ", User: " + username + ", Ticket: " + ticketId;

                reservas.add(reservaInfo);
            }
        }

        return reservas;
    }
}
