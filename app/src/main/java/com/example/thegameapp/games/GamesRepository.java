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
    private String[] gamesToFetch = new String[] { "Portal 2", "Warcraft III: Reign of Chaos", "The Stanley Parable"};
    private List<String> fetchList;

    public GamesRepository() {
        api = RetrofitClient.getInstance().create(GameService.class);
        fetchList = Arrays.asList(gamesToFetch);

    }

    public LiveData<List<Game>> getGames() {
        System.out.println("Hello there" + this.games);
        new GetGamesAsyncTask(this.api).execute(fetchList);
        return this.games;
    }

    protected void setGames(LiveData<List<Game>> games) {
        this.games = games;
    }

    private class GetGamesAsyncTask extends AsyncTask<List<String>, Void, Void> {
        private GameService api;
        private LiveData<List<Game>> liveDataList;

        private GetGamesAsyncTask(GameService api) {
            this.api = api;
            liveDataList = new MutableLiveData<List<Game>>();
            System.out.println("More cancer");
        }

        @Override
        protected Void doInBackground(List<String>... games) {
            System.out.println("Things are happening");
            ArrayList<Game> gameList = new ArrayList<>();
            for(String title : games[0]) {
                Call<Result> r = api.getGame(title);
                try {
                    Result fetchedResult = r.execute().body();
                    System.out.println("for the love of fucking god " + fetchedResult.getGame());
                    gameList.add(fetchedResult.getGame());
                }
                catch(IOException ex) {
                    ex.printStackTrace();
                }
            }
            liveDataList.getValue().addAll(gameList);
            System.out.printf("Do we perhaps make it to the end?");
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            System.out.println("This actually happens" + liveDataList);
            setGames(liveDataList);
        }


    }


}
