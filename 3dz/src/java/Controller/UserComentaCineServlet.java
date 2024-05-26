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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author filip
 */
public class UserComentaCineServlet extends HttpServlet {

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
        String pelicula=request.getParameter("peliname");
        String comentario=request.getParameter("comentario");
        String user=request.getParameter("user");
       
        String com="";
        
        try (Connection connection = DBConnector.getConnection()) {
            
            PreparedStatement idCine=connection.prepareStatement("SELECT * FROM FILM WHERE FILM_NAME='"+pelicula+"'");
            ResultSet res=idCine.executeQuery();
            res.next();
            int cine=res.getInt("FILM_ID");
            res.close();
            idCine.close();
            
            PreparedStatement idUser=connection.prepareStatement("SELECT * FROM USERS WHERE USERNAME='"+user+"'");
            ResultSet resUser=idUser.executeQuery();
            resUser.next();
            int usuario=resUser.getInt("USER_ID");
            resUser.close();
            idUser.close();
            
            String sql = "INSERT INTO APP.COMMENT (COMMENT_TEXT,  USER_ID, FILM_ID) VALUES (?, ?, ?)";
            
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, comentario);
                statement.setInt(2, usuario);
                statement.setInt(3, cine); 
                statement.executeUpdate();
            }  
            
            com="succes";
            response.getWriter().write("Success");
            
        } catch (SQLException e) {
            e.printStackTrace();  
        }
        
        request.setAttribute("com", com);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("user_viewFilm.jsp");
        
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

}
