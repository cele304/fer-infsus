/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;



import Controller.DBConnector;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.DriverManager;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author filip
 */
public class DataBase {
    
    private String url;
    private String username;
    private String password;
    

    public DataBase() {
        
        url = "jdbc:derby://localhost:1527/sample";
        username = "app";
        password = "app";
    }
    
    public List<Reservation> getTodasReservas(){
        List<Reservation> reservas=new ArrayList();
    try {
        // Učitavanje drivera i povezivanje s bazom podataka
        Class.forName("org.apache.derby.client.ClientAutoloadedDriver");
        Connection connection = DriverManager.getConnection(url, username, password);

        // Izvršavanje SQL upita
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM RESERVATION ");
        ResultSet resultSet = statement.executeQuery();

        // Iteriranje kroz rezultate i dodavanje filmova u listu
        while (resultSet.next()) {
            
            Reservation reservation=new Reservation();
            
            reservation.setReservationId(resultSet.getInt("reservation_id"));
            
            
            reservation.setUserId(resultSet.getInt("user_id"));
            reservation.setTicketId(resultSet.getInt("ticket_id"));
            
            PreparedStatement idticket=connection.prepareStatement("SELECT * FROM TICKET WHERE TICKET_ID="+resultSet.getInt("ticket_id"));
            ResultSet rest=idticket.executeQuery();
            rest.next();
            
            Ticket tick=new Ticket();
            tick.setTicketId(resultSet.getInt("ticket_id"));
            tick.setColumnNumber(rest.getInt("column_number"));
            tick.setRowNumber(rest.getInt("row_num"));
            
            reservation.setTick(tick); 
            
            PreparedStatement idses=connection.prepareStatement("SELECT * FROM SESIONES WHERE SESION_ID="+rest.getInt("sesion_id"));
            ResultSet reses=idses.executeQuery();
            reses.next();
            
            Sesiones ses=new Sesiones();
            ses.setId(reses.getInt("sesion_id"));
            ses.setCinema_id(reses.getInt("cinema_id"));
            ses.setFilm_id(reses.getInt("film_id"));
            ses.setSala_id(reses.getInt("sala_id"));
            ses.setDate(reses.getDate("fecha").toString());
            ses.setHour(reses.getTime("hora").toString());
            
            
            PreparedStatement idcin=connection.prepareStatement("SELECT * FROM CINEMA WHERE CINEMA_ID="+ses.getCinema_id());
            ResultSet rescine=idcin.executeQuery();
            rescine.next();
            ses.setNombreCine(rescine.getString("cinema_name"));
            
            rescine.close();
            idcin.close();
            
            PreparedStatement idp=connection.prepareStatement("SELECT * FROM FILM WHERE FILM_ID="+ses.getFilm_id());
            ResultSet resp=idp.executeQuery();
            resp.next();
            ses.setPelicula(resp.getString("film_name"));
            
            resp.close();
            idp.close();
 

            PreparedStatement mandato=connection.prepareStatement("SELECT * FROM SALA WHERE SALA_ID="+ses.getSala_id());
            ResultSet rescin=mandato.executeQuery();
            rescin.next();
            ses.setNombreSala(rescin.getString("theater_name"));
            
            
            
            reservation.setSesion(ses);
            
            rescin.close();
            mandato.close();
            
            rest.close();
            idticket.close();
            
            reses.close();
            idses.close();
            
            reservas.add(reservation);
            
            
        }
       // Zatvaranje resursa
        resultSet.close();
        statement.close();
        connection.close();

    } catch (Exception e) {
        e.printStackTrace();
    }
    
    return reservas;  
        
    }
    
   public List<Cinema> getTodosCines(){
        
    String sqlCinemas = "SELECT * FROM CINEMA JOIN CITY ON cinema.city_id=city.city_id";
    
    

    // Lista za pohranu rezultata
    List<Cinema> listaDeCines = new ArrayList<>();
    

    try {
        // Učitavanje drivera i povezivanje s bazom podataka
        Class.forName("org.apache.derby.client.ClientAutoloadedDriver");
        Connection connection = DriverManager.getConnection(url, username, password);

        // Izvršavanje SQL upita za kino dvorane
        PreparedStatement statementCinemas = connection.prepareStatement(sqlCinemas);
        ResultSet resultSetCinemas = statementCinemas.executeQuery();



    // Iterating through the result set and adding cinema instances to the list
    while (resultSetCinemas.next()) {
        Cinema cinema = new Cinema();
        cinema.setCinemaId(resultSetCinemas.getInt("cinema_id"));
        cinema.setCinemaName(resultSetCinemas.getString("cinema_name"));
       cinema.setCityName(resultSetCinemas.getString("city_name"));
        // Set other attributes if needed

        listaDeCines.add(cinema);
    }
    
    resultSetCinemas.close();
    statementCinemas.close();
    connection.close();


    }catch(Exception e){
        System.out.println("Error: "+e.toString());
    }
    return listaDeCines;
    
    }
   
