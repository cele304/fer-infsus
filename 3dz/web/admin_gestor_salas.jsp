

<%@page import="Model.Cinema"%>
<%@page import="Model.DataBase"%>
<%@page import="Model.User"%>
<%@page import="Model.Sala" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Upravljanje dvoranama</title>
    <link rel="stylesheet" href="style3.css">
</head>
<body>
    <h2>Upravljanje dvoranama</h2>
<center><p class="error-message"><c:if test="${not empty error_message}">${error_message}</c:if></p></center>
    <div class="form-container">
    <!-- Dodaj salu form -->
    <form class="ticket-form" action="AdminAddSalaServlet" method="post">
        
        
        <%
            
    DataBase dt=new DataBase();
    
    String admin=(String) session.getAttribute("admin");
    if(admin!=null){
            
            
        if(admin.equals("y")){

            List<Cinema> listaDeCines = dt.getTodosCines();

   
%>
<div class="form-group">
<label for="cines">Označi kino</label>
        <select name="cinemaName" id="cines">
            <% for (Cinema cine : listaDeCines) { %>
            <option value="<%=cine.getCinemaName() %>"><%=cine.getCinemaName()%></option>
            <% } %>
        </select>
        <br>
        </div>
        
        <div class="form-group">
        <label for="nameSala">Ime dvorana:</label>
        <input type="text" name="theaterName" required><br>
        </div>
        
        <div class="form-group">
        <label for="rowsCount">Broj redova:</label>
        <input type="number" name="rowsCount" required><br>
        </div>
        
        
        <div class="form-group">
        <label for="columnsCount">Broj stupaca:</label>
        <input type="number" name="columnsCount" required><br>
        </div>

        
        <div class="form-group">
        <input type="submit" value="Dodaj dvoranu">
        </div>
    </form>
            </div>

<!-- ostatak direktiva -->

<%

    List<Sala> listaDeSalas = dt.getAllSalas();

    
%>

<!-- Prikazi sale -->
<h3>Lista dvorana:</h3>


<table border="1">
    <tr>
        <th>ID</th>
        <th>Broj redova</th>
        <th>Broj stupaca</th>
        <th>Ime</th>
        <th>Akcije</th> <!-- Add a new column for actions -->
    </tr>
    <% for (Sala sala : listaDeSalas) { %>
        <tr>
            <td><%= sala.getSalaId() %></td>
            <td><%= sala.getRowsCount() %></td>
            <td><%= sala.getColumnsCount() %></td>
            <td><%= sala.getTheaterName() %></td>
            <td>
                <!-- Add a form for deleting the theater -->
                <form action="AdminDeleteSalaServlet" method="post">
                    <input type="hidden" name="salaId" value="<%= sala.getSalaId() %>">
                    <input type="submit" value="Izbriši">
                </form>
            </td>
        </tr>
    <% } %>
</table>




<!-- Prikazi sale -->
<h3>Lista dvorana:</h3>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Broj stupaca</th>
        <th>Broj redova</th>
        <th>Ime</th>
        <th>Akcije</th> <!-- Add a new column for actions -->
    </tr>
    <% for (Sala sala : listaDeSalas) { %>
        <tr>
            <td><%= sala.getSalaId() %></td>
            <td><%= sala.getRowsCount() %></td>
            <td><%= sala.getColumnsCount() %></td>
            <td><%= sala.getTheaterName() %></td>
            <td>
                <!-- Add a form for modifying the theater -->
                <form action="AdminModifySalaServlet" method="post">
                    <input type="hidden" name="salaId" value="<%= sala.getSalaId() %>">
                    <label for="rowsCount">Novi broj redova:</label>
                    <input type="number" name="rowsCount" value="<%= sala.getRowsCount() %>" required><br>
                    <label for="columnsCount">Novi broj stupaca:</label>
                    <input type="number" name="columnsCount" value="<%= sala.getColumnsCount() %>" required><br>
                    <label for="theaterName">Novo ime sale:</label>
                    <input type="text" name="theaterName" value="<%= sala.getTheaterName() %>" required><br>
                    <input type="submit" value="Izmjeni">
                </form>
            </td>
        </tr>
    <% } %>
</table>



    

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
<font>Nisi administrator, vrati se na početnu stranicu i ulogiraj se ili registriraj</font>
    <a href="index.html" class="button">Volver a home</a>
    
</center>   
    <%

}


%>


</body>
</html>