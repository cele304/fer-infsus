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
    <title>Upravljanje ulaznicama</title>
    <link rel="stylesheet" href="style3.css">
</head>
<body>
    <h2>Upravljanje ulaznicama</h2>


<%

    
    DataBase dt=new DataBase();
    
    // Lista za pohranu rezultata
    List<Cinema> listaDeCines = dt.getTodosCines();
    List<Film> filmList = dt.getAllFilms();
    List<Sala> salaList = dt.getAllSalas();   
    List<Sesiones> sesions = dt.getAllSesions();
    List<User> users = dt.getAllUsers();
    
String admin=(String) session.getAttribute("admin");
            if(admin!=null){
            
            
            if(admin.equals("y")){
 
%>



<div class="form-container">
    
     <h3>Lista sesija:</h3>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Film</th>
        <th>Kino</th>
        <th>Dvorana</th>
        <th>Datum</th>
        <th>Sat</th>
        
        
    </tr>
        <%
            for (Sesiones sesion : sesions) { 
        %>
            <tr>
                <td><%= sesion.getId()%></td>
                <td><%= sesion.getFilm_id()%></td>
                <td><%= sesion.getCinema_id()%></td>
                <td><%= sesion.getSala_id()%></td>
                <td><%= sesion.getDate()%></td>
                <td><%= sesion.getHour()%></td>
                
            
        </tr>
        </table>
                
        <% 
            }            
            %>
            
            <br><br>
    <form class="ticket-form" action="AdminAddEntradaServlet" method="post">
        <div class="form-group">
            <label>Korisnik: </label>
        <select name="users" id="users">
                <% for (User user : users) { %>
                    <option value="<%=user.getUserId() %>"><%=user.getUsername()%></option>
                <% } %>
            </select>
        </div>   
            <div class="form-group">
                <label>Sesija: </label>
        <select name="sesion" id="sesion">
                <% for (Sesiones sesi : sesions) { %>
                    <option value="<%=sesi.getId() %>"><%=sesi.getId()%></option>
                <% } %>
            </select>
        </div> 
            
           <!-- <label for="cinema">Selecciona Cine:</label>
            <select name="cinemaId" id="cinema">
                <% for (Cinema cine : listaDeCines) { %>
                    <option value="<%=cine.getCinemaId()%>"><%=cine.getCinemaName()%></option>
                <% } %>
            </select>
        </div>

        <div class="form-group">
            <label for="date">Fecha:</label>
            <input type="date" name="date" required>
        </div>

        <div class="form-group">
            <label for="time">Hora:</label>
            <input type="time" name="time" required>
        </div>

        <div class="form-group">
            <label for="salaId">Selecciona Sala:</label>
            <select name="salaId" id="sala">
                <% for (Sala sala : salaList) { %>
                    <option value="<%=sala.getSalaId()%>"><%=sala.getSalaId()%></option>
                <% } %>
            </select>
        </div>
         -->
         
         
         
        <div class="form-group">
            <label for="rowNumber">Broj reda:</label>
            <input type="number" name="rowNumber" required>
        </div>

        <div class="form-group">
            <label for="columnNumber">Broj stupca:</label>
            <input type="number" name="columnNumber" required>
        </div>
<!--
        <div class="form-group">
            <label for="filmId">Selecciona Película:</label>
            <select name="filmId" id="film">
                <% for (Film film : filmList) { %>
                    <option value="<%=film.getFilmId()%>"><%=film.getFilmName()%></option>
                <% } %>
            </select>
        </div>
-->

        <div class="form-group">
            <input type="submit" value="Dodaj rezervaciju">
        </div>
    </form>
</div>
            
            <br>


    
    
    
    

<!-- Prikazi karte -->
    <% 

    List<Reservation> reservas=dt.getTodasReservas();

    if (reservas != null && !reservas.isEmpty()) {
    %>
        <table border="1">
            <tr>
                <th>ID Rezervacija</th>
                <th>Kino</th>
                <th>Dvorana</th>
                <th>Film</th>
                <th>Dan</th>
                <th>Sat</th>
                <th>Sjedalo</th>
                <th>Ostalo</th>
                <!--<th>Film Name</th>
                <th>Film Name</th>
                <th>Film Name</th>
                <th>Film Name</th>
                <th>Film Name</th>
                <th>Film Name</th>-->
                
                <!-- Add other relevant columns -->
            </tr>
            <% for (Reservation rax : reservas) { %>
                <tr>
                    <td><%= rax.getReservationId() %></td>
                    <td><%= rax.getSesion().getNombreCine() %></td>
                    <td><%= rax.getSesion().getNombreSala() %></td>
                    <th><%= rax.getSesion().getPelicula() %></td>
                    <td><%= rax.getSesion().getDate()%></td>
                    <td><%= rax.getSesion().getHour()%></td>
                    <td><%="Stupac: "+ rax.getTick().getColumnNumber()+"| Red: "+rax.getTick().getRowNumber() %></td>
                    <td>  
                    <!-- Add a form for deleting the ticket -->
                <form action="AdminDeleteEntradaServlet" method="post">
                    <input type="hidden" name="ticketId" value="<%= rax.getReservationId()%>">
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
        <p>Nema dostupnih rezervacija.</p>
    <% 
    }
    %>
                
    
</table>





<!-- Show tickets -->
<h3>Lista ulaznica:</h3>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Kino ID</th>
        <th>Datum</th>
        <th>Vrijeme</th>
        <th>Dvorana ID</th>
        <th>Broj reda</th>
        <th>Broj stupca</th>
        <th>Film ID</th>
        <th>Ostalo</th> <!-- Add a new column for actions -->
    </tr>
    <% for (Reservation ret : reservas) { %>
        <tr>
            <td><%= ret.getTick().getTicketId() %></td>
            <td><%= ret.getSesion().getCinema_id() %></td>
            <td><%= ret.getSesion().getDate() %></td>
            <td><%= ret.getSesion().getHour()%></td>
            <td><%= ret.getSesion().getSala_id()%></td>
            <td><%= ret.getTick().getRowNumber() %></td>
            <td><%= ret.getTick().getColumnNumber() %></td>
            <td><%= ret.getSesion().getFilm_id()%></td>
            <td>
                <!-- Form for modifying the ticket -->
                <form class="forma2" action="AdminModifyEntradaServlet" method="post">
                    <input type="hidden" name="ticketId" value="<%= ret.getTick().getTicketId() %>">
                    <label for="time">Nova sesija:</label>
                    
                    <select name="sesion" id="sesion">
                        <% for (Sesiones ses : sesions) { %>
                        <option value="<%=ses.getId()%>"><%=ses.getId()%></option>
                        <% } %>
                    </select><br>                    
                    <label for="rowNumber">Novi broj reda:</label>
                    <input type="number" name="rowNumber" value="<%= ret.getTick().getRowNumber() %>" required><br>
                    <label for="columnNumber">Novi broj stupca:</label>
                    <input type="number" name="columnNumber" value="<%= ret.getTick().getColumnNumber() %>" required><br>
                    <!-- Add other input fields for modification -->
                    <input type="submit" value="Izmjeni">
                </form>
            </td>
        </tr>
    <% } %>
</table>


<%








%>


            






    <p class="error-message"><c:if test="${not empty error_message}">${error_message}</c:if></p>

    <!-- Back to admin home button -->
    <button class="back-to-home"><a href="adminHome.jsp">Povratak</a></button>

    <%
    }else{
%>
    
<center>
<font>Nisi administrator, vrati se na početnu stranicu i započni sesiju</font>
    <a href="index.html" class="button">Povratak</a>
    
</center>  
    <%


}
}else{
%>
    
<center>
<font>Nisi korisnik, vrati se na početnu stranicu i registriraj se ili ulogiraj</font>
    <a href="index.html" class="button">Povratak</a>
    
</center>   
    <%

}


%>

</body>
</html>