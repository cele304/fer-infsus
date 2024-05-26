/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.Sesiones;
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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author filip
 */
public class ShowSesionsFilmServlet extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        
        String cinema = request.getParameter("cinemaName");
        String film =request.getParameter("filmName");
        System.out.println(cinema);
        // Dohvati filmove po žanru iz baze
        List<Sesiones> films = getSesionsByFilm(cinema,film);
        

        // Postavi filmove u atribute za JSP
        request.setAttribute("sesionbyfilm", films);

        // Prikazi JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("user_reserveTicket.jsp");     
        dispatcher.forward(request, response);
        
    }

    
    
    
    
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    private List<Sesiones> getSesionsByFilm(String cinema,String film) {
        List<Sesiones> sesion = new ArrayList<>();
       
        String sql = "SELECT * FROM SESIONES WHERE CINEMA_ID = ? AND FILM_ID=(SELECT FILM_ID FROM FILM WHERE FILM_NAME=? AND CINEMA_ID=?)";

        try (Connection connection = DBConnector.getConnection()) {
            
            PreparedStatement idCine=connection.prepareStatement("SELECT * FROM CINEMA WHERE CINEMA_NAME='"+cinema+"'");
            ResultSet res=idCine.executeQuery();
            res.next();
            int cine=res.getInt("CINEMA_ID");

            PreparedStatement statement = connection.prepareStatement(sql);
            
            PreparedStatement mandato;
            ResultSet response;
            
            statement.setInt(1,cine);
            statement.setString(2,film);
            statement.setInt(3,cine);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Sesiones ses=new Sesiones();
                    ses.setId(resultSet.getInt("sesion_id"));
                    ses.setCinema_id(resultSet.getInt("cinema_id"));
                    ses.setFilm_id(resultSet.getInt("film_id"));
                    ses.setSala_id(resultSet.getInt("sala_id"));
                    ses.setDate(resultSet.getDate("fecha").toString());
                    ses.setHour(resultSet.getTime("hora").toString());
                    ses.setNombreCine(cinema);
                    ses.setPelicula(film);
                    
                    mandato=connection.prepareStatement("SELECT * FROM SALA WHERE SALA_ID="+ses.getSala_id());
                    response=mandato.executeQuery();
                    response.next();
                    ses.setNombreSala(response.getString("theater_name"));
                    response.close();
                    mandato.close();
                    
                    sesion.add(ses);
                    
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sesion;
    }

}