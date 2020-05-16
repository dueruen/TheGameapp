package com.example.thegameapp.games;

import com.google.gson.annotations.SerializedName;

public class Game {

    @SerializedName("title")
    private String title;

    @SerializedName("genre")
    private String[] genre;

    @SerializedName("description")
    private String description;

    @SerializedName("image")
    private String imageURL;

    @SerializedName("score")
    private String score;

    @SerializedName("platform")
    private String platform;

    @SerializedName("publisher")
    private String[] publisher;

    @SerializedName("developer")
    private String developer;

    public Game(String title, String[] genre, String description, String imageURL, String score, String platform, String[] publisher, String developer) {
        this.title = title;
        this.genre = genre;
        this.description = description;
        this.imageURL = imageURL;
        this.score = score;
        this.platform = platform;
        this.publisher = publisher;
        this.developer = developer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getGenre() {
        return genre;
    }

    public void setGenre(String[] genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String[] getPublisher() {
        return publisher;
    }

    public void setPublisher(String[] publisher) {
        this.publisher = publisher;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }
}