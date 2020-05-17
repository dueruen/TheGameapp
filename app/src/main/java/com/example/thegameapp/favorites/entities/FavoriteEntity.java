package com.example.thegameapp.favorites.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorite_table")
public class FavoriteEntity {
    @PrimaryKey
    private int id;
    private String title;
    private int score;
    private String image;

    public FavoriteEntity(int id, String title, int score, String image) {
        this.id = id;
        this.title = title;
        this.score = score;
        this.image = image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getScore() {
        return score;
    }

    public String getImage() {
        return image;
    }
}
