package com.example.gads_lbd.services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public interface ServiceBuilder {

    public static final String URL = "https://docs.google.com/forms/d/e/";

    public static Retrofit.Builder builder = new Retrofit.Builder().baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create());

    public static Retrofit retrofit = builder.build();

    public static <S> S buildService(Class<S> serviceType) {
        return retrofit.create(serviceType);
    }
}
