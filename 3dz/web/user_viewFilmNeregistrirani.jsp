


<%@page import="Model.Cinema"%>
<%@page import="Model.DataBase"%>
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
<html>
<head>
    <meta charset="UTF-8">
    <title>Filmovi po žanru</title>
    <link rel="stylesheet" href="style7.css">
</head>
<body>
    <h2>Filmovi po žanru</h2>
    <% String loggedInUser = (String) session.getAttribute("loggedInUser");%>
    <% loggedInUser = null; %>
    
    
    <form action="filterFilmCinemaGeneServlet" method="post">
        
        
        <%
 
    
    DataBase dt=new DataBase();

    List<Cinema> listaDeCines = dt.getTodosCines();
    
    List<String> generos=new ArrayList();
    
    List<Film> listaDePeliculas = dt.getAllFilms();
    
    boolean añadir=true;
    
    for(int i=0;i<listaDePeliculas.size();i++){
        String aux=listaDePeliculas.get(i).getGenre();
        
        for(int j=0;j<generos.size();j++){
            if (aux != null && aux.equals(generos.get(j))) {
               añadir=false;
            }
            
        }
        if(añadir){
            generos.add(aux);
        }else añadir=true;
        
    
    }

    
%>
<label for="cines">Označi žanr</label>
        
        
        <select name="generos" id="generos">
            <option value=""></option>
            <% for (String gen : generos) { %>
            
            <option value="<%=gen%>"><%=gen%></option>
            
            <% } %>
        </select>
        
        <br>
        

        <input type="submit" value="Vidi filmove po ovom kriteriju">
    </form>

    <!-- Prikazi filmove po žanru -->
    <ul>
        <% 

        String com=(String)request.getAttribute("com");
        if(com!=null){
             if(!com.equals("")){
        %>
            
        <font color="green">Komentar uspješno dodan!</font>
        <p><a href="user_viewMyComments.jsp">Vidi moje komentare</a></p>
      
      <%
        }
            }
            
        listaDePeliculas=(List<Film>) request.getAttribute("filmbycinemagenre");
        String error=(String)request.getAttribute("error");
        
        if (listaDePeliculas == null || listaDePeliculas.isEmpty()) {
            listaDePeliculas=dt.getAllFilms();
            if(error!=null){
            %>
            
      
            <font color="red"><!-- comment --><%=(String)request.getAttribute("error")%></font>
        
        <%
            }
        }    
        
        
        if (listaDePeliculas != null && !listaDePeliculas.isEmpty()) {
            
        %>
            <h3>Lista filmova:</h3>
<table border="1">
    <tr>
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
    </tr>
    <% for (Film film : listaDePeliculas) { %>
        <tr>
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
        </tr>
    <% } %>
    
    
</table>
        
    <center>        
<%
    
    if(loggedInUser!=null){
    
    
      %>
      <br><br>
      <label> Ostavi svoj komentar za neki od ovih filmova</label>    
            
      <form action="UserComentaCineServlet" method="post">
          
        <select name="peliname" id="cines">
        <% for (Film peliculas : listaDePeliculas) { %>
        <option value="<%=peliculas.getFilmName()%>"><%=peliculas.getFilmName()%></option>
        <% } %>
        </select>
        <br>
        <label>Korisnik: </label><!-- comment -->
          <input type="text" id="user" name="user" value="<%=loggedInUser%>" readonly><br><br>
        <textarea name="comentario" rows="4" cols="50" required></textarea><br>

        <input type="submit" value="Ostavi komentar">
    </form>  
            
            <%  
                
            }else{
%>

<br>
<p> Nisi registriran. Ako želiš kupiti kartu ili ostaviti komentar registriraj se!</p>
     <a href="login.jsp" class="button">Prijava</a> 
     <a href="registration.jsp" class="button">Registracija</a> 
      <%

}    
    
    
    } else {
        %>
            <li>Nema dostupnih filmova za ovaj film</li>
        <% 
        }
        %>
    </ul>
    
       <%
    String reg="userHome.jsp";       
    if(loggedInUser==null) reg="index.html";

    %>
    
    <br><br>
    <p class="back-link"><a href=<%=reg%>>Povratak</a></p>
</center> 
</body>
</html>