package com.example.thegameapp.games.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Game implements Comparable<Game> {

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String title;

    @SerializedName("genre")
    private String[] genre;

    @SerializedName("description_raw")
    private String description;

    @SerializedName("background_image")
    private String imageURL;

    @SerializedName("metacritic")
    private int score;

    @SerializedName("platform")
    private String platform;

    @SerializedName("publishers")
    @Expose
    private Publisher[] publishers;

    @SerializedName("developer")
    private String developer;

    public Game(String title, String[] genre, String description, String imageURL, int score, String platform, Publisher[] publishers, String developer) {
        this.title = title;
        this.genre = genre;
        this.description = description;
        this.imageURL = imageURL;
        this.score = score;
        this.platform = platform;
        this.publishers = publishers;
        this.developer = developer;

    }

    @Override
    public int compareTo(Game game) {
        return game.getScore() - this.score;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public Publisher[] getPublishers() {
        return publishers;
    }

    public void setPublisher(Publisher[] publishers) {
        this.publishers = publishers;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }
}