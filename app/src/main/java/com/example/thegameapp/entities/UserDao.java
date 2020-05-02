package com.example.thegameapp.entities;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.TypeConverter;
import androidx.room.Update;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

@Dao
public interface UserDao {

    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    @Query("SELECT * FROM user_table WHERE name = :name AND password = :password LIMIT 1")
    LiveData<User> getUser(String name, String password);

    public class Converters {
        @TypeConverter
        public static ArrayList<FavoritEntity> fromString(String value) {
            Type listType = new TypeToken<ArrayList<FavoritEntity>>() {}.getType();
            ArrayList<FavoritEntity> ff = new Gson().fromJson(value, listType);
            return ff;
        }

        @TypeConverter
        public static String fromArrayList(ArrayList<FavoritEntity> list) {
            Gson gson = new Gson();
            String json = gson.toJson(list);
            return json;
        }
    }
}

