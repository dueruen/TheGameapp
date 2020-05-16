package com.example.thegameapp.games;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;

public class GamesRepository {
    private LiveData<List<Game>> games;
    private GameService api;
    private String[] gamesToFetch = new String[] { "Warcraft III: Reign of Chaos", "Jazzpunk"};
    private List<String> fetchList;

    public GamesRepository() {
        api = RetrofitClient.getInstance().create(GameService.class);
        fetchList = Arrays.asList(gamesToFetch);
        games =  new MutableLiveData<>();

    }

    public LiveData<List<Game>> getGames() {
        new GetGamesAsyncTask(this.api, this.games).execute(fetchList);
        return this.games;
    }

    protected void setGames(LiveData<List<Game>> games) {
        this.games = games;
    }

    private class GetGamesAsyncTask extends AsyncTask<List<String>, Void, Void> {
        private GameService api;
        private MutableLiveData<List<Game>> liveDataList;

        private GetGamesAsyncTask(GameService api, LiveData<List<Game>> liveDataList) {
            this.api = api;
            this.liveDataList = (MutableLiveData) liveDataList;
        }

        @Override
        protected Void doInBackground(List<String>... games) {
            ArrayList<Game> gameList = new ArrayList<>();
            for(String title : games[0]) {
                Call<Result> r = api.getGame(title);
                System.out.println("The list has this size: " + games[0].size());
                System.out.println("Fetching: " + title);
                try {
                    Result fetchedResult = r.execute().body();
                    gameList.add(fetchedResult.getGame());
                }
                catch(IOException ex) {
                    ex.printStackTrace();
                }
            }
            liveDataList.postValue(gameList);
            return null;
        }
    }
}
