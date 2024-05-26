/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.Sesiones;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminModifyEntradaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Accept data from the form
        int ticketId = Integer.parseInt(request.getParameter("ticketId"));
        int rowNumber = Integer.parseInt(request.getParameter("rowNumber"));
        int columnNumber = Integer.parseInt(request.getParameter("columnNumber"));
        int sesion=Integer.parseInt(request.getParameter("sesion"));
        
        String errores="";
        
        boolean ocupado=false;
        
        int[] tamaño=getTamañoSala(sesion);
        
        if(rowNumber>tamaño[1]){
            
            errores+=" Greska u redu "+sesion+",";
            ocupado=true;
            
        }
        if(columnNumber>tamaño[0]){
            
            errores+=" Greska u stupcu "+sesion+",";
            ocupado=true;
        }
        
        List<int[]> ocupados=this.getOcupados(sesion);
        
       
        
        for(int[] i:ocupados){
            if(i[0]==columnNumber && i[1]==rowNumber){
                errores+=" El asiento escogido ta esta ocupado";
                ocupado=true;
            }
        }

        
        if(!ocupado){
            
        
        try (Connection connection = DBConnector.getConnection()) {
            // SQL query to update a ticket
            String sql = "UPDATE Ticket SET row_num = ?, column_number = ?, sesion_id=? WHERE ticket_id = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                
               
                statement.setInt(1, rowNumber);
                statement.setInt(2, columnNumber);
                statement.setInt(3, sesion);
                statement.setInt(4, ticketId);

                // Execute the update query
                statement.executeUpdate();
            }

            // Redirect the user to the page with the list of tickets after updating
            response.sendRedirect("admin_gestor_entradas.jsp");
        } catch (SQLException e) {
            e.printStackTrace();

            // Handling error while updating a ticket

            // Set the error message as an attribute to display on the same page
            request.setAttribute("error_message", "Error updating ticket. Details: " + e.getMessage());

            // Display the error message on the same page
            request.getRequestDispatcher("admin_gestor_entradas.jsp").forward(request, response);
        }
        }else{
            request.setAttribute("error_message", "Error updating ticket. " + errores);
            // Display the error message on the same page
            request.getRequestDispatcher("admin_gestor_entradas.jsp").forward(request, response);
        }
        
            
        
    }
    
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
    
}

