/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author filip
 */
public class AdimDeleteSesionServlet extends HttpServlet {

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
 
    

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int sesionId=Integer.parseInt(request.getParameter("sesionId"));
        
        String errores="";
        
        
        try {
            if(!existSesion(sesionId)){
                
                try (Connection connection = DBConnector.getConnection()) {
                    // SQL query to delete a ticket
                    String sql = "DELETE FROM SESIONES WHERE SESION_ID = ?";
                    
                    
                    
                    try (PreparedStatement statement1 = connection.prepareStatement(sql)) {
                        statement1.setInt(1, sesionId);
                        
                        // Execute the deletion query
                        statement1.executeUpdate();
                    }

                    
                    
                } catch (SQLException e) {
                    e.printStackTrace();
                    
                    // Handling error while deleting a ticket
                    
                    // Set the error message as an attribute to display on the same page
                    request.setAttribute("error_message", "Error deleting sesion. Details: " + e.getMessage());
                    
                    // Display the error message on the same page
                    request.getRequestDispatcher("admin_gestor_sesiones.jsp").forward(request, response);
                }
                
            }else{
                errores="Existen tickets que usan esta sesion, por favor, elimine primero esos tickets";
                request.setAttribute("error_message", "Error eliminando Sala. " + errores);
            // Display the error message on the same page
            request.getRequestDispatcher("admin_gestor_sesiones.jsp").forward(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminDeleteSalaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        response.sendRedirect("admin_gestor_sesiones.jsp");
    }
    
    private boolean existSesion(int sesionId) throws SQLException{

        try (Connection connection = DBConnector.getConnection()) {
            String sql = "SELECT * FROM ticket WHERE sesion_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, sesionId);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); // Ako postoji rezultat, korisnik je ispravan
            }
        }
    }
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
