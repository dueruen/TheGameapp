package com.example.thegameapp.api;

import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("result")
    private Game game;

    public Result(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
