package com.example.thegameapp.favorites.entities;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface FavoriteDao {

    @Insert
    void insert(FavoriteEntity f);

    @Update
    void update(FavoriteEntity f);

    @Delete
    void delete(FavoriteEntity f);

    @Query("SELECT * FROM favorite_table")
    LiveData<List<FavoriteEntity>> getFavorites();
}