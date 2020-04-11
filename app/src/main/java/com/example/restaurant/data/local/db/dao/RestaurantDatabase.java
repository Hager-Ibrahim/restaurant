package com.example.restaurant.data.local.db.dao;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.restaurant.data.model.api.userCycle.Region;

@Database(entities = {Region.class}, version = 2)
public abstract class RestaurantDatabase extends RoomDatabase {
    public abstract RestaurantDao restaurantDao();
}
