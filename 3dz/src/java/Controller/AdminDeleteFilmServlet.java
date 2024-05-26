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

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AdminDeleteFilmServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Prihvati podatke iz obrasca
        int filmId = Integer.parseInt(request.getParameter("filmId"));

        try (Connection connection = DBConnector.getConnection()) {
            // SQL upit za brisanje sale
            String sql = "DELETE FROM FILM WHERE film_id = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, filmId);

                // IzvrÅ¡i upit za brisanje
                statement.executeUpdate();
            }

            // Preusmjeri korisnika na stranicu s popisom sala nakon brisanja
            response.sendRedirect("admin_gestor_peliculas.jsp");
            response.getWriter().write("Success or some keyword");
        } catch (SQLException e) {
            e.printStackTrace();
            // Obrada greÅ¡ke pri brisanju sale

            // Postavi poruku o greÅ¡ki kao atribut za prikaz na istoj stranici
            request.setAttribute("error_message", "Se ha encontrado un error. Detalles: " + e);

            // Prikazi poruku o greÅ¡ki na istoj stranici
            request.getRequestDispatcher("admin_gestor_peliculas.jsp").forward(request, response);
        }
    }
}
