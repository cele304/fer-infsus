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


public class ComprarTicketServlet extends HttpServlet {

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
  
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("sesion"));
        String asientos=request.getParameter("asiento");
        String user=request.getParameter("user");
        
        String credit=request.getParameter("credit");
        String correcto="ok";
        
        if(credit.length()==12){
            
            int reserva=generaTicketRes(asientos, id, user);
            
            request.setAttribute("reseerva", reserva);
            
        }else{
           correcto="no";
        }
        
        request.setAttribute("correcto",correcto);
        

        // Prikazi JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("user_reserveTicket.jsp");     
        dispatcher.forward(request, response);
    }
    
    private int generaTicketRes(String asientos, int sesion, String user) {
               
        String sql = "INSERT INTO TICKET (SESION_ID, ROW_NUM, COLUMN_NUMBER) VALUES (?, ?, ?)";
        
        String sql2 = "INSERT INTO RESERVATION (USER_ID, TICKET_ID)VALUES (?, ?)";
        
        String[] asi=asientos.split("|");
        
        int reservation=0;
        int fila=Integer.parseInt(asi[2]);
        int columna=Integer.parseInt(asi[0]);
        
        
        
        
        
        System.out.println(fila+"|"+columna+"|"+asientos);

        try (Connection connection = DBConnector.getConnection()) {

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,sesion);
            statement.setInt(2,fila);
            statement.setInt(3,columna);
            
            statement.executeUpdate();

            
            PreparedStatement idUser=connection.prepareStatement("SELECT * FROM USERS WHERE USERNAME='"+user+"'");
            ResultSet res=idUser.executeQuery();
            res.next();
            int userid=res.getInt("user_id");
            System.out.println(userid);
            res.close();
            idUser.close();
            
            PreparedStatement idTicket=connection.prepareStatement("SELECT * FROM TICKET WHERE SESION_ID="+sesion+" AND ROW_NUM="+fila+" AND COLUMN_NUMBER="+columna);
            ResultSet res2=idTicket.executeQuery();
            res2.next();
            int ticket=res2.getInt("ticket_id");
            res2.close();
            idTicket.close();
            
            PreparedStatement statement2 = connection.prepareStatement(sql2);
            statement2.setInt(1,userid);
            statement2.setInt(2, ticket);
            statement2.executeUpdate();
            
            
            PreparedStatement idRes=connection.prepareStatement("SELECT * FROM RESERVATION WHERE USER_ID="+userid+" AND TICKET_ID="+ticket);
            ResultSet res3=idRes.executeQuery();
            res3.next();
            reservation=res3.getInt("reservation_id");
            res2.close();
            idTicket.close();
            
            
            

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservation;

        
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
