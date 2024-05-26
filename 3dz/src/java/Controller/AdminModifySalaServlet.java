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


public class AdminModifySalaServlet extends HttpServlet {

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
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            int nFilas=Integer.parseInt(request.getParameter("rowsCount"));
            int nColumnas=Integer.parseInt(request.getParameter("columnsCount"));
            int nSalaId=Integer.parseInt(request.getParameter("salaId"));
            String nSala=request.getParameter("theaterName");
            
            String errores="";
            
            
            
            
            
            
            if(PocasColumnasFilas(nSalaId, nColumnas, nFilas)){
                errores="Greska";
                request.setAttribute("error_message", "Greska. " + errores);
                // Display the error message on the same page
                request.getRequestDispatcher("admin_gestor_salas.jsp").forward(request, response);
            }
            
            else{
                
                try (Connection connection = DBConnector.getConnection()) {
                    // SQL query to update a ticket
                    String sql = "UPDATE Sala SET rows_count = ?, columns_count = ?, theater_name=? WHERE sala_id = ?";
                    
                    try (PreparedStatement statement = connection.prepareStatement(sql)) {
                        
                        
                        statement.setInt(1, nFilas);
                        statement.setInt(2, nColumnas);
                        statement.setString(3, nSala);
                        statement.setInt(4, nSalaId);
                        
                        // Execute the update query
                        statement.executeUpdate();
                    }
                    
                    // Redirect the user to the page with the list of tickets after updating
                    response.sendRedirect("admin_gestor_salas.jsp");
                    response.getWriter().write("Success or some keyword");
                    
                }   catch (SQLException ex) {
                    request.setAttribute("error_message", "Error: " + ex.getMessage());

            // Display the error message on the same page
            request.getRequestDispatcher("admin_gestor_salas.jsp").forward(request, response);
                }
            } 
        } catch (SQLException ex) {
            request.setAttribute("error_message", "Error: " + ex.getMessage());

            // Display the error message on the same page
            request.getRequestDispatcher("admin_gestor_salas.jsp").forward(request, response);
        }
       
        
        }
 
        
        
    
    
    
    
    private boolean PocasColumnasFilas(int salaId, int column, int rows) throws SQLException{
        boolean pequeño=false;
        int ecolumns = 0;
        int erows=0;
        int i=0;
        try (Connection connection = DBConnector.getConnection()) {
            String sql = "SELECT * FROM Ticket join Sesiones on ticket.sesion_id=sesiones.sesion_id WHERE sesiones.sala_id=?";
            
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, salaId);
            
            
            try (ResultSet resultSet = statement.executeQuery()) {
                while(resultSet.next()){
                    
                    System.out.println("Entrada "+1);
                    ecolumns=resultSet.getInt("column_number");
                    erows=resultSet.getInt("row_num");
                    
                    if(ecolumns>column||erows>rows){
                        pequeño=true;
                        
                    }
                }
                    
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
        }
        return pequeño;
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
