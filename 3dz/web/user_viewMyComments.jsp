

<%@page import="Model.DataBase"%>
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

<!--<!-- todo -->
<html>
<head>
    <meta charset="UTF-8">
    <title>Komentari</title>
    <link rel="stylesheet" href="style8.css">
</head>
<body>
    

  
    
    
    <%
    // Postavljanje podataka za povezivanje s bazom podataka
    
     String admin=(String) session.getAttribute("admin");
            if(admin!=null){
            
    
    String loggedInUser = (String) session.getAttribute("loggedInUser");
    %>
    
    <h2>Moji komentari</h2>
    
    <%
    // SQL upit za dohvat svih sala
        // Postavljanje podataka za povezivanje s bazom podataka

    
   
    DataBase dt=new DataBase();

    
    List<String> pelis =new ArrayList<>();
    pelis.add("");
    
    List<Film> pelisaux =dt.getAllFilms();
        while (!pelisaux.isEmpty()) {

            pelis.add(pelisaux.remove(0).getFilmName());  
        }

    
%>
    
    <form action="UserPasaPeliculaServlet" method="post">

        <select name="filmName" id="cines">
            <% for (String peliculas : pelis) { %>
            <option value="<%=peliculas%>"><%=peliculas%></option>
            <% } %>
        </select>
        
        

        <input type="submit" value="Filtriraj po filmu">
    </form>  
    
        
        <%
    
    List<Comment> coms =dt.getAllComentariosUser(loggedInUser);

    try {

        String filtro=(String)request.getAttribute("nombrePeli");
        
        System.out.println(filtro+" jsp");
        
        if(filtro!=null){
            if(!filtro.equals("")){
                coms=dt.getCommentFilmUser(filtro, loggedInUser);
            }
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
    




    if(coms.isEmpty()){
    
    %>
       <br> 
       <font color="red">Nemaš komentara za ovaj film</font>
    <%
    
            }else{

%>
    
<table border="1">
    <tr>
        <th>Film</th><!-- comment -->
        <th>Korisnik</th><!-- comment -->
        <th>Komentar</th>    
    </tr>
    <%
        for(Comment con : coms){
        %>
        <tr>
            <th><%=con.getFilm()%></th>
            <th><%=con.getUser()%></th>
            <th><%=con.getCommentText()%></th>     
        </tr>
  
    <%
        }
        }

    %>
    
</table>
    
    <br><br>
        <p class="back-link"><a href="userHome.jsp">Povratak</a></p>
        
                            <%

}else{
%>
    
<center>
<font>Nisi korisnik, vrati se na pocetnu stranicu i registriraj se</font>
    <a href="index.html" class="button">Povratak na pocetnu stranicu</a>
    
</center>   
    <%

}


%>

        </body>
</html>
