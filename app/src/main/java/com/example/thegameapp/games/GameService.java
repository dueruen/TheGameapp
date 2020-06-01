package com.example.thegameapp.games;

import com.example.thegameapp.games.entities.Game;
import com.example.thegameapp.games.entities.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GameService {

    /**
     * Search for a specific game by its ID
     * @param id game ID
     * @return Call containing the Game
     */
    @GET("games/{id}")
    Call<Game> getGameByID(@Path("id")String id);

    /**
     * Returns all released games within the specified timeperiod
     * @param intervalString Dates must be in format YYYY-MM-DD, and the to and from must be comma-separated,
     * e.g.: YYYY-MM-DD,YYYY-MM-DD
     * @return Call containing result
     */
    @GET("games")
    Call<Result> getGamesFromTimeInterval(@Query("dates")String intervalString, @Query("ordering")String ordering);

    /**
     * Returns games found by name
     * @param name string
     * @return Call with result
     */
    @GET("games")
    Call<Result> searchGameByName(@Query("search")String name);
}

