package com.example.thegameapp.games;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class GamesViewModel extends AndroidViewModel {

    private GamesRepository gameRepository;
    private LiveData<List<Game>> games;

    public GamesViewModel(@NonNull Application application) {
        super(application);
        gameRepository = new GamesRepository();
        games = gameRepository.getGames();
    }

    public LiveData<List<Game>> getGames() {
        return games;
    }
}