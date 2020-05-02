package com.example.thegameapp.entities;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.ArrayList;
import java.util.Arrays;

@Database(entities = {User.class}, version = 1, exportSchema = false)
@TypeConverters({UserDao.Converters.class})
public abstract class UserDatabase extends RoomDatabase {
    private static UserDatabase instance;
    public abstract UserDao userDao();

    public static synchronized UserDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    UserDatabase.class,
                    "user_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private UserDao userDao;

        private PopulateDbAsyncTask(UserDatabase db) {
            userDao = db.userDao();
        }

        @Override
        protected Void doInBackground(Void... Void) {
            userDao.insert(new User(
                    "TEST_NAME",
                    "TEST_PASSWORD",
                    new ArrayList<FavoritEntity>(Arrays.asList(
                            new FavoritEntity(
                                    "TEST_TITLE01",
                                    9999,
                                    "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQB_DE_tw2pRS7u7Bw4PjZfINbgwpAcakrYaLrRZZtbHJ4eFoL5&usqp=CAU"),
                            new FavoritEntity(
                                    "TEST_TITLE02",
                                    9999,
                                    "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQB_DE_tw2pRS7u7Bw4PjZfINbgwpAcakrYaLrRZZtbHJ4eFoL5&usqp=CAU"),
                            new FavoritEntity(
                                    "TEST_TITLE03",
                                    9999,
                                    "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQB_DE_tw2pRS7u7Bw4PjZfINbgwpAcakrYaLrRZZtbHJ4eFoL5&usqp=CAU"),
                            new FavoritEntity(
                                    "TEST_TITLE04",
                                    9999,
                                    "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQB_DE_tw2pRS7u7Bw4PjZfINbgwpAcakrYaLrRZZtbHJ4eFoL5&usqp=CAU")
                    ))));
            return null;
        }
    }
}
