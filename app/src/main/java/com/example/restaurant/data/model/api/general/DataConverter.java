package com.example.restaurant.data.model.api.general;

import androidx.room.TypeConverter;

import com.example.restaurant.data.model.api.userCycle.Region;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class DataConverter {

        @TypeConverter
        public String fromItemList(Region products) {
            if (products == null) {
                return (null);
            }
            Gson gson = new Gson();
            Type type = new TypeToken<Region>() {}.getType();
            String json = gson.toJson(products, type);
            return json;
        }

        @TypeConverter
        public Region toItemList(String product) {
            if (product == null) {
                return (null);
            }
            Gson gson = new Gson();
            Type type = new TypeToken<Region>() {}.getType();
            Region ite = gson.fromJson(product, type);
            return ite;
        }

}
