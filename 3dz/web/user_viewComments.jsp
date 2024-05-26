

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


<html>
<head>
    <meta charset="UTF-8">
    <title>Komentari</title>
    <link rel="stylesheet" href="style8.css">
</head>
<body>
    <h2>Komentari</h2>

   
    
    
    <%
    // Postavljanje podataka za povezivanje s bazom podataka
    String loggedInUser = (String) session.getAttribute("loggedInUser");
    
   
    DataBase dt=new DataBase();
    
            String admin=(String) session.getAttribute("admin");
            if(admin!=null){
            
            
            

    
    List<String> pelis =new ArrayList<>();
    pelis.add("");
    
    List<Film> pelisaux =dt.getAllFilms();
        while (!pelisaux.isEmpty()) {

            pelis.add(pelisaux.remove(0).getFilmName());  
        }
   
%>
    
    <form action="PasaPeliculaServlet" method="post">

        <select name="filmName" id="cines">
            <% for (String peliculas : pelis) { %>
            <option value="<%=peliculas%>"><%=peliculas%></option>
            <% } %>
        </select>

        <input type="submit" value="Filtriraj po filmu">
    </form>  
    
        
        <%
    // Postavljanje podataka za povezivanje s bazom podataka
   
    
    
    // SQL upit za dohvat svih sala
    
    String sql = "SELECT * FROM COMMENT";
    

    // Lista za pohranu rezultata
    
    List<Comment> coms = dt.getAllComentarios();
    String filtro = (String) request.getAttribute("nombrePeli");
    try {

        if (filtro != null) {
            if (!filtro.equals("")) {
                coms=dt.getCommentFilm(filtro);
            }
        }

    } catch (Exception e) {
        System.out.println("Error: "+e);
    }
       
   if(coms.isEmpty()){
    
    %>
       <br> 
       <p class="error">Nema komentara za ovaj film</p>       
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
    <%
    String reg="userHome.jsp";       
    if(loggedInUser==null) reg="index.html";

    %>
    
    <br><br>
    <p class="back-link"><a href=<%=reg%>>Povratak</a></p>
    
        <%

}else{
%>
    
<center>
<font>Nisi korisnik, vrati se na početnu stranicu i registriraj se</font>
    <a href="index.html" class="button">Početak</a>
    
</center>   
    <%

}


%>

        </body>
</html>