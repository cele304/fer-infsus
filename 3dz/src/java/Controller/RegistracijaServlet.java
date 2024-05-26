/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
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

@WebServlet("/registration")
public class RegistracijaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        
            // Serverska validacija
        if (!firstName.matches("[a-zA-Z]{2,}") || !lastName.matches("[a-zA-Z]{2,}")) {
            request.setAttribute("porukaRegistracije", "Ime i prezime moraju sadržavati samo slova i biti duži od jednog znaka.");
            request.getRequestDispatcher("registration.jsp").forward(request, response);
            return;
        }

        if (!username.matches("\\w{5,}")) {
            request.setAttribute("porukaRegistracije", "Korisničko ime mora biti duže od 4 znaka i može sadržavati samo slova, brojeve i donje crte.");
            request.getRequestDispatcher("registration.jsp").forward(request, response);
            return;
        }

        if (!password.matches("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}")) {
            request.setAttribute("porukaRegistracije", "Lozinka mora biti duža od 7 znakova i sadržavati barem jedno veliko slovo, jedno malo slovo i jedan broj.");
            request.getRequestDispatcher("registration.jsp").forward(request, response);
            return;
        }

        if (!email.matches("\\S+@\\S+\\.\\S+")) {
            request.setAttribute("porukaRegistracije", "Unesite valjanu e-mail adresu.");
            request.getRequestDispatcher("registration.jsp").forward(request, response);
            return;
        }

        try (Connection connection = DBConnector.getConnection()) {
            // Provjera postoji li već korisnik s istim korisničkim imenom
            if (provjeriDostupnostKorisnickogImena(username, connection)) {
                // Ako postoji, prikaži poruku o grešci
                request.setAttribute("porukaRegistracije", "Ese nombre de Usuario ya existe. Por favor, bueve a intentarlo");
                request.getRequestDispatcher("registration.jsp").forward(request, response);
                return;
            }

            // Ako korisničko ime nije zauzeto, dodaj novog korisnika u bazu podataka
            String sql = "INSERT INTO USERS (first_name, last_name, username, password, email) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, firstName);
                statement.setString(2, lastName);
                statement.setString(3, username);
                statement.setString(4, password);
                statement.setString(5, email);
                statement.executeUpdate();
            }

            // Postavi poruku o uspješnoj registraciji i preusmjeri korisnika na prijavu
            request.setAttribute("porukaRegistracije", "Registracija uspješna! Prijavite se.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            // Dodajte poruku o greški u slučaju problema s registracijom
            request.setAttribute("porukaRegistracije", "Greška pri registraciji. Detalji: " + e.getMessage());
            request.getRequestDispatcher("registration.jsp").forward(request, response);
        }
    }

    // Provjera dostupnosti korisničkog imena
    private boolean provjeriDostupnostKorisnickogImena(String username, Connection connection) throws SQLException {
        String sql = "SELECT * FROM USERS WHERE username = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); // Ako postoji rezultat, korisničko ime je zauzeto
            }
        }
    }
}
