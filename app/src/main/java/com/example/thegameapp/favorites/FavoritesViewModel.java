package com.example.thegameapp.favorites;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.thegameapp.favorites.entities.FavoriteEntity;

import java.util.List;

public class FavoritesViewModel extends AndroidViewModel {

    private FavoriteRepository favoriteRepository;
    private LiveData<List<FavoriteEntity>> favorites;

    public FavoritesViewModel(@NonNull Application application) {
        super(application);
        favoriteRepository = new FavoriteRepository(application);
        favorites = favoriteRepository.getFavorites();
    }

    public void insert(FavoriteEntity f) { favoriteRepository.insert(f);}

    public void update(FavoriteEntity f) {
        favoriteRepository.update(f);
    }

    public void delete(FavoriteEntity f) {
        favoriteRepository.delete(f);
    }

    public LiveData<List<FavoriteEntity>> getFavorites() {
        return favorites;
    }
}