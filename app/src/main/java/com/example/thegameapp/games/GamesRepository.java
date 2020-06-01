package com.example.thegameapp.games;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.thegameapp.games.entities.Game;
import com.example.thegameapp.games.entities.Result;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import retrofit2.Call;

public class GamesRepository {
    private LiveData<List<Game>> games;
    private GameService api;
    private String recentReleasePeriod;
    private final String ORDERING = "-rating"; //For descending ordering based on rating
    private final int RECENT_RELEASE_MONTH_BACKTRACK = 2;

    public GamesRepository() {
        api = RetrofitClient.getInstance().create(GameService.class);
        games =  new MutableLiveData<>();
        recentReleasePeriod = getRecentReleasePeriod();

    }

    public LiveData<List<Game>> getGamesFromTimeInterval() {
        new GetGamesFromTimeIntervalAsyncTask(this.api, this.games).execute(recentReleasePeriod);
        return this.games;
    }

    public LiveData<List<Game>> getGameFromID(String id) {
        new GetGameFromIDAsyncTask(this.api, this.games).execute(id);
        return this.games;
    }

    public LiveData<List<Game>> searchGameByName(String name) {
        new SearchGameByNameAsyncTask(this.api, this.games).execute(name);
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


    private class SearchGameByNameAsyncTask extends AsyncTask<String, Void, Void> {
        private GameService api;
        private MutableLiveData<List<Game>> liveDataList;
        private ArrayList<Game> gameList;

        private SearchGameByNameAsyncTask(GameService api, LiveData<List<Game>> liveDataList) {
            this.api = api;
            this.liveDataList = (MutableLiveData) liveDataList;
            gameList = new ArrayList<>();
            this.liveDataList.postValue(gameList);
        }

        @Override
        protected Void doInBackground(String... name) {
            Call<Result> r = api.searchGameByName(name[0]);

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



    private class GetGamesFromTimeIntervalAsyncTask extends AsyncTask<String, Void, Void> {
        private GameService api;
        private MutableLiveData<List<Game>> liveDataList;
        private ArrayList<Game> gameList;

        private GetGamesFromTimeIntervalAsyncTask(GameService api, LiveData<List<Game>> liveDataList) {
            this.api = api;
            this.liveDataList = (MutableLiveData) liveDataList;
            gameList = new ArrayList<>();
            this.liveDataList.postValue(gameList);
        }

        @Override
        protected Void doInBackground(String... timeInterval) {
            Call<Result> r = api.getGamesFromTimeInterval(timeInterval[0], ORDERING);

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

    private String getRecentReleasePeriod() {
        StringBuilder releasePeriodSB = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();

        //Current date
        Date currentDate = cal.getTime();
        String convertedCurrentDate =sdf.format(currentDate);

        //Two months ago
        cal.add(Calendar.MONTH, -RECENT_RELEASE_MONTH_BACKTRACK);
        Date reducedDate = cal.getTime();
        String convertedReducedDate =sdf.format(reducedDate);

        releasePeriodSB.append(convertedReducedDate);
        releasePeriodSB.append(",");
        releasePeriodSB.append(convertedCurrentDate);
        return releasePeriodSB.toString();
    }
}
