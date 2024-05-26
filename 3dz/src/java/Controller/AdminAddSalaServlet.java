/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */


/**
 *
 * @author filip
 */


package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AdminAddSalaServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Prihvati podatke iz obrasca
            String cinemaId =request.getParameter("cinemaName");
            
            int rowsCount = Integer.parseInt(request.getParameter("rowsCount"));
            int columnsCount = Integer.parseInt(request.getParameter("columnsCount"));
            String theaterName = request.getParameter("theaterName");
            
            String errores="";
            
            if(existNombre(cinemaId, theaterName)){
                
                errores="Vec postoji takva sala";
                request.setAttribute("error_message", "Error modificando Sala. " + errores);
                // Display the error message on the same page
                request.getRequestDispatcher("admin_gestor_salas.jsp").forward(request, response);
                
                
            }
            
            try (Connection connection = DBConnector.getConnection()) {
                // SQL upit za umetanje nove sale
                String sql = "INSERT INTO SALA (cinema_id, rows_count, columns_count, theater_name) VALUES (?, ?, ?, ?)";
                
                PreparedStatement idCine=connection.prepareStatement("SELECT * FROM CINEMA WHERE CINEMA_NAME='"+cinemaId+"'");
                ResultSet res=idCine.executeQuery();
                res.next();
                int cine=res.getInt("CINEMA_ID");
                
                PreparedStatement cont=connection.prepareStatement("SELECT COUNT(*) as NUMEROS FROM SALA");
                ResultSet resC=cont.executeQuery();
                resC.next();
                int idSala=1+resC.getInt("NUMEROS");
                
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setInt(1,cine );
                    statement.setInt(2, rowsCount);
                    statement.setInt(3, columnsCount);
                    statement.setString(4, theaterName);
                    // statement.setInt(5, idSala);
                    
                    // Izvrši upit za umetanje
                    statement.executeUpdate();
                }
                /*res.close();
                idCine.close();*/
                
                // Preusmjeri korisnika na stranicu s popisom sala nakon dodavanja
                response.sendRedirect("admin_gestor_salas.jsp");
                response.getWriter().write("Success or some keyword");

            } catch (SQLException e) {
                e.printStackTrace();
                // Obrada greške pri dodavanju sale
                
                // Postavi poruku o greški kao atribut za prikaz na istoj stranici
                request.setAttribute("error_message", "Greska: " + e +" "+cinemaId);
                
                // Prikazi poruku o greški na istoj stranici
                request.getRequestDispatcher("admin_gestor_salas.jsp").forward(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminAddSalaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private boolean existNombre(String nombreCine, String sala) throws SQLException{

        try (Connection connection = DBConnector.getConnection()) {
            
            String sql = "SELECT * FROM sala WHERE cinema_id = (Select cinema_id from cinema where cinema_name=?) and theater_name=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nombreCine);
            statement.setString(2, sala);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); // Ako postoji rezultat, korisnik je ispravan
            }
        }
        }
    }
    
    private boolean existSesion(int salaId) throws SQLException{

        try (Connection connection = DBConnector.getConnection()) {
            String sql = "SELECT * FROM Sesiones WHERE sala_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, salaId);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); // Ako postoji rezultat, korisnik je ispravan
            }
        }
        }
    }
    
}

