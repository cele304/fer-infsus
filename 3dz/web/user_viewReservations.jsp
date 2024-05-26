


<%@page import="Model.DataBase"%>
<%@page import="Model.Ticket"%>
<%@page import="Model.Sesiones"%>
<!-- not working-->
<%@ page import="java.util.List" %>
<%@ page import="Model.Reservation" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Moje rezervacije</title>
    <link rel="stylesheet" href="style6.css">
</head>
<body>
    <h2>Moje rezervacije</h2>

    <% 

    DataBase dt=new DataBase();
 
    String user = (String) session.getAttribute("loggedInUser");
    // SQL upit za dohvat svih filmova

    // Lista za pohranu rezultata
     String admin=(String) session.getAttribute("admin");
            if(admin!=null){
    
    List<Reservation> reservas=dt.getAllReservUser(user);

    
    if (reservas != null && !reservas.isEmpty()) {
    %>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Kino</th>
                <th>Dvorana</th>
                <th>Film</th>
                <th>Dan</th>
                <th>Vrijeme</th>
                <th>Sjedalo</th>
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
    
     <button class="back-button" onclick="location.href='userHome.jsp'">Povratak</button>
     
                         <%

}else{
%>
    
<center>
<font>Nisi korisnik, vrati se na pocetnu stranicu i registriraj se</font>
    <a href="index.html" class="button">Povratak</a>
    
</center>   
    <%

}


%>

</body>
</html>
