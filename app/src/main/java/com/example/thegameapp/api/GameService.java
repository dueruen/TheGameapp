package com.example.thegameapp.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GameService {
    @GET("[{title}]") //?platform=[{platform}]&amp;selectors[]=title&amp;selectors[]=genre&amp;selectors[]=score&amp;selectors[]=alsoAvailableOn&amp;selectors[]=image&amp;selectors[]=description")
    Call<Result> getGame(@Query("title")String title);
}

