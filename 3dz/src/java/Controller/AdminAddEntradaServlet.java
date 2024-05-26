package Controller;

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

public class AdminAddEntradaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        int user=Integer.parseInt(request.getParameter("users"));
        int sesion=Integer.parseInt(request.getParameter("sesion"));
        int rowNumber=Integer.parseInt(request.getParameter("rowNumber"));
        int columnNumber=Integer.parseInt(request.getParameter("columnNumber"));
        int ticketId=0;
        
        String errores="";
        
        int[] tamaño=getTamañoSala(sesion);
        
        boolean ocupado=false;
        
        if(rowNumber>tamaño[1]){
            
            errores+=" Fila escogida mayor al numero de filas de la sala de la sesion "+sesion+",";
            ocupado=true;
            
        }
        if(columnNumber>tamaño[0]){
            
            errores+=" Columna escogida mayor al numero de columnas de la sala de la sesion "+sesion+",";
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
            // SQL query to insert a new ticket
            String sql = "INSERT INTO Ticket (row_num, column_number, sesion_id) VALUES ( ?, ?, ?)";
            

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, rowNumber);
                statement.setInt(2, columnNumber);
                statement.setInt(3, sesion);
                

                // Execute the insertion query
                statement.executeUpdate();
                
                
            // SQL query to insert a new ticket
            String sql2 = "SELECT * FROM TICKET WHERE row_num=? AND column_number=? AND sesion_id=? ";
            

            try (PreparedStatement statement2 = connection.prepareStatement(sql2)) {
                statement2.setInt(1, rowNumber);
                statement2.setInt(2, columnNumber);
                statement2.setInt(3, sesion);
                

                // Execute the insertion query
                ResultSet resultSet=statement2.executeQuery();
                resultSet.next();
                ticketId=resultSet.getInt("ticket_id");
                
            
            }
            String sql3 = "INSERT INTO RESERVATION (user_id, ticket_id) VALUES ( ?, ?)";
            try (PreparedStatement statement3 = connection.prepareStatement(sql3)) {
                statement3.setInt(1, user);
                statement3.setInt(2, ticketId);
                // Execute the insertion query
                statement3.executeUpdate();

            }
            // Redirect the user to the page with the list of tickets after adding
            }    response.sendRedirect("admin_gestor_entradas.jsp");
        } catch (SQLException e) {
            e.printStackTrace();

            // Handling error while adding a ticket

            // Set the error message as an attribute to display on the same page
            request.setAttribute("error_message", "Error adding ticket. Details: " + e.getMessage());

            // Display the error message on the same page
            request.getRequestDispatcher("admin_gestor_entradas.jsp").forward(request, response);
        }
        }else{
            request.setAttribute("error_message", "Error adding ticket. Details: " + errores);

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
