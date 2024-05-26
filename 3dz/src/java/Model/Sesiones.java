/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author filip
 */
public class Sesiones {
    int id, cinema_id, film_id,sala_id;
    String date, hour,nombreSala,nombreCine, pelicula;

    public void setNombreCine(String nombreCine) {
        this.nombreCine = nombreCine;
    }

    public void setPelicula(String pelicula) {
        this.pelicula = pelicula;
    }

    public String getNombreCine() {
        return nombreCine;
    }

    public String getPelicula() {
        return pelicula;
    }

    public void setNombreSala(String nombreSala) {
        this.nombreSala = nombreSala;
    }

    public String getNombreSala() {
        return nombreSala;
    }

    public Sesiones() {
    }

    public int getId() {
        return id;
    }

    public int getCinema_id() {
        return cinema_id;
    }

    public int getFilm_id() {
        return film_id;
    }

    public int getSala_id() {
        return sala_id;
    }

    public String getDate() {
        return date;
    }

    public String getHour() {
        return hour;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCinema_id(int cinema_id) {
        this.cinema_id = cinema_id;
    }

    public void setFilm_id(int film_id) {
        this.film_id = film_id;
    }

    public void setSala_id(int sala_id) {
        this.sala_id = sala_id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }
    
    
}
