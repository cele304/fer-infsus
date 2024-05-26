/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author filip
 */
public class Film {
    
    private String filmName, synopsis, filmPage, originalTitle, genre, nationality, distributor, director, actors, classification, otherData,cinemaName;
    private int FilmId;

    public void setFilmId(int FilmId) {
        this.id = FilmId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
   

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public String getCinemaName() {
        return cinemaName;
    }
    private int duration, releaseYear, id;

    public Film() {
    }

    public void setFilId(int id) {
        this.id = id;
    }

    public int getFilmId() {
        return id;
    }

    public String getFilmName() {
        return filmName;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getFilmPage() {
        return filmPage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getGenre() {
        return genre;
    }

    public String getNationality() {
        return nationality;
    }

    public String getDistributor() {
        return distributor;
    }

    public String getDirector() {
        return director;
    }

    public String getActors() {
        return actors;
    }

    public String getClassification() {
        return classification;
    }

    public String getOtherData() {
        return otherData;
    }

    public int getDuration() {
        return duration;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public void setFilmPage(String filmPage) {
        this.filmPage = filmPage;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setDistributor(String distributor) {
        this.distributor = distributor;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public void setOtherData(String otherData) {
        this.otherData = otherData;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }
    
    

    @Override
    public String toString() {
        return "Film{" + "filmName=" + filmName + ", synopsis=" + synopsis + ", filmPage=" + filmPage + ", originalTitle=" + originalTitle + ", genre=" + genre + ", nationality=" + nationality + ", distributor=" + distributor + ", director=" + director + ", actors=" + actors + ", classification=" + classification + ", otherData=" + otherData + ", duration=" + duration + ", releaseYear=" + releaseYear + '}';
    }
    
    
}
