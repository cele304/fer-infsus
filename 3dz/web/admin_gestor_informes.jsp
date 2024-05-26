


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
    <title>Filmovi</title>
    <link rel="stylesheet" href="style7.css">
</head>
<body>
    <h2>Filmovi</h2>
    <% String loggedInUser = (String) session.getAttribute("loggedInUser");%>
    
    
    
    <form action="filterFilmCinemaGeneServlet" method="post">
        
        
        <%
            
            
            String admin=(String) session.getAttribute("admin");
            if(admin!=null){
            
            
            if(admin.equals("y")){
 
    
    DataBase dt=new DataBase();

    List<Cinema> listaDeCines = dt.getTodosCines();
    
    List<String> generos=new ArrayList();
    
    List<Film> listaDePeliculas = dt.getAllFilms();
    
    boolean añadir=true;
    
    for(int i=0;i<listaDePeliculas.size();i++){
        String aux=listaDePeliculas.get(i).getGenre();
        
        for(int j=0;j<generos.size();j++){
            if(aux.equals(generos.get(j))){
               añadir=false;
            }
            
        }
        if(añadir){
            generos.add(aux);
        }else añadir=true;
        
    
    }

    
%>
<label for="cines">Označi žanr</label>
        <!--
        <select name="cinema" id="cinema">
            <option value=""></option>
            <//% for (Cinema cine : listaDeCines) { %>
            <option value="<//%=cine.getCinemaName()%>"><//%=cine.getCinemaName()%></option>
            <//% } %>
        </select>
        -->
        
        <select name="generos" id="generos">
            <option value=""></option>
            <% for (String gen : generos) { %>
            
            <option value="<%=gen%>"><%=gen%></option>
            
            <% } %>
        </select>
        
        <br>
        

        <input type="submit" value="Vidi filmove po ovim kriterijima">
    </form>

    <!-- Prikazi filmove po žanru -->
    <ul>
        <% 

        String com=(String)request.getAttribute("com");
        if(com!=null){
             if(!com.equals("")){
        %>
            
        <font color="green">Uspješno dodan komentar!!</font>
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
        <th>Ostalo</th>
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
%>

<br>

      <%

} else   
    
    
    {
        %>
            <li>Nema dostupnih filmova po ovom žanru</li>
        <% 
        }
        %>
    </ul>
    
       <%
    String reg="userHome.jsp";   

    
    if(loggedInUser==null) reg="index.html";

    %>
    
    <br><br>
    <p class="back-link"><a href=adminHome.jsp>Povratak</a></p>
    <%
    }else{
%>
    
<center>
<font>Nisi administrator, vrati se na početnu stranicu i ulogiraj se</font>
    <a href="index.html" class="button">Povratak</a>
    
</center>  
    <%


}
}else{
%>
    
<center>
<font>Nisi korisnik, vrati se na početnu stranicu i ulogiraj se</font>
    <a href="index.html" class="button">Povratak</a>
    
</center>   
    <%

}


%>
</center> 
</body>
</html>
