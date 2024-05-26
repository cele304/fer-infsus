<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Home</title>
    <link rel="stylesheet" href="style2.css">
</head>
<body>
    <header>
        <% 
            String loggedInUser = (String) session.getAttribute("loggedInUser");
            String admin=(String) session.getAttribute("admin");
            if(admin!=null){
            
            
            if(admin.equals("y")){
            
            
            
        %>
        <!-- Display user information -->
        <% if (loggedInUser != null) { %>
            <p style="float: left;">Dobrodošao: <%= loggedInUser %></p>
        <% } %>
        <a href="logout.jsp" class="button">Izlaz</a>
    </header>

    <section>
        <h2>Dobrodošao, admin!</h2>
        <h3>Administracija kina:</h3>
        <ul>
            <li><a href="admin_gestor_peliculas.jsp" class="button">Upravljanje filmovima</a></li>
            <li><a href="admin_gestor_salas.jsp" class="button">Upravljanje salama</a></li>
            <li><a href="admin_gestor_entradas.jsp" class="button">Upravljanje ulaznicama</a></li>
            <li><a href="admin_gestor_reservas.jsp" class="button">Upravljanje rezervacijama</a></li>
            <li><a href="admin_gestor_sesiones.jsp" class="button">Upravljanje sesijama</a></li>
            <li><a href="admin_gestor_informes.jsp" class="button">Upravljanje informacijama</a></li>
            <!--<li><a href="admin_master_detail.jsp" class="button">Master Detail</a></li>-->
            <li><a href="userHome.jsp" class="button">Postani obični korisnik</a></li>

        </ul>
    </section>
<%
    }else{
%>
    
<center>
<font>Nisi administrator, vrati se na početak i započni sesiju kao administrator</font>
    <a href="index.html" class="button">Povratak na početnu stranicu</a>
    
</center>  
    <%


}
}else{
%>
    
<center>
<font>Nisi korisnik, vrati se na početak i registriraj se ili ulogiraj se</font>
    <a href="index.html" class="button">Povratak na početnu stranicu</a>
    
</center>   
    <%

}


%>
    <footer>
        &copy; 2024 Admin Home
    </footer>
</body>
</html>
