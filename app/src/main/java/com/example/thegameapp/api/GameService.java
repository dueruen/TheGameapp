package com.example.thegameapp.api;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GameService {
    @GET("?title=[{title}]?platform=[{platform}]&amp;selectors[]=title&amp;selectors[]=genre&amp;selectors[]=score&amp;selectors[]=alsoAvailableOn&amp;selectors[]=image&amp;selectors[]=description")
    Call<Game> getGame(String title, String platform);
}

