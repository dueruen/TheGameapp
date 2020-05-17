package com.example.thegameapp.games;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.thegameapp.games.entities.Game;
import com.example.thegameapp.games.entities.Result;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;

public class GamesRepository {
    private LiveData<List<Game>> games;
    private GameService api;
    private final String RECENT_RELEASE_PERIOD =  "2020-01-01,2020-05-01";

    public GamesRepository() {
        api = RetrofitClient.getInstance().create(GameService.class);
        games =  new MutableLiveData<>();
    }

    public LiveData<List<Game>> getGamesFromTimeInterval() {
        new GetGamesFromTimeIntervalAsyncTask(this.api, this.games).execute(RECENT_RELEASE_PERIOD);
        return this.games;
    }

    public LiveData<List<Game>> getGameFromID(String id) {
        new GetGameFromIDAsyncTask(this.api, this.games).execute(id);
        return this.games;
    }

    protected void setGames(LiveData<List<Game>> games) {
        this.games = games;
    }

    private class GetGameFromIDAsyncTask extends AsyncTask<String, Void, Void> {
        private GameService api;
        private MutableLiveData<List<Game>> liveDataList;
        private ArrayList<Game> gameList;

        private GetGameFromIDAsyncTask(GameService api, LiveData<List<Game>> liveDataList) {
            this.api = api;
            this.liveDataList = (MutableLiveData) liveDataList;
            gameList = new ArrayList<>();
            this.liveDataList.postValue(gameList);
        }

        @Override
        protected Void doInBackground(String... ID) {

            Call<Game> r = api.getGameByID(ID[0]);
            try {
                Game fetchedGame = r.execute().body();
                gameList.add(fetchedGame);
                liveDataList.postValue(gameList);
            }
            catch(IOException ex) {
                ex.printStackTrace();
            }
            return null;
        }
    }

    private class GetGamesFromTimeIntervalAsyncTask extends AsyncTask<String, Void, Void> {
        private GameService api;
        private MutableLiveData<List<Game>> liveDataList;

        private GetGamesFromTimeIntervalAsyncTask(GameService api, LiveData<List<Game>> liveDataList) {
            this.api = api;
            this.liveDataList = (MutableLiveData) liveDataList;
        }

        @Override
        protected Void doInBackground(String... timeInterval) {
            ArrayList<Game> gameList = new ArrayList<>();
            Call<Result> r = api.getGamesFromTimeInterval(timeInterval[0]);
            try {
                Result fetchedResult = r.execute().body();
                Game[] fetchedGames = fetchedResult.getGames();
                gameList.addAll(Arrays.asList(fetchedGames));
            }
            catch(IOException ex) {
                ex.printStackTrace();
            }

            Collections.sort(gameList);

            liveDataList.postValue(gameList);
            return null;
        }
    }
}
