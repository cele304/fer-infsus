/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author filip
 */
public class Comment {
    
    private int commentId, rating;
    private String commentText, user, film;

    public Comment() {
    }

    public Comment(int commentId, int rating, String commentText) {
        this.commentId = commentId;
        this.rating = rating;
        this.commentText = commentText;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setFilm(String fillm) {
        this.film = fillm;
    }

    public String getUser() {
        return user;
    }

    public String getFilm() {
        return film;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    @Override
    public String toString() {
        return "Comment{" + "commentId=" + commentId + ", rating=" + rating + ", commentText=" + commentText + '}';
    }
    
    
    
}
