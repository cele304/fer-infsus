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
        <title>Kupi ulaznicu</title>
        <link rel="stylesheet" href="style5.css">
    </head>
    <body>

        <header>
            <h1>Kupi ulaznicu</h1>
        </header>

        <section>
            <%
            
            String admin=(String) session.getAttribute("admin");
            if(admin!=null){
            
            
           
            
            
            %>

            <h2>Označi film koji želiš gledati</h2>

            <!-- Dodaj salu form -->
            <form action="filmsbycinema" method="post">


                <%

                    String loggedInUser = (String) session.getAttribute("loggedInUser");

                    DataBase dt = new DataBase();

                    // SQL upit za dohvat svih sala
                    String sql = "SELECT * FROM CINEMA";

                    // Lista za pohranu rezultata
                    List<Cinema> listaDeCines = dt.getTodosCines();


                %>
                <label for="cines">Označi kino</label>
                <select name="cinemaName" id="cines">
                    <% for (Cinema cine : listaDeCines) {%>
                    <option value="<%=cine.getCinemaName()%>"><%=cine.getCinemaName()%></option>
                    <% } %>
                </select>
                <br>


                <input type="submit" value="Vidi filmove u ovom kinu">
            </form>



            <%
                List<Film> filmsByGenre = new ArrayList<>();
                filmsByGenre = (List<Film>) request.getAttribute("filmbyciema");
                if (filmsByGenre != null && !filmsByGenre.isEmpty()) {
            %>
            <h3>Lista filmova:</h3>

            <table border="1">
                <tr>
                    <th>Ime</th>
                    <th>Kino</th>
                    <th>Žanr</th>
                    <th>Nacionalnost</th>
                    <th>Trajanje</th>
                    <th>Godina</th>
                    <th>Redatelj</th>
                    <th>Glumci</th>
                    <th>Dobno ograničenje</th>

                </tr>
                <%
                    for (Film film : filmsByGenre) {
                %>
                <tr>
                    <td><%= film.getFilmName()%></td>
                    <td><%= film.getCinemaName()%></td>
                    <td><%= film.getGenre()%></td>
                    <td><%= film.getNationality()%></td>
                    <td><%= film.getDuration()%></td>
                    <td><%= film.getReleaseYear()%></td>
                    <td><%= film.getDirector()%></td>
                    <td><%= film.getActors()%></td>
                    <td><%= film.getClassification()%></td>

                </tr>
            </table>
            <%
                }

            %>

            <br>
            <form action="ShowSesionsFilmServlet" method="post">
                <label for="cines">Označi film</label>
                <select name="filmName" id="film">
                    <% for (Film film : filmsByGenre) {%>
                    <option value="<%=film.getFilmName()%>"><%=film.getFilmName()%></option>
                    <% }%>
                </select>
                <input type="text" id="cine" name="cinemaName" value="<%=filmsByGenre.get(0).getCinemaName()%>" readonly><br><br>
                <br>

                <input type="submit" value="Vidi sesije">
            </form>

            <%
                }
            %>

            <%
                List<Sesiones> sesions = (List<Sesiones>) request.getAttribute("sesionbyfilm");
                if (sesions != null && !sesions.isEmpty()) {
            %>

            <h3>Lista de Sesiones:</h3>

            <table border="1">
                <tr>
                    <th>Film</th>
                    <th>Kino</th>
                    <th>Dvorana</th>
                    <th>Datum</th>
                    <th>Vrijeme</th>


                </tr>
                <%
                    for (Sesiones sesion : sesions) {
                %>
                <tr>
                    <td><%= sesion.getPelicula()%></td>
                    <td><%= sesion.getNombreCine()%></td>
                    <td><%= sesion.getNombreSala()%></td>
                    <td><%= sesion.getDate()%></td>
                    <td><%= sesion.getHour()%></td>


                </tr>
            </table>
            <%
                }
            %>
            <br>
            <form action="ShowOccupiedServlet" method="post">
                <label for="cines">Označi film</label>
                <select name="id_sesion" id="id">
                    <% for (Sesiones ses : sesions) {%>
                    <option value="<%=ses.getId()%>"><%="Sala: "+ses.getNombreSala() + " | Dia: " + ses.getDate() + " | Hora: " + ses.getHour()%></option>
                    <% } %>
                </select>

                <br>

                <input type="submit" value="Vidi sjedala">
            </form>

            <%
                }
                List<int[]> ocup = (List<int[]>) request.getAttribute("ocuppied");
                int[] tamaño = (int[]) request.getAttribute("t_sala");
                Sesiones sesion = (Sesiones) request.getAttribute("sasion");

                String color;
                String estado;
                boolean existe = false;
                List<int[]> libres = new ArrayList<>();
                if (tamaño != null) {
            %>
            <h3>Sjedala</h3>

            <table border="1">
                <tr>
                    <th>0</th>
                        <%
                            for (int i = 1; i <= tamaño[0]; i++) {
                        %>
                    <th><%=i%></th>
                        <%
                            }
                        %>
                </tr> 
                <%
                    for (int i = 1; i <= tamaño[1]; i++) {

                %>
                <tr>
                    <th><%=i%></th>
                        <%
                            for (int j = 1; j <= tamaño[0]; j++) {
                                int[] aux = new int[2];
                                aux[0] = j;
                                aux[1] = i;

                                for (int[] alt : ocup) {
                                    if (alt[0] == aux[0] && alt[1] == aux[1]) {
                                        existe = true;

                                    }
                                }
                                if (existe) {
                                    color = "red";
                                    estado = "Zauzeto";
                                    existe = false;

                                } else {
                                    color = "lightblue";
                                    estado = "Slobodno";
                                    libres.add(aux);

                                }


                        %>
                    <td style="background-color: <%= color%>;"><%= estado%></td>

                    <%
                        }
                    %>
                </tr>
                <%
                    }


                %>


            </table>

            <form action="ComprarTicketServlet" method="post">
                <label for="cines">Oznaci svoje sjedalo</label><br>
                <select name="asiento" id="asiento">
                    <% for (int[] as : libres) {%>
                    <option value="<%=as[0] + "|" + as[1]%>"><%="Stupac: " + as[0] + "| Red: " + as[1]%></option>
                    <% }%>
                </select><br><br>
                <label> Podaci rezervacije:</label><br>
                Korisničko ime: 
                <input type="text" id="user" name="user" value="<%=loggedInUser%>" readonly><br><br>




                <tr>
                <label> Informacije o sesiji: </label>
                <input type="text" id="sesion" name="sesion" value="<%=sesion.getId()%>" readonly><br>
                <table border="1">
                    <tr>
                        <th>Film</th>
                        <th>Kino</th>
                        <th>Dvorana</th>
                        <th>Datum</th>
                        <th>Vrijeme</th>


                    </tr>

                    <tr>
                        <td><%= sesion.getPelicula()%></td>
                        <td><%= sesion.getNombreCine()%></td>
                        <td><%= sesion.getNombreSala()%></td>
                        <td><%= sesion.getDate()%></td>
                        <td><%= sesion.getHour()%></td>


                    </tr>
                </table>

   <br>
                <label> Informacije o plaćanju: </label><br><!-- comment -->
                <label> Broj kreditne kartice </label>
                <input type="number" id="credit" name="credit"><br><br>
                <input type="submit" value="Kupi kartu">
            </form>

            <%}
                String comprobar = (String) request.getAttribute("correcto");
                int refe = 0;
                if (request.getAttribute("reseerva") != null) {
                    refe = (int) request.getAttribute("reseerva");
                }

                if (comprobar != null) {
                    if (comprobar.equals("no")) {
            %>

            <br>
         
            <font color="red"> Pogrešne informacije o plaćanju, molimo pokušajte ponovo</font>


            <%
            } else {
            %>
            <br><!-- comment -->

            <font color="green"> Uspješno si rezervirao kartu s referencom: <%=refe%></font>

            <br>
            <p><a href="user_viewReservations.jsp">Moje rezervacije</a></p>

            <%
                    }

                }

            %>



            <c:if test="${not empty error_message}">
                <p class="error-message">${error_message}</p>
            </c:if>

            <!-- Back to user home button -->
            <button onclick="location.href = 'userHome.jsp'" class="button">Povratak na početnu stranicu</button>
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
        </section>

    </body>
</html>