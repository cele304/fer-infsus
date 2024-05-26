<%@page import="Model.DataBase"%>
<%@page import="Model.Reservation"%>
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
    <title>Upravljanje rezervacijama</title>
    <link rel="stylesheet" href="style3.css">
</head>
<body>
    <h2>Upravljanje rezervacijama</h2>

    <%
            // SQL upit za dohvat svih filmova
            String admin=(String) session.getAttribute("admin");
            if(admin!=null){
            
            
            if(admin.equals("y")){
            
            DataBase dt=new DataBase();

            // Lista za pohranu rezultata
            List<Reservation> listaDeReservas = dt.getTodasReservas();
            
            
        %>

    <!-- Prikazi filmove -->
    <h3>Lista rezervacija:</h3>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Korisnik ID</th>
            <th>Ulaznica ID</th>
        </tr>
        
        <!-- Loop through the list of films and display in the table -->
        <% for (Reservation reservation : listaDeReservas) { %>
            <tr>
                <td><%= reservation.getReservationId() %></td>
                <td><%= reservation.getUserId() %></td>
                <td><%= reservation.getTicketId() %></td>
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
<font>Nisi administrator, vrati se na početnu stranicu i ulogiraj se</font>
    <a href="index.html" class="button">Volver a home</a>
    
</center>  
    <%


}
}else{
%>
    
<center>
<font>Nisi korisnik, vrati se na početnu stranicu i ulogiraj se</font>
    <a href="index.html" class="button">Volver a home</a>
    
</center>   
    <%

}


%>
    
</body>
</html>