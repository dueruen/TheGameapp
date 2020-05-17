package com.example.thegameapp.favorites.entities;

import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {FavoriteEntity.class}, version = 2, exportSchema = false)
public abstract class FavoriteDatabase extends RoomDatabase {
    private static FavoriteDatabase instance;
    public abstract FavoriteDao favoriteDao();

    public static synchronized FavoriteDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    FavoriteDatabase.class,
                    "favorite_database")
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
        private FavoriteDao favoriteDao;

        private PopulateDbAsyncTask(FavoriteDatabase db) {
            favoriteDao = db.favoriteDao();
        }

        @Override
        protected Void doInBackground(Void... Void) {
            /*favoriteDao.insert(new FavoriteEntity(
                    12345,
                    "TEST_TITLE01",
                    9999,
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQB_DE_tw2pRS7u7Bw4PjZfINbgwpAcakrYaLrRZZtbHJ4eFoL5&usqp=CAU"));
            favoriteDao.insert(new FavoriteEntity(
                    123456,
                    "TEST_TITLE02",
                    9999,
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQB_DE_tw2pRS7u7Bw4PjZfINbgwpAcakrYaLrRZZtbHJ4eFoL5&usqp=CAU"));
            favoriteDao.insert(new FavoriteEntity(
                    123457,
                    "TEST_TITLE03",
                    9999,
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQB_DE_tw2pRS7u7Bw4PjZfINbgwpAcakrYaLrRZZtbHJ4eFoL5&usqp=CAU"));
            favoriteDao.insert(new FavoriteEntity(
                    123458,
                    "TEST_TITLE04",
                    9999,
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQB_DE_tw2pRS7u7Bw4PjZfINbgwpAcakrYaLrRZZtbHJ4eFoL5&usqp=CAU"));*/
            return null;
        }
    }
}
