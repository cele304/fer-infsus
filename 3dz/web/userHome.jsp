


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Home</title>
    <link rel="stylesheet" href="style2.css">
</head>
<body>
    <header>
        <% 
            String loggedInUser = (String) session.getAttribute("loggedInUser");
             String admin=(String) session.getAttribute("admin");
            if(admin!=null){
            
            
            
            
        %>
        <!-- Display user information -->
        <% if (loggedInUser != null) { %>
            <p style="float: left;">Dobrodošao: <%= loggedInUser %></p>
        <% } %>
        <a href="logout.jsp" class="button">Izlaz</a>
    </header>
    
    
    <section>
    <h2>Dobrodošao, <%= loggedInUser %></h2>
    <h3>Što želiš danas učiniti?</h3>
    <ul>
        <!-- Poveznica na rezervaciju karte -->
        <li><a href="user_reserveTicket.jsp" class="button">Rezervirati kartu</a></li>

        <!-- Poveznica na pregled rezervacija -->
        <li><a href="user_viewReservations.jsp" class="button">Moje rezervacije</a></li>
        

        
        <!-- Poveznica na pregled filmova po teatru -->
        <li><a href="user_viewFilm.jsp" class="button">Vidi filmove</a></li>
       
        <!-- Poveznica na pregled kina -->
        <li><a href="user_viewCities.jsp" class="button">Vidi kina i gradove</a></li>
        
        <!-- Poveznica na pregled svih komentara ovisno o useru, filmu -->
        <li><a href="user_viewComments.jsp" class="button">Vidi sve komentare</a></li>
        
        <!-- Poveznica na pregled mojih komentara i filmova za taj komentar -->
        <li><a href="user_viewMyComments.jsp" class="button">Vidi moje komentare</a></li>
        
        <!--master detail -->
        <li><a href="user_master_detail.jsp" class="button">Moji podaci</a></li>
        
    </ul>
    </section>
    
     <p class="error-message"><c:if test="${not empty error_message}">${error_message}</c:if></p>

                    <%

}else{
%>
    
<center>
<font>Nisi korisnik, vrati se na početnu stranicu i registriraj se</font>
    <a href="index.html" class="button">Povratak</a>
    
</center>   
    <%

}


%>

    <footer>
        &copy; 2024 User Home
    </footer>
    
    
</body>
</html>
