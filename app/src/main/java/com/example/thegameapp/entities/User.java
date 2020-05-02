package com.example.thegameapp.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "user_table")
public class User {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String password;
    private ArrayList<FavoritEntity> favorits;

    public User(String name, String password, ArrayList<FavoritEntity> favorits) {
        this.name = name;
        this.password = password;
        this.favorits = favorits;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<FavoritEntity> getFavorits() {
        return favorits;
    }
}
