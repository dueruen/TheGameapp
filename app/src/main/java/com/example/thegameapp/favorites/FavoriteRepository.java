package com.example.thegameapp.favorites;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.thegameapp.favorites.entities.FavoriteDao;
import com.example.thegameapp.favorites.entities.FavoriteDatabase;
import com.example.thegameapp.favorites.entities.FavoriteEntity;

import java.util.List;

public class FavoriteRepository {
    private FavoriteDao favoriteDao;
    private LiveData<List<FavoriteEntity>> favorites;

    public FavoriteRepository(Application application) {
        FavoriteDatabase database = FavoriteDatabase.getInstance(application);
        favoriteDao = database.favoriteDao();
        favorites = favoriteDao.getFavorites();
    }

    public void insert(FavoriteEntity f) {
        new InsertFavoriteAsyncTask(favoriteDao).execute(f);
    }

    public void update(FavoriteEntity f) {
        new UpdateFavoriteAsyncTask(favoriteDao).execute(f);
    }

    public void delete(FavoriteEntity f) {
        new DeleteFavoriteAsyncTask(favoriteDao).execute(f);
    }

    public LiveData<List<FavoriteEntity>> getFavorites() {
        return favorites;
    }

    private static class InsertFavoriteAsyncTask extends AsyncTask<FavoriteEntity, Void, Void> {
        private FavoriteDao favoriteDao;

        private InsertFavoriteAsyncTask(FavoriteDao favoriteDao) {
            this.favoriteDao = favoriteDao;
        }

        @Override
        protected Void doInBackground(FavoriteEntity... favoriteEntities) {
            favoriteDao.insert(favoriteEntities[0]);
            return null;
        }
    }

    private static class UpdateFavoriteAsyncTask extends AsyncTask<FavoriteEntity, Void, Void> {
        private FavoriteDao favoriteDao;

        private UpdateFavoriteAsyncTask(FavoriteDao favoriteDao) {
            this.favoriteDao = favoriteDao;
        }

        @Override
        protected Void doInBackground(FavoriteEntity... favoriteEntities) {
            favoriteDao.update(favoriteEntities[0]);
            return null;
        }
    }

    private static class DeleteFavoriteAsyncTask extends AsyncTask<FavoriteEntity, Void, Void> {
        private FavoriteDao favoriteDao;

        private DeleteFavoriteAsyncTask(FavoriteDao favoriteDao) {
            this.favoriteDao = favoriteDao;
        }

        @Override
        protected Void doInBackground(FavoriteEntity... favoriteEntities) {
            favoriteDao.delete(favoriteEntities[0]);
            return null;
        }
    }
}