   public List<Film> getAllFilms(){
       // SQL upit za dohvat svih filmova
            String sql2 = "SELECT * FROM FILM ORDER BY CINEMA_ID";

            // Lista za pohranu rezultata
            List<Film> listaDeFilms = new ArrayList<>();

            try {
                // Učitavanje drivera i povezivanje s bazom podataka
                Class.forName("org.apache.derby.client.ClientAutoloadedDriver");
                Connection connection = DriverManager.getConnection(url, username, password);

                // Izvršavanje SQL upita
                PreparedStatement statement = connection.prepareStatement(sql2);
               
                ResultSet resultSet = statement.executeQuery();

                // Iteriranje kroz rezultate i dodavanje filmova u listu
                while (resultSet.next()) {
                    Film film = new Film();

                    film.setFilmId(resultSet.getInt("film_id"));
                    System.out.println(resultSet.getInt("film_id"));
                    film.setFilmName(resultSet.getString("film_name"));
                    film.setSynopsis(resultSet.getString("synopsis"));
                    film.setFilmPage(resultSet.getString("film_page"));
                    film.setOriginalTitle(resultSet.getString("original_title"));
                    film.setGenre(resultSet.getString("genre"));
                    film.setNationality(resultSet.getString("nationality"));
                    film.setDuration(resultSet.getInt("duration"));
                    film.setReleaseYear(resultSet.getInt("release_year"));
                    film.setDistributor(resultSet.getString("distributor"));
                    film.setDirector(resultSet.getString("director"));
                    film.setActors(resultSet.getString("actors"));
                    film.setClassification(resultSet.getString("classification"));
                    film.setOtherData(resultSet.getString("other_data"));

                    listaDeFilms.add(film);
                }


                // Zatvaranje resursa
                statement.close();
                resultSet.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return listaDeFilms;
   }
   
   public List<Sala> getAllSalas(){
           // SQL upit za dohvat svih sala (Prilagodite upit prema vašoj bazi podataka)
    // SQL upit za dohvat svih sala
    String sql2 = "SELECT * FROM SALA ORDER BY CINEMA_ID";

    // Lista za pohranu rezultata
    List<Sala> listaDeSalas = new ArrayList<>();

    try {
        // Učitavanje drivera i povezivanje s bazom podataka
        Class.forName("org.apache.derby.client.ClientAutoloadedDriver");
        Connection connection = DriverManager.getConnection(url, username, password);

        // Izvršavanje SQL upita
        PreparedStatement statement = connection.prepareStatement(sql2);
        PreparedStatement mandato;
        
        ResultSet resultSet = statement.executeQuery();
        
        ResultSet res;

        // Iteriranje kroz rezultate i dodavanje filmova u listu
        while (resultSet.next()) {
            
            Sala sala = new Sala();
            sala.setCinema_id(resultSet.getInt("cinema_id"));
            
            mandato=connection.prepareStatement("SELECT * FROM CINEMA WHERE CINEMA_ID="+resultSet.getInt("cinema_id"));
            res=mandato.executeQuery();
            res.next();
            sala.setSalaId(resultSet.getInt("sala_id"));
            sala.setRowsCount(resultSet.getInt("rows_count"));
            sala.setColumnsCount(resultSet.getInt("columns_count"));
            sala.setTheaterName(resultSet.getString("theater_name"));
            sala.setCinemaName(res.getString("cinema_name"));
            res.close();
            mandato.close();
            
            listaDeSalas.add(sala);
        }

        // Zatvaranje resursa

        statement.close();
        resultSet.close();
        
        connection.close();

    } catch (Exception e) {
        e.printStackTrace();
    } 
    return listaDeSalas;
   }
   
   public List<Sesiones> getAllSesions() throws ClassNotFoundException{
       List<Sesiones>sesiones=new ArrayList();
       
        String sql = "SELECT * FROM SESIONES";

        try (Connection connection = DBConnector.getConnection()) {
            
           

            PreparedStatement statement = connection.prepareStatement(sql);
            
            PreparedStatement mandato;
            ResultSet response;
            
           
            
            
            

            try (ResultSet resultSet = statement.executeQuery()) {
                while(resultSet.next()){
                    
                
                    
                Sesiones ses=new Sesiones();
                ses.setId(resultSet.getInt("sesion_id"));
                ses.setCinema_id(resultSet.getInt("cinema_id"));
                ses.setFilm_id(resultSet.getInt("film_id"));
                ses.setSala_id(resultSet.getInt("sala_id"));
                ses.setDate(resultSet.getDate("fecha").toString());
                ses.setHour(resultSet.getTime("hora").toString());
                
                mandato=connection.prepareStatement("SELECT * FROM CINEMA WHERE CINEMA_ID="+ses.getCinema_id());
                response=mandato.executeQuery();
                response.next();
                ses.setNombreCine(response.getString("cinema_name"));
                response.close();
                mandato.close();
                
                mandato=connection.prepareStatement("SELECT * FROM FILM WHERE FILM_ID="+ses.getFilm_id());
                response=mandato.executeQuery();
                response.next();
                ses.setPelicula(response.getString("film_name"));
                response.close();
                mandato.close();

                mandato=connection.prepareStatement("SELECT * FROM SALA WHERE SALA_ID="+ses.getSala_id());
                response=mandato.executeQuery();
                response.next();
                ses.setNombreSala(response.getString("theater_name"));
                response.close();
                mandato.close();
                    
                sesiones.add(ses);
                    
                } 
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sesiones;
   }
   
   public List<User> getAllUsers() throws ClassNotFoundException{
       
       List<User> users = new ArrayList<>();
       
        String sqlus = "SELECT * FROM USERS";
            
        Class.forName("org.apache.derby.client.ClientAutoloadedDriver");
       
        try (Connection connection = DriverManager.getConnection(url, username, password);) {
            
           

            PreparedStatement statement = connection.prepareStatement(sqlus);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    
                    
                    User us=new User();
                    us.setUserId(resultSet.getInt("user_id"));
                    us.setUsername(resultSet.getString("username"));
                    
                  users.add(us);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
       
   }
   
   public List<Comment> getAllComentarios(){
       String sql = "SELECT * FROM COMMENT";
    

    // Lista za pohranu rezultata
    
    List<Comment> coms =new ArrayList<>();
    
    try {
        // Učitavanje drivera i povezivanje s bazom podataka
        Class.forName("org.apache.derby.client.ClientAutoloadedDriver");
        Connection connection = DriverManager.getConnection(url, username, password);
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        // Iteriranje kroz rezultate i dodavanje filmova u listu
        
 
        while (resultSet.next()) {
            Comment comment=new Comment();
            
            PreparedStatement idcin=connection.prepareStatement("SELECT * FROM USERS WHERE USER_ID="+resultSet.getInt("USER_ID"));
            ResultSet rescine=idcin.executeQuery();
            rescine.next();
            comment.setUser(rescine.getString("USERNAME"));
            
            PreparedStatement idp=connection.prepareStatement("SELECT * FROM FILM WHERE FILM_ID="+resultSet.getInt("FILM_ID"));
            ResultSet resp=idp.executeQuery();
            resp.next();
            comment.setFilm(resp.getString("FILM_NAME"));
            
            comment.setCommentText(resultSet.getString("comment_text"));
            
            resp.close();
            idp.close();
            
            coms.add(comment);
            
        }


        // Zatvaranje resursa
        resultSet.close();
        statement.close();
        connection.close();

    } catch (Exception e) {
        e.printStackTrace();
    }
    
    
    
    return coms;
    
   }
    
   
   public List<Comment> getCommentFilm(String filtro){
        // Postavljanje podataka za povezivanje s bazom podataka
   
    
    
    // SQL upit za dohvat svih sala
    
    String sql = "SELECT * FROM COMMENT";
    

    // Lista za pohranu rezultata
    
    List<Comment> coms =new ArrayList<>();
    

    try {
        // Učitavanje drivera i povezivanje s bazom podataka
        Class.forName("org.apache.derby.client.ClientAutoloadedDriver");
        Connection connection = DriverManager.getConnection(url, username, password);

        sql+=" WHERE FILM_ID=(SELECT FILM_ID FROM FILM WHERE FILM_NAME='"+filtro+"')";
        
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        // Iteriranje kroz rezultate i dodavanje filmova u listu
        
 
        while (resultSet.next()) {
            Comment comment=new Comment();
            
            PreparedStatement idcin=connection.prepareStatement("SELECT * FROM USERS WHERE USER_ID="+resultSet.getInt("USER_ID"));
            ResultSet rescine=idcin.executeQuery();
            rescine.next();
            comment.setUser(rescine.getString("USERNAME"));
            
            PreparedStatement idp=connection.prepareStatement("SELECT * FROM FILM WHERE FILM_ID="+resultSet.getInt("FILM_ID"));
            ResultSet resp=idp.executeQuery();
            resp.next();
            comment.setFilm(resp.getString("FILM_NAME"));
            
            comment.setCommentText(resultSet.getString("comment_text"));
            
            resp.close();
            idp.close();
            
            coms.add(comment);
            
        }


        // Zatvaranje resursa
        resultSet.close();
        statement.close();
        connection.close();

    } catch (Exception e) {
        e.printStackTrace();
    }
    
       return coms;
   }
   
      public List<Comment> getAllComentariosUser(String user){
          
          
       String sql = "SELECT * FROM COMMENT WHERE USER_ID=(SELECT USER_ID FROM USERS WHERE USERNAME='"+user+"')";
    

    // Lista za pohranu rezultata
    
    List<Comment> coms =new ArrayList<>();
    
    try {
        // Učitavanje drivera i povezivanje s bazom podataka
        Class.forName("org.apache.derby.client.ClientAutoloadedDriver");
        Connection connection = DriverManager.getConnection(url, username, password);
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        // Iteriranje kroz rezultate i dodavanje filmova u listu
        
 
        while (resultSet.next()) {
            Comment comment=new Comment();
            
            PreparedStatement idcin=connection.prepareStatement("SELECT * FROM USERS WHERE USER_ID="+resultSet.getInt("USER_ID"));
            ResultSet rescine=idcin.executeQuery();
            rescine.next();
            comment.setUser(rescine.getString("USERNAME"));
            
            PreparedStatement idp=connection.prepareStatement("SELECT * FROM FILM WHERE FILM_ID="+resultSet.getInt("FILM_ID"));
            ResultSet resp=idp.executeQuery();
            resp.next();
            comment.setFilm(resp.getString("FILM_NAME"));
            
            comment.setCommentText(resultSet.getString("comment_text"));
            
            resp.close();
            idp.close();
            
            coms.add(comment);
            
        }


        // Zatvaranje resursa
        resultSet.close();
        statement.close();
        connection.close();

    } catch (Exception e) {
        e.printStackTrace();
    }
    
    
    
    return coms;
    
   }
    
   
   public List<Comment> getCommentFilmUser(String filtro, String user){
        // Postavljanje podataka za povezivanje s bazom podataka
   
    
    
    // SQL upit za dohvat svih sala
    
    String sql ="SELECT * FROM COMMENT WHERE USER_ID=(SELECT USER_ID FROM USERS WHERE USERNAME='"+user+"')";
    

    // Lista za pohranu rezultata
    
    List<Comment> coms =new ArrayList<>();
    

    try {
        // Učitavanje drivera i povezivanje s bazom podataka
        Class.forName("org.apache.derby.client.ClientAutoloadedDriver");
        Connection connection = DriverManager.getConnection(url, username, password);

        sql+=" AND FILM_ID=(SELECT FILM_ID FROM FILM WHERE FILM_NAME='"+filtro+"')";
        
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();

        // Iteriranje kroz rezultate i dodavanje filmova u listu
        
 
        while (resultSet.next()) {
            Comment comment=new Comment();
            
            PreparedStatement idcin=connection.prepareStatement("SELECT * FROM USERS WHERE USER_ID="+resultSet.getInt("USER_ID"));
            ResultSet rescine=idcin.executeQuery();
            rescine.next();
            comment.setUser(rescine.getString("USERNAME"));
            
            PreparedStatement idp=connection.prepareStatement("SELECT * FROM FILM WHERE FILM_ID="+resultSet.getInt("FILM_ID"));
            ResultSet resp=idp.executeQuery();
            resp.next();
            comment.setFilm(resp.getString("FILM_NAME"));
            
            comment.setCommentText(resultSet.getString("comment_text"));
            
            resp.close();
            idp.close();
            
            coms.add(comment);
            
        }


        // Zatvaranje resursa
        resultSet.close();
        statement.close();
        connection.close();

    } catch (Exception e) {
        e.printStackTrace();
    }
    
       return coms;
   }
   
   public List<Reservation> getAllReservUser(String user){
       
       
       // Lista za pohranu rezultata
    
    List<Reservation> reservas=new ArrayList<>();

    try {
        // Učitavanje drivera i povezivanje s bazom podataka
        Class.forName("org.apache.derby.client.ClientAutoloadedDriver");
        Connection connection = DriverManager.getConnection(url, username, password);
        
        PreparedStatement idUser=connection.prepareStatement("SELECT * FROM USERS WHERE USERNAME='"+user+"'");
        ResultSet res=idUser.executeQuery();
        res.next();
        int userid=res.getInt("user_id");
        
        res.close();
        idUser.close();
        // Izvršavanje SQL upita
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM RESERVATION WHERE USER_ID="+userid);
        ResultSet resultSet = statement.executeQuery();

        // Iteriranje kroz rezultate i dodavanje filmova u listu
        while (resultSet.next()) {
            
            Reservation reservation=new Reservation();
            
            reservation.setReservationId(resultSet.getInt("reservation_id"));
            
            PreparedStatement idticket=connection.prepareStatement("SELECT * FROM TICKET WHERE TICKET_ID="+resultSet.getInt("ticket_id"));
            ResultSet rest=idticket.executeQuery();
            rest.next();
            
            Ticket tick=new Ticket();
            
            tick.setColumnNumber(rest.getInt("column_number"));
            tick.setRowNumber(rest.getInt("row_num"));
            
            reservation.setTick(tick); 
            
            PreparedStatement idses=connection.prepareStatement("SELECT * FROM SESIONES WHERE SESION_ID="+rest.getInt("sesion_id"));
            ResultSet reses=idses.executeQuery();
            reses.next();
            
            Sesiones ses=new Sesiones();
            ses.setId(reses.getInt("sesion_id"));
            ses.setCinema_id(reses.getInt("cinema_id"));
            ses.setFilm_id(reses.getInt("film_id"));
            ses.setSala_id(reses.getInt("sala_id"));
            ses.setDate(reses.getDate("fecha").toString());
            ses.setHour(reses.getTime("hora").toString());
            
            
            PreparedStatement idcin=connection.prepareStatement("SELECT * FROM CINEMA WHERE CINEMA_ID="+ses.getCinema_id());
            ResultSet rescine=idcin.executeQuery();
            rescine.next();
            ses.setNombreCine(rescine.getString("cinema_name"));
            
            rescine.close();
            idcin.close();
            
            PreparedStatement idp=connection.prepareStatement("SELECT * FROM FILM WHERE FILM_ID="+ses.getFilm_id());
            ResultSet resp=idp.executeQuery();
            resp.next();
            ses.setPelicula(resp.getString("film_name"));
            
            resp.close();
            idp.close();
 

            PreparedStatement mandato=connection.prepareStatement("SELECT * FROM SALA WHERE SALA_ID="+ses.getSala_id());
            ResultSet rescin=mandato.executeQuery();
            rescin.next();
            ses.setNombreSala(rescin.getString("theater_name"));
            
            
            
            reservation.setSesion(ses);
            
            rescin.close();
            mandato.close();
            
            rest.close();
            idticket.close();
            
            reses.close();
            idses.close();
            
            reservas.add(reservation);
            
            
        }


        // Zatvaranje resursa
        resultSet.close();
        statement.close();
        connection.close();

    } catch (Exception e) {
        e.printStackTrace();
    }
    
    return reservas;
   }
   
   
  
   
   public List<Cinema> getCinemasByName(String cinemaName) {
        // SQL query to find cinemas by name with a join to the city table
        String sql = "SELECT cinema.cinema_id, cinema.cinema_name, city.city_name FROM CINEMA JOIN CITY ON cinema.city_id = city.city_id WHERE cinema.cinema_name LIKE ?";

        // List to store the results
        List<Cinema> listaDeCines = new ArrayList<>();

        try {
            // Load driver and connect to the database
            Class.forName("org.apache.derby.client.ClientAutoloadedDriver");
            Connection connection = DriverManager.getConnection(url, username, password);

            // Prepare and execute the SQL statement
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + cinemaName + "%"); // Set cinema name parameter with wildcard characters for substring matching
            ResultSet resultSet = statement.executeQuery();

            // Iterate through the result set and add cinema instances to the list
            while (resultSet.next()) {
                Cinema cinema = new Cinema();
                cinema.setCinemaId(resultSet.getInt("cinema_id"));
                cinema.setCinemaName(resultSet.getString("cinema_name"));
                cinema.setCityName(resultSet.getString("city_name"));
                // Add other attributes if necessary

                listaDeCines.add(cinema);
            }

            // Close all resources
            resultSet.close();
            statement.close();
            connection.close();

        } catch (Exception e) {
            System.out.println("Error in getCinemasByName: " + e.toString());
        }

        return listaDeCines;
    }

}