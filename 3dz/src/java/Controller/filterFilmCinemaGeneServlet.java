/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
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
/**
 *
 * @author filip
 */
public class filterFilmCinemaGeneServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       // Dohvati žanr iz parametra
        String genre = request.getParameter("generos");
        String cinema= request.getParameter("cinema");
        String error="";
        System.out.println(genre);
        // Dohvati filmove po žanru iz baze
        List<Film> filmsByCinema = getFilmsByGenreCinemaFromDatabase(cinema,genre);
        if(filmsByCinema.isEmpty()){
            error=" Nema takvih filmova. Pokusaj ponovno.";
        }

        // Postavi filmove u atribute za JSP
        request.setAttribute("filmbycinemagenre", filmsByCinema);
        request.setAttribute("error", error);
        
        
        // Prikazi JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("user_viewFilm.jsp");
        dispatcher.forward(request, response);
    }
    
    private List<Film> getFilmsByGenreCinemaFromDatabase(String cinema,String genre) {
        
     
        String sql="SELECT * FROM Film";
        
        boolean filtcine=false;
        if(cinema!=null){
            filtcine=!cinema.equals("");
        }

        if(filtcine){
            sql+= " JOIN Sesiones ON Film.Film_id = Sesiones.film_id where sesiones.cinema_id=(select cinema_id from cinema where cinema_name='"+cinema+"')";
        }
        
        if(genre!=null){
        if(!genre.equals("")){
            if(filtcine) sql+=" and";
             sql+=   "  WHERE Film.Genre='"+genre+"'";
            
        }
        }
        
        System.out.println(sql);
        

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

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
