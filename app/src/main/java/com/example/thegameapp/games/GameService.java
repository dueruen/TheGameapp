package com.example.thegameapp.games;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GameService {
    @GET("{title}") //?platform=[{platform}]&amp;selectors[]=title&amp;selectors[]=genre&amp;selectors[]=score&amp;selectors[]=alsoAvailableOn&amp;selectors[]=image&amp;selectors[]=description")
    Call<Result> getGame(@Path("title")String title);
}
