/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author filip
 */
public class Reservation {
    
    private int reservationId, userId, ticketId ;

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getUserId() {
        return userId;
    }

    public int getTicketId() {
        return ticketId;
    }
    
    Sesiones sesion;

    public Sesiones getSesion() {
        return sesion;
    }

    public Ticket getTick() {
        return tick;
    }

    public void setSesion(Sesiones sesion) {
        this.sesion = sesion;
    }

    public void setTick(Ticket tick) {
        this.tick = tick;
    }
    Ticket tick;

    public Reservation(int reservationId) {
        this.reservationId = reservationId;
    }

    public Reservation() {
        
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    @Override
    public String toString() {
        return "Reservation{" + "reservationId=" + reservationId + '}';
    }

    public void setFilm(Film film) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
}
