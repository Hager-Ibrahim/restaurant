package com.example.restaurant.di.module;


import com.example.restaurant.data.remote.RestaurantApi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class ApiModule {

    @Provides
    @Singleton
    public RestaurantApi providesCatalogApi(Retrofit retrofit) {
        return retrofit.create(RestaurantApi.class);
    }
}
