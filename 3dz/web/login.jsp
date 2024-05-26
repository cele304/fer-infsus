


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Prijava</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>

<div class="container">
    <h2>Prijava</h2>
    <form action="login" method="post" class="login-form">
        Korisničko ime: <input type="text" name="username" required><br>
        Lozinka: <input type="password" name="password" required><br>
        <input type="submit" value="Potvrdi">
    </form>

    <!-- Prikaz poruke nakon prijave -->
    <c:if test="${not empty requestScope.porukaLogin}">
        <p class="login-message">${requestScope.porukaLogin}</p>
    </c:if>

    <!-- Prikaz greške nakon neuspjele prijave -->
    <c:if test="${not empty requestScope.greskaLogin}">
        <p class="login-error">${requestScope.greskaLogin}</p>
    </c:if>
</div>
    
    

</body>
</html>

