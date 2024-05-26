




<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registracija</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>

    <div class="container">
        <h2>Registracija</h2>
        <form action="registration" method="post" class="registration-form">
            <label for="firstName">Ime:</label>
            <input type="text" name="firstName" id="firstname" required>

            <label for="lastName">Prezime:</label>
            <input type="text" name="lastName" id="lastname" required>

            <label for="username">Korisničko ime:</label>
            <input type="text" name="username" id="username" required>

            <label for="password">Lozinka:</label>
            <input type="password" name="password" id="password" required>

            <label for="email">Email:</label>
            <input type="email" name="email" id="email" required>

            <input type="submit" value="Registriraj se">
        </form>

        <!-- Prikaz poruke nakon registracije -->
        <% if (request.getAttribute("porukaRegistracije") != null) { %>
            <p class="registration-message"><%= request.getAttribute("porukaRegistracije") %></p>
        <% } %>
    </div>

</body>


<script>
document.addEventListener("DOMContentLoaded", function() {
    const form = document.querySelector(".registration-form");
    form.addEventListener("submit", function(event) {
        const firstName = document.getElementById('firstname').value;
        const lastName = document.getElementById('lastname').value;
        const username = document.getElementById('username').value;
        const password = document.getElementById('password').value;
        const email = document.getElementById('email').value;

        if (!/^[a-zA-Z]{2,}$/.test(firstName) || !/^[a-zA-Z]{2,}$/.test(lastName)) {
            alert('Ime i prezime moraju sadržavati samo slova i biti duži od jednog znaka.');
            event.preventDefault();
            return false;
        }

        if (!/^\w{5,}$/.test(username)) {
            alert('Korisničko ime mora biti duže od 4 znaka i može sadržavati samo slova, brojeve i donje crte.');
            event.preventDefault();
            return false;
        }

        if (!/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$/.test(password)) {
            alert('Lozinka mora biti duža od 7 znakova i sadržavati barem jedno veliko slovo, jedno malo slovo i jedan broj.');
            event.preventDefault();
            return false;
        }

        if (!/^\S+@\S+\.\S+$/.test(email)) {
            alert('Unesite valjanu e-mail adresu.');
            event.preventDefault();
            return false;
        }
    });
});
</script>



</html>
