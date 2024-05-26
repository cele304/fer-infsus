package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminAddFilmServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Prihvati podatke iz obrasca
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
            // SQL upit za umetanje novog filma
            String sql = "INSERT INTO Film (film_name, synopsis, film_page, original_title, genre, nationality, duration, release_year, distributor, director, actors, classification, other_data) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            
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

                // IzvrÅ¡i upit za umetanje
                statement.executeUpdate();
            }

            // Preusmjeri korisnika na stranicu s popisom filmova nakon dodavanja
            response.sendRedirect("admin_gestor_peliculas.jsp");
            //response.getWriter().write("Success or some keyword");
        } catch (SQLException e) {
            e.printStackTrace();
            // Obrada greÅ¡ke pri dodavanju filma

            // Postavi poruku o greÅ¡ki kao atribut za prikaz na istoj stranici
            request.setAttribute("error_message", "GreÅ¡ka pri dodavanju filma. Detalji: " + e.getMessage());

            // Prikazi poruku o greÅ¡ki na istoj stranici
            request.getRequestDispatcher("admin_gestor_peliculas.jsp").forward(request, response);
        }
    }
}
