package Controller;

import Model.Film;
import Controller.DBConnector;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/filmbytheater")
public class AdminFilmByTheaterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Dohvati žanr iz parametra
        String genre = request.getParameter("cinema");

        // Dohvati filmove po žanru iz baze
        List<Film> filmsByCinema = getFilmsByGenreFromDatabase(genre);

        // Postavi filmove u atribute za JSP
        request.setAttribute("filmbycinema", filmsByCinema);
        
        // Prikazi JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("filmbytheater.jsp");
        dispatcher.forward(request, response);
    }

    // Metoda za dohvaćanje filmova po žanru iz baze podataka
    private List<Film> getFilmsByGenreFromDatabase(String genre) {

       

    String sql = "SELECT * FROM Film JOIN Sesiones ON Film.Film_id = Sesiones.film_id where sesiones.cinema_id=(select cinema_id from cinema where cinema_name='"+genre+"')";

    // Lista za pohranu rezultata
    List<Film> listaDePeliculas = new ArrayList<>();

    try {
        // Učitavanje drivera i povezivanje s bazom podataka
        Connection connection = DBConnector.getConnection();
        // Izvršavanje SQL upita
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        // Iteriranje kroz rezultate i dodavanje filmova u listu
        while (resultSet.next()) {
            System.out.println("Result no vacio");
            Film film = new Film();
            film.setFilmName(resultSet.getString("film_name"));
            film.setSynopsis(resultSet.getString("synopsis"));
            film.setFilmPage(resultSet.getString("film_page"));
            film.setOriginalTitle(resultSet.getString("original_title"));
            film.setGenre(resultSet.getString("genre"));
            film.setNationality(resultSet.getString("nationality"));
            film.setDuration(resultSet.getInt("duration"));
            film.setReleaseYear(resultSet.getInt("release_year"));
            film.setDistributor(resultSet.getString("distributor"));
            film.setDirector(resultSet.getString("director"));
            film.setActors(resultSet.getString("actors"));
            film.setClassification(resultSet.getString("classification"));
            film.setOtherData(resultSet.getString("other_data"));

            listaDePeliculas.add(film);
        }


        // Zatvaranje resursa
        resultSet.close();
        statement.close();
        connection.close();

    } catch (Exception e) {
        e.printStackTrace();
    }

        return listaDePeliculas;
    }
}