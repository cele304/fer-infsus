/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.Film;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author filip
 */
@WebServlet("/filmsbycinema")
public class ShowFilmsCineServlets extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Dohvati žanr iz parametra
        String cinema = request.getParameter("cinemaName");
       
        // Dohvati filmove po žanru iz baze
        List<Film> films = getFilmsByGenreFromDatabase(cinema);
        

        // Postavi filmove u atribute za JSP
        request.setAttribute("filmbyciema", films);

        // Prikazi JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("user_reserveTicket.jsp");
        dispatcher.forward(request, response);
    }

    // Metoda za dohvaćanje filmova po žanru iz baze podataka
    private List<Film> getFilmsByGenreFromDatabase(String cinema) {
        List<Film> films = new ArrayList<>();
       
         String sql = "SELECT * FROM Film JOIN Sesiones ON Film.Film_id = Sesiones.film_id where sesiones.cinema_id=(select cinema_id from cinema where cinema_name='"+cinema+"')";

        try (Connection connection = DBConnector.getConnection()) {
            
            

            PreparedStatement statement = connection.prepareStatement(sql);
            

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Film film=new Film();
                    film.setFilId(resultSet.getInt("film_id"));
                    film.setFilmName(resultSet.getString("film_name"));
                    film.setGenre(resultSet.getString("genre"));
                    film.setNationality(resultSet.getString("nationality"));
                    film.setDuration(resultSet.getInt("duration"));
                    film.setReleaseYear(resultSet.getInt("release_year"));
                    film.setDirector(resultSet.getString("director"));
                    film.setActors(resultSet.getString("actors"));
                    film.setClassification(resultSet.getString("classification"));
                    film.setCinemaName(cinema);
                    films.add(film);
                    
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return films;
    }

}
