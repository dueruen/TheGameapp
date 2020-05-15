package com.example.thegameapp.games;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit instance;
    private static final String BASE_URL = "https://chicken-coop.fr/rest/games/";

    private RetrofitClient() {}

    public static Retrofit getInstance() {
        if(instance == null) {
            instance = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return instance;
    }
}
