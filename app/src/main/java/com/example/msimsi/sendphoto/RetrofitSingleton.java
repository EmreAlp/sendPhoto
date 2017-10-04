package com.example.msimsi.sendphoto;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by MSIMSI on 4.10.2017.
 * All rights reserved.
 */
public class RetrofitSingleton {

    public static final String BASE_URL = "https://lit-escarpment-15978.herokuapp.com";

    private RetrofitSingleton() {
    } // Empty constructor for Singleton Class

    private static Retrofit createRetrofit() { // Instance of Api

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());

        return builder.build();
    }

    public static SendPhotoApi getInstance() {
        return RetrofitSingleton.createRetrofit().create(SendPhotoApi.class);
    }
}
