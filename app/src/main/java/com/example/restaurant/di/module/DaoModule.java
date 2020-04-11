package com.example.restaurant.di.module;

import android.app.Application;
import androidx.room.Room;

import com.example.restaurant.data.local.db.dao.RestaurantDao;
import com.example.restaurant.data.local.db.dao.RestaurantDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DaoModule {

    @Provides
    @Singleton
    public RestaurantDao provideGitHubDao(Application app) {
        RestaurantDatabase db = Room.databaseBuilder(app,
                RestaurantDatabase.class, "restaurant-db").build();
        return db.restaurantDao();
    }
}
