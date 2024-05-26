
<%@page import="Model.DataBase"%>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.sql.Time" %>
<%@ page import="Model.Ticket" %>
<%@ page import="Model.Film" %>
<%@ page import="Model.Sala" %>
<%@ page import="Model.Cinema" %>
<%@ page import="Model.User" %>
<%@page import="Model.Sesiones"%>
<%@page import="Model.Reservation"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Upravljanje sesijama</title>
    <link rel="stylesheet" href="style3.css">
</head>
<body>
    <h2>Upravljanje sesijama</h2>


<%

     String admin=(String) session.getAttribute("admin");
            if(admin!=null){
            
            
            if(admin.equals("y")){
    
    DataBase dt=new DataBase();
    
    // Lista za pohranu rezultata
    List<Cinema> listaDeCines = dt.getTodosCines();
    List<Film> filmList = dt.getAllFilms();
    List<Sala> salaList = dt.getAllSalas();   
    List<Sesiones> sesions = dt.getAllSesions();
    
 
%>



<div class="form-container">
    
    <!-- <h3>Lista sesija:</h3>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Film</th>
        <th>Kino</th>
        <th>Dvorana</th>
        <th>Datum</th>
        <th>Vrijeme</th>
        
        
    </tr>
        <%
            for (Sesiones sesion : sesions) { 
        %>
            <tr>
                <td><%= sesion.getId()%></td>
                <td><%= sesion.getPelicula()%></td>
                <td><%= sesion.getCinema_id()%></td>
                <td><%= sesion.getSala_id()%></td>
                <td><%= sesion.getDate()%></td>
                <td><%= sesion.getHour()%></td>
                
            
        </tr>
        </table>-->
                
        <% 
            }            
            %>
            
            <br><br>
    <form class="ticket-form" action="AdminAddSesionServlet" method="post">
          
        <!--    <div class="form-group">
                <label>Sesion: </label>
        <select name="sesion" id="sesion">
                <% for (Sesiones sesi : sesions) { %>
                    <option value="<%=sesi.getId() %>"><%=sesi.getId()%></option>
                <% } %>
            </select>
        </div> 
                -->
            <div>    
           <label for="cinema">Označi kino:</label>
            <select name="cinemaId" id="cinema">
                <% for (Cinema cine : listaDeCines) { %>
                    <option value="<%=cine.getCinemaId()%>"><%=cine.getCinemaName()%></option>
                <% } %>
            </select>
        </div>
            
            <div class="form-group">
            <label for="filmId">Označi film:</label>
            <select name="filmId" id="film">
                <% for (Film film : filmList) { %>
                    <option value="<%=film.getFilmId()%>"><%=film.getFilmName()%></option>
                <% } %>
            </select>
        </div>

        <div class="form-group">
            <label for="date">Datum:</label>
            <input type="date" name="date" required>
        </div>

        <div class="form-group">
            <label for="time">Sat:</label>
            <input type="time" name="time" required>
        </div>

        <div class="form-group">
            <label for="salaId">Označi dvoranu:</label>
            <select name="salaId" id="sala">
                <% for (Sala sala : salaList) { %>
                    <option value="<%=sala.getSalaId()%>"><%=sala.getSalaId()%></option>
                <% } %>
            </select>
        </div>

        <div class="form-group">
            <input type="submit" value="Dodaj sesiju">
        </div>
    </form>
</div>

            
            <br>


    
    
    
    

<!-- Prikazi karte -->
    <% 

    

    if (sesions != null && !sesions.isEmpty()) {
    %>
        <table border="1">
            <tr>
                <th>Id</th>
                <th>Film</th>
                <th>Kino</th>
                <th>Dvorana</th>
                <th>Datum</th>
                <th>Vrijeme</th>
                <th>Akcije</th>
                <!--<th>Film Name</th>
                <th>Film Name</th>
                <th>Film Name</th>
                <th>Film Name</th>
                <th>Film Name</th>
                <th>Film Name</th>-->
                
                <!-- Add other relevant columns -->
            </tr>
            <% for (Sesiones sesion : sesions) { %>
                <tr>
                    
                    <td><%=sesion.getId()%></td>
                    <td><%= sesion.getPelicula()%></td>
                    <td><%= sesion.getNombreCine()%></td>
                    <td><%= sesion.getNombreSala()%></td>
                    <td><%= sesion.getDate()%></td>
                    <td><%= sesion.getHour()%></td>
                    <td>  
                    <!-- Add a form for deleting the ticket -->
                <form action="AdimDeleteSesionServlet" method="post">
                    <input type="hidden" name="sesionId" value="<%= sesion.getId()%>">
                    <input type="submit" value="Izbriši">
                </form>
            </td>
        </tr>
                   
                </tr>
            <% } %>
        </table>
    <% 
    } else {
    %>
        <p>Nema dostupnih sesija.</p>
    <% 
    }
    %>
                
    
</table>





<!-- Show tickets -->

<!--
<h3>Lista de Entradas:</h3>
<table border="1">
    <tr>
                <th>Id</th>
                <th>Fiml</th>
                <th>Kino</th>
                <th>Dvorana</th>
                <th>Datum</th>
                <th>Vrijeme</th>
                <th>Akcije</th>
    </tr>
    <% for (Sesiones sesion : sesions) { %>
        <tr>

                    <td><%=sesion.getId()%></td>
                    <td><%= sesion.getFilm_id()%></td>
                    <td><%= sesion.getCinema_id()%></td>
                    <td><%= sesion.getSala_id()%></td>
                    <td><%= sesion.getDate()%></td>
                    <td><%= sesion.getHour()%></td>
            <td>
               
                <form class="forma2" action="AdminModifyEntradaServlet" method="post">
                    <input type="hidden" name="ticketId" value="<%= sesion.getId() %>">
                    <label for="time">Nova sesija:</label>
                    
                    <select name="sesion" id="sesion">
                        <% for (Film film : filmList) { %>
                        <option value="<%=film.getId()%>"><%=film.getFilmName()%></option>
                        <% } %>
                    </select><br>                    
                    <label for="rowNumber">Novi datum:</label>
                    <input type="date" name="fecha" value="<%= sesion.getDate() %>" required><br>
                    <label for="columnNumber">Novo vrijeme:</label>
                    <input type="time" name="hour" value="<%= sesion.getHour() %>" required><br>
                    
                    <input type="submit" value="Izmjeni">
                </form>
            </td>
        </tr>
    <% } %>
</table>
-->

<%








%>


            





    <p class="error-message"><c:if test="${not empty error_message}">${error_message}</c:if></p>

    <!-- Back to admin home button -->
    <button class="back-to-home"><a href="adminHome.jsp">Povratak</a></button>
    
                    <%
    }else{
%>
    
<center>
<font>Nisi administrator, vrati se na pocetnu stranicu i ulogiraj se</font>
    <a href="index.html" class="button">Volver a home</a>
    
</center>  
    <%


}
}else{
%>
    
<center>
<font>Nisi korisnik, vrati se na pocetnu stranicu i ulogiraj se</font>
    <a href="index.html" class="button">Volver a home</a>
    
</center>   
    <%

}


%>


</body>
</html>