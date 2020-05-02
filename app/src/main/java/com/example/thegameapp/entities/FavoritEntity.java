package com.example.thegameapp.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favoritentity_table")
public class FavoritEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private int score;
    private String image;

    public FavoritEntity(String title, int score, String image) {
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
