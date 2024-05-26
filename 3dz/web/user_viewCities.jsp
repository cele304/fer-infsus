

<%@page import="Model.DataBase"%>
<%@page import="Model.Cinema"%>
<%@page import="Model.Comment"%>
<%@page import="Model.Sesiones"%>
<%@page import="Model.Film"%>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="Model.Sala" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <title>Kina</title>
    <link rel="stylesheet" href="style8.css">
</head>
<body>
    <h2>Kina</h2>


    <!-- Search Form -->
    <form action="UserCinemaSearch" method="post">
        <input type="text" name="cinemaName" placeholder="Upisi ime kina">
        <input type="submit" value="Search">
    </form>

    <% 
        List<Cinema> cinemas = (List<Cinema>) request.getAttribute("cines");
        if (cinemas == null) {
            DataBase dt = new DataBase();
            cinemas = dt.getTodosCines();
        }
    %>

    <table border="1">
        <tr>
            <th>City</th>
            <th>Cinema</th>    
        </tr>
        <% for (Cinema cin : cinemas) { %>
            <tr>
                <td><%= cin.getCityName() %></td>
                <td><%= cin.getCinemaName() %></td>     
            </tr>
        <% } %>
    </table>
    
    




    
    <%
 
String loggedInUser = (String) session.getAttribute("loggedInUser");



DataBase dt=new DataBase();

List<Cinema> cines =dt.getTodosCines();
   
%>



<!--      
<table border="1">
    <tr>
        <th>Grad</th>
        <th>Kino</th>    
    </tr>
-->
    <%
        //for(Cinema cin : cines){
        %>
        <!--
        <tr>
            <th><//%=//cin.getCityName()%></th>
            <th><//%=//cin.getCinemaName()%></th>     
        </tr>
        -->
  
    <%
        //}

    %>
  <!--  
</table>
 --> 
    <br><br>
        <%
    String reg="userHome.jsp";       
    if(loggedInUser==null) reg="index.html";

    %>
    
    <br><br>
    <p class="back-link"><a href=<%=reg%>>Povratak</a></p>


        </body>
</html>


