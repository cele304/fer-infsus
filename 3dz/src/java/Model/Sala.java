/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author filip
 */
public class Sala {
    
    private int salaId, rowsCount, columnsCount, cinema_id;

    public void setCinema_id(int cinema_id) {
        this.cinema_id = cinema_id;
    }

    public int getCinema_id() {
        return cinema_id;
    }
    private String theaterName,cinemaName;

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public Sala() {
        
    }

    public int getSalaId() {
        return salaId;
    }

    public void setSalaId(int salaId) {
        this.salaId = salaId;
    }

    public int getRowsCount() {
        return rowsCount;
    }

    public void setRowsCount(int rowsCount) {
        this.rowsCount = rowsCount;
    }

    public int getColumnsCount() {
        return columnsCount;
    }

    public void setColumnsCount(int columnsCount) {
        this.columnsCount = columnsCount;
    }

    public String getTheaterName() {
        return theaterName;
    }

    public void setTheaterName(String theaterName) {
        this.theaterName = theaterName;
    }

    @Override
    public String toString() {
        return "Sala{" + "salaId=" + salaId + ", rowsCount=" + rowsCount + ", columnsCount=" + columnsCount + ", theaterName=" + theaterName + '}';
    }
    
    
    
}
