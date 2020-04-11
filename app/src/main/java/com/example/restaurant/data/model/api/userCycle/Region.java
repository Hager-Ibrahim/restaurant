package com.example.restaurant.data.model.api.userCycle;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;
import com.example.restaurant.data.model.api.general.DataConverter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class Region {

    @SerializedName("id")
    @Expose
    @PrimaryKey
    private Integer id;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("city_id")
    @Expose
    private String cityId;
    @SerializedName("city")
    @Expose
    @TypeConverters(DataConverter.class)
    private Region city;

    public Region(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public Region getCity() {
        return city;
    }

    public void setCity(Region city) {
        this.city = city;
    }
}
