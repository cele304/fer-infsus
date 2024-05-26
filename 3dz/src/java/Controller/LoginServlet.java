/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */


//FINISHED
package Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try (Connection connection = DBConnector.getConnection()) {
            if (provjeriKorisnika(username, password, connection)) {
                // Ako je korisnik admin, postavi ga kao admina u sesiju
                if (jeAdmin(username, connection)) {
                    HttpSession session = request.getSession();
                    session.setAttribute("admin", "y");
                    postaviPodatkeOKorisnikuUSesiju(username, connection, session);
                    response.sendRedirect("adminHome.jsp"); // Preusmjeri na admin početnu stranicu
                } else {
                    // Ako je korisnik obični, postavi ga kao običnog korisnika u sesiju
                    HttpSession session = request.getSession();
                    session.setAttribute("admin", "n");
                    postaviPodatkeOKorisnikuUSesiju(username, connection, session);
                    response.sendRedirect("userHome.jsp"); // Preusmjeri na korisnikovu početnu stranicu
                    
                }
            } else {
                // Ako korisnički podaci nisu ispravni, prikaži poruku o grešci
                request.setAttribute("porukaLogin", "Krivo korisnicko ime ili lozinka. Pokusajte ponovno.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Dodajte poruku o greški u slučaju problema s prijavom
            request.setAttribute("greskaLogin", "Greška pri prijavi. Detalji: " + e.getMessage());
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    private void postaviPodatkeOKorisnikuUSesiju(String username, Connection connection, HttpSession session) throws SQLException {
        String sql = "SELECT * FROM USERS WHERE username = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    session.setAttribute("loggedInUser", username);
                    session.setAttribute("userFirstName", resultSet.getString("first_name"));
                    session.setAttribute("userLastName", resultSet.getString("last_name"));
                    session.setAttribute("email", resultSet.getString("email"));
                    
                    // Dodajte ostale informacije o korisniku koje želite pratiti
                }
            }
        }
    }
    
    // Provjera korisnika po korisničkom imenu i lozinci
    private boolean provjeriKorisnika(String username, String password, Connection connection) throws SQLException {
        String sql = "SELECT * FROM USERS WHERE username = ? AND password = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); // Ako postoji rezultat, korisnik je ispravan
            }
        }
    }

    // Provjera je li korisnik admin
    private boolean jeAdmin(String username, Connection connection) throws SQLException {
        String sql = "SELECT * FROM USERS WHERE username = ? AND password = 'admin'";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); // Ako postoji rezultat, korisnik je admin
            }
        }
    }
}