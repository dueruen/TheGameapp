package com.example.thegameapp.games;

import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("countResult")
    private int resultCount;


    @SerializedName("result")
    private Game game;


    public Result(int resultCount, Game game) {
        this.resultCount = resultCount;
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getResultCount() {
        return resultCount;
    }

    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }
}
