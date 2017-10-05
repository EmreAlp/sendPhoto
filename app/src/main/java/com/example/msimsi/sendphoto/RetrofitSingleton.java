package com.example.msimsi.sendphoto;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by MSIMSI on 4.10.2017.
 * All rights reserved.
 */
public class RetrofitSingleton {

    public static final String BASE_URL = "https://lit-escarpment-15978.herokuapp.com";
    public static final String BASE_URL2 = "http://uploads.im";

    private RetrofitSingleton() {
    } // Empty constructor for Singleton Class

    private static Retrofit createRetrofit(String urlChoice) { // Instance of Api

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(urlChoice)
                .addConverterFactory(GsonConverterFactory.create());

        return builder.build();
    }

    public static SendPhotoApi getInstance(String urlChoice) {
        return RetrofitSingleton.createRetrofit(urlChoice).create(SendPhotoApi.class);
    }
}
