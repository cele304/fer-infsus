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
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AdminAddSesionServlet extends HttpServlet {

    
    

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
    
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
   // Accept data from the form
        int cinemaId = Integer.parseInt(request.getParameter("cinemaId"));
        String date = request.getParameter("date");
        String time = request.getParameter("time");
        int salaId = Integer.parseInt(request.getParameter("salaId"));
       
        int filmId = Integer.parseInt(request.getParameter("filmId"));

        
        try (Connection connection = DBConnector.getConnection()) {
            // SQL query to insert a new ticket
            String sql = "INSERT INTO Sesiones (cinema_id, fecha, hora, sala_id, film_id) VALUES ( ?, ?, ?, ?, ?)";
           
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, 2);
                statement.setString(2, date);
                statement.setString(3, time);
                statement.setInt(4, salaId);
                statement.setInt(5, filmId);
                // Execute the insertion query
                statement.executeUpdate();   
            // SQL query to insert a new ticket
            // Redirect the user to the page with the list of tickets after adding
            }    
            response.sendRedirect("admin_gestor_sesiones.jsp");
            response.getWriter().write("Success or some keyword");
        } catch (SQLException e) {
            e.printStackTrace();

            // Handling error while adding a ticket

            // Set the error message as an attribute to display on the same page
            request.setAttribute("error_message", "Error adding ticket. Details: " + e.getMessage());

            // Display the error message on the same page
            request.getRequestDispatcher("admin_gestor_sesiones.jsp").forward(request, response);
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
