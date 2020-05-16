package com.example.thegameapp.games;

import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("count")
    private int resultCount;


    @SerializedName("results")
    private Game[] games;


    public Result(int resultCount, Game[] games) {
        this.resultCount = resultCount;
        this.games = games;
    }

    public Game[] getGames() {
        return games;
    }

    public void setGames(Game[] games) {
        this.games = games;
    }

    public int getResultCount() {
        return resultCount;
    }

    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }
}
