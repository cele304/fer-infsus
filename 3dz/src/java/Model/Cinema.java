/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author filip
 */
public class Cinema {
    
    private int cinemaId, cityId;
    private String cinemaName, cityName;

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getCityId() {
        return cityId;
    }
    

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityName() {
        return cityName;
    }

    public Cinema() {
    }

    public Cinema(int cinemaId, String cinemaName) {
        this.cinemaId = cinemaId;
        this.cinemaName = cinemaName;
    }

    public int getCinemaId() {
        return cinemaId;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaId(int cinemaId) {
        this.cinemaId = cinemaId;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    @Override
    public String toString() {
        return "Cinema{" + "cinemaId=" + cinemaId + ", cinemaName=" + cinemaName + '}';
    }
    
    
    
}
