


<%@page import="Model.Cinema"%>
<%@page import="Model.DataBase"%>


<%@ page import="java.util.*" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="Model.Film" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Upravljanje filmovima</title>
    <link rel="stylesheet" href="style3.css">
</head>
<body>
    <h2>Upravljanje filmovima</h2>

    <div class="form-container">
    <!-- Dodaj film form -->
    <form class="ticket-form" action="AdminAddFilmServlet" method="post">
        <%
            // Postavljanje podataka za povezivanje s bazom podataka
           
            
            String admin=(String) session.getAttribute("admin");
            if(admin!=null){
            
            
            if(admin.equals("y")){
            
            
            DataBase dt=new DataBase();

            // SQL upit za dohvat svih filmova
            

            // Lista za pohranu rezultata
            List<Cinema> listaDeCines = dt.getTodosCines();

            
        %>
        
        <div class="form-group">
        <label for="cines">Označi kino</label>
        <select name="cinemaName" id="cines">
            <% for (Cinema cine : listaDeCines) { %>
                <option value="<%=cine.getCinemaName()%>"><%=cine.getCinemaName()%></option>
            <% } %>
        </select>
        <br>
        </div>
        
        
        <div class="form-group">
        <label for="filmName">Ime filma:</label>
        <input type="text" name="filmName" required><br>
        </div>
        
        
        <div class="form-group">
        <label for="synopsis">Opis:</label>
        <textarea name="synopsis" rows="4" cols="50" required></textarea><br>
        </div>
        
        
        <div class="form-group">
        <label for="filmPage">Službena stranica:</label>
        <input type="text" name="filmPage" required><br>
        </div>

        
        <div class="form-group">
        <label for="originalTitle">Originalni naslov:</label>
        <input type="text" name="originalTitle"><br>
        </div>
        
        
        <div class="form-group">
        <label for="genre">Žanr:</label>
        <input type="text" name="genre" required><br>
        </div>
        
        
        <div class="form-group">
        <label for="nationality">Nacionalnost:</label>
        <input type="text" name="nationality"><br>
        </div>
        
        
        <div class="form-group">
        <label for="duration">Trajanje (min):</label>
        <input type="number" name="duration" required><br>
        </div>

        
        <div class="form-group">
        <label for="releaseYear">Godina:</label>
        <input type="number" name="releaseYear"><br>
        </div>
        
        <div class="form-group">
        <label for="distributor">Distributer:</label>
        <input type="text" name="distributor"><br>
        </div>
        
        
        <div class="form-group">
        <label for="director">Redatelj:</label>
        <input type="text" name="director"><br>
        </div>

        
        <div class="form-group">
        <label for="actors">Glumci:</label>
        <textarea name="actors" rows="4" cols="50"></textarea><br>
        </div>

        
        <div class="form-group">
        <label for="classification">Dobno ograničenje:</label>
        <input type="text" name="classification"><br>
        </div>

        
        <div class="form-group">
        <label for="otherData">Ostali podaci:</label>
        <textarea name="otherData" rows="4" cols="50"></textarea><br>
        </div>

        
        <div class="form-group">
        <input type="submit" value="Dodaj film">
        </div>
    </form>
     </div>
        
        
    <!-- Prikazi filmove -->
    <h3>Lista filmova:</h3>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Ime</th>
            <th>Opis</th>
            <th>Stranica</th>
            <th>Originalni naslov</th>
            <th>Žanr</th>
            <th>Nacionalnost</th>
            <th>Trajanje</th>
            <th>Godina</th>
            <th>Distributer</th>
            <th>Redatelj</th>
            <th>Glumci</th>
            <th>Dobno ograničenje</th>
            <th>Drugi podaci</th>
            <th>Akcije</th> <!-- Add a new column for actions -->
        </tr>
        <%
            // SQL upit za dohvat svih filmova
           

            // Lista za pohranu rezultata
            List<Film> listaDeFilms = dt.getAllFilms();

            
        %>
        <!-- Loop through the list of films and display in the table -->
        <% for (Film film : listaDeFilms) { %>
            <tr>
                <td><%= film.getFilmId() %></td>
                <td><%= film.getFilmName() %></td>
                <td><%= film.getSynopsis() %></td>
                <td><%= film.getFilmPage() %></td>
                <td><%= film.getOriginalTitle() %></td>
                <td><%= film.getGenre() %></td>
                <td><%= film.getNationality() %></td>
                <td><%= film.getDuration() %></td>
                <td><%= film.getReleaseYear() %></td>
                <td><%= film.getDistributor() %></td>
                <td><%= film.getDirector() %></td>
                <td><%= film.getActors() %></td>
                <td><%= film.getClassification() %></td>
                <td><%= film.getOtherData() %></td>
                <td>
                    <!-- Add a form for deleting the movie -->
                    <form action="AdminDeleteFilmServlet" method="post">
                        <input type="hidden" name="filmId" value="<%= film.getFilmId() %>">
                        <input type="submit" value="Izbriši">
                    </form>                
                </td>
            </tr>
        <% } %>
    </table>
    
    
    
    <!-- Prikazi sale -->
<h3>Izmjeni filmove</h3>

<table border="1">
    <tr>
            <th>ID</th>
            <th>Ime</th>
            <th>Opis</th>
            <th>Stranica</th>
            <th>Originalni naslov</th>
            <th>Žanr</th>
            <th>Nacionalnost</th>
            <th>Trajanje</th>
            <th>Godina</th>
            <th>Distributer</th>
            <th>Redatelj</th>
            <th>Glumci</th>
            <th>Dobno ograničenje</th>
            <th>Drugi podaci</th>
            <th>Akcije</th> <!-- Add a new column for actions -->
    </tr>
    <% for (Film film : listaDeFilms) { %>
        <tr>
                <td><%= film.getFilmId() %></td>
                <td><%= film.getFilmName() %></td>
                <td><%= film.getSynopsis() %></td>
                <td><%= film.getFilmPage() %></td>
                <td><%= film.getOriginalTitle() %></td>
                <td><%= film.getGenre() %></td>
                <td><%= film.getNationality() %></td>
                <td><%= film.getDuration() %></td>
                <td><%= film.getReleaseYear() %></td>
                <td><%= film.getDistributor() %></td>
                <td><%= film.getDirector() %></td>
                <td><%= film.getActors() %></td>
                <td><%= film.getClassification() %></td>
                <td><%= film.getOtherData() %></td>
                <td>
                <!-- Add a form for modifying the theater -->
                <form action="AdminModifyFilmServlet" method="post">
                        <input type="hidden" name="filmId" value="<%= film.getFilmId() %>">
                        <!-- Add other input fields for modification -->
                        <label for="filmName">Ime:</label>
                        <input type="text" name="filmName" value="<%= film.getFilmName() %>" required><br>
                        <label for="synopsis">Opis:</label>
                        <textarea name="synopsis" rows="4" cols="50" required><%= film.getSynopsis() %></textarea><br>
                        <label for="filmPage">Stranica:</label>
                        <input type="text" name="filmPage" value="<%= film.getFilmPage() %>" required><br>
                        <label for="originalTitle">Originalni naslov:</label>
                        <input type="text" name="originalTitle" value="<%= film.getOriginalTitle() %>" required><br>
                        <label for="genre">Žanr:</label>
                        <input type="text" name="genre" value="<%= film.getGenre() %>" required><br>
                        <label for="nationality">Nacionalnost:</label>
                        <input type="text" name="nationality" value="<%= film.getNationality() %>" required><br>
                        <label for="duration">Trajanje (min):</label>
                        <input type="number" name="duration" value="<%= film.getDuration() %>" required><br>
                        <label for="releaseYear">Godina:</label>
                        <input type="number" name="releaseYear" value="<%= film.getReleaseYear() %>" required><br>
                        <label for="distributor">Distributer:</label>
                        <input type="text" name="distributor" value="<%= film.getDistributor() %>" required><br>
                        <label for="director">Redatelj:</label>
                        <input type="text" name="director" value="<%= film.getDirector() %>" required><br>
                        <label for="actors">Glumci:</label>
                        <textarea name="actors" rows="4" cols="50" required><%= film.getActors() %></textarea><br>
                        <label for="classification">Dobno ograničenje:</label>
                        <input type="text" name="classification" value="<%= film.getClassification() %>" required><br>
                        <label for="otherData">Ostali podaci:</label>
                        <textarea name="otherData" rows="4" cols="50" required><%= film.getOtherData() %></textarea><br>
                        <!-- Add more input fields based on your Film class attributes -->
                        <input type="submit" value="Izmjeni">
                    </form>
            </td>
        </tr>
    <% } %>
</table>
    
    
    <p class="error-message"><c:if test="${not empty error_message}">${error_message}</c:if></p>

    <!-- Back to admin home button -->
    <button class="back-to-home"><a href="adminHome.jsp">Povratak</a></button>

        <%
    }else{
%>
    
<center>
<font>Nisi administrator, vrati se na početnu stranicu i logiraj se</font>
    <a href="index.html" class="button">Volver a home</a>
    
</center>  
    <%


}
}else{
%>
    
<center>
<font>Nisi administrator, vrati se na početnu stranicu i logiraj se</font>
    <a href="index.html" class="button">Volver a home</a>
    
</center>   
    <%

}


%>
</body>
</html>
