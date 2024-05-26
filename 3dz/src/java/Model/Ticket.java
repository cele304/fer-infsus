/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author filip
 */

import java.util.Date;

public class Ticket {
    
    private int ticketId, rowNumber, columnNumber;
    private Date date, time;

    public Ticket(int ticketId, int rowNumber, int columnNumber, Date date, Date time) {
        this.ticketId = ticketId;
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
        this.date = date;
        this.time = time;
    }

    public Ticket() {
    }
    
    

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public int getColumnNumber() {
        return columnNumber;
    }

    public void setColumnNumber(int columnNumber) {
        this.columnNumber = columnNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Ticket{" + "ticketId=" + ticketId + ", rowNumber=" + rowNumber + ", columnNumber=" + columnNumber + ", date=" + date + ", time=" + time + '}';
    }
    
    
    
}
