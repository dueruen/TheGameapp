package com.example.thegameapp.games.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Game implements Comparable<Game> {

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String title;

    @SerializedName("publishers")
    private Publisher[] publishers;

    @SerializedName("genres")
    private Genre[] genre;

    @SerializedName("description_raw")
    private String description;

    @SerializedName("background_image")
    private String imageURL;

    @SerializedName("metacritic")
    private int score;

    @SerializedName("platform")
    private String platform;

    @SerializedName("developers")
    private Developer[] developer;

    @SerializedName("released")
    private String releaseDate;

    public Game(String title, Genre[] genre, String description, String imageURL, int score, String platform, Publisher[] publishers, Developer[] developer, String releaseDate) {
        this.title = title;
        this.genre = genre;
        this.description = description;
        this.imageURL = imageURL;
        this.score = score;
        this.platform = platform;
        this.publishers = publishers;
        this.developer = developer;
        this.releaseDate = releaseDate;
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

    public Genre[] getGenre() {
        return genre;
    }

    public void setGenre(Genre[] genre) {
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

    public Developer[] getDeveloper() {
        return developer;
    }

    public void setDeveloper(Developer[] developer) {
        this.developer = developer;
    }

    public Publisher[] getPublishers() {
        return publishers;
    }

    public void setPublishers(Publisher[] publishers) {
        this.publishers = publishers;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}