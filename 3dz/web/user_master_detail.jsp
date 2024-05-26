

<%@page import="Model.DataBase"%>
<%@page import="Model.Comment"%>
<%@page import="Model.Sesiones"%>
<%@page import="Model.Ticket"%>
<%@page import="Model.Film"%>
<%@ page import="Model.Reservation" %>
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
        <title>Moji podaci</title>
        <link rel="stylesheet" href="style6.css">
    </head>
    <body>
       
        
        
        
            <header>
        <h2>Moji podaci</h2>
        <% 
            String loggedInUser = (String) session.getAttribute("loggedInUser");
            String userFirstName = (String) session.getAttribute("userFirstName");
            String userLastName = (String) session.getAttribute("userLastName");
            String email = (String) session.getAttribute("email");
            String admin = (String) session.getAttribute("admin");
        %>
        <div class="header-content">
            <% if (admin != null && loggedInUser != null) { %>
                <div class="header-table-container">
                    <table class="header-table">
                        <tr>
                            <th>Korisničko ime</th>
                            <td class="novo-boja"><%= loggedInUser %></td>
                        </tr>
                        <tr>
                            <th>Ime</th>
                            <td class="novo-boja"><%= userFirstName %></td>
                        </tr>
                        <tr>
                            <th>Prezime</th>
                            <td class="novo-boja"><%= userLastName %></td>
                        </tr>
                        <tr>
                            <th>Email</th>
                            <td class="novo-boja"><%= email %></td>
                        </tr>
                    </table>
                </div>
                <!--<a href="logout.jsp" class="button">Izlaz</a>-->
            <% } else { %>
                <p>Dobrodošli, gost!</p>
                <a href="login.jsp" class="button">Prijava</a>
            <% } %>
        </div>
    </header>
        
        <br><br><br><br>

        
        
        
        

    
  
    




    
    
         
        <%
    // Postavljanje podataka za povezivanje s bazom podataka
    
     //String admin=(String) session.getAttribute("admin");
            if(admin!=null){
            
    
    //String loggedInUser = (String) session.getAttribute("loggedInUser");
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
    
    <br><br><br><br>
        
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
    
    
    
    
    
    
    
    
    
    
    <h2>Moje rezervacije</h2>

    <% 

    DataBase dt=new DataBase();
 
    String user = (String) session.getAttribute("loggedInUser");
    // SQL upit za dohvat svih filmova

    // Lista za pohranu rezultata
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
    
     <p class="back-link"><a href="userHome.jsp">Povratak</a></p>
     
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
