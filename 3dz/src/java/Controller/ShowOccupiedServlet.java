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
public class ShowOccupiedServlet extends HttpServlet {

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
    
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        
        
        int id = Integer.parseInt(request.getParameter("id_sesion"));
        
        
        // Dohvati filmove po žanru iz baze
        int[] tamaño = getTamañoSala(id);
        request.setAttribute("t_sala",tamaño);
        
        List<int[]> ocupado = getOcupados(id);
        request.setAttribute("ocuppied",ocupado);
 
        Sesiones sesion=getInfoSesion(id);
        request.setAttribute("sasion",sesion);

        // Prikazi JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("user_reserveTicket.jsp");     
        dispatcher.forward(request, response);
       
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
    
    private int[] getTamañoSala(int id_sesion) {
        
        int[] tamaño=new int[2];
        String sql = "SELECT * FROM SALA WHERE SALA_ID = (SELECT SALA_ID FROM SESIONES WHERE SESION_ID = ?)";

        try (Connection connection = DBConnector.getConnection()) {

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,id_sesion);
            

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {                    
                    tamaño[0]=resultSet.getInt("columns_count");
                    tamaño[1]=resultSet.getInt("rows_count");   
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tamaño;
    }
    
    private List<int[]> getOcupados(int id_sesion) {
        List<int[]> ocupados =new ArrayList<>();
        
        String sql = "SELECT * FROM TICKET WHERE SESION_ID =?";

        try (Connection connection = DBConnector.getConnection()) {

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,id_sesion);
            

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {  
                    int[] ocu=new int[2];
                    ocu[0]=resultSet.getInt("column_number");
                    ocu[1]=resultSet.getInt("row_num");
                    ocupados.add(ocu);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ocupados;
    }
    private Sesiones getInfoSesion(int id) {
        Sesiones sesion = new Sesiones();
       
        String sql = "SELECT * FROM SESIONES WHERE SESION_ID= ?";

        try (Connection connection = DBConnector.getConnection()) {
            
           

            PreparedStatement statement = connection.prepareStatement(sql);
            
            PreparedStatement mandato;
            ResultSet response;
            
           
            
            statement.setInt(1,id);
            

            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                Sesiones ses=new Sesiones();
                ses.setId(resultSet.getInt("sesion_id"));
                ses.setCinema_id(resultSet.getInt("cinema_id"));
                ses.setFilm_id(resultSet.getInt("film_id"));
                ses.setSala_id(resultSet.getInt("sala_id"));
                ses.setDate(resultSet.getDate("fecha").toString());
                ses.setHour(resultSet.getTime("hora").toString());
                
                mandato=connection.prepareStatement("SELECT * FROM CINEMA WHERE CINEMA_ID="+ses.getCinema_id());
                response=mandato.executeQuery();
                response.next();
                ses.setNombreCine(response.getString("cinema_name"));
                response.close();
                mandato.close();
                
                mandato=connection.prepareStatement("SELECT * FROM FILM WHERE FILM_ID="+ses.getFilm_id());
                response=mandato.executeQuery();
                response.next();
                ses.setPelicula(response.getString("film_name"));
                response.close();
                mandato.close();

                mandato=connection.prepareStatement("SELECT * FROM SALA WHERE SALA_ID="+ses.getSala_id());
                response=mandato.executeQuery();
                response.next();
                ses.setNombreSala(response.getString("theater_name"));
                response.close();
                mandato.close();
                    
                sesion=ses;
                    
                
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sesion;
    }
    

}
