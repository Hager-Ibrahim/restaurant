package com.example.restaurant.data.remote;
import com.example.restaurant.data.model.api.BasicResponse;
import com.example.restaurant.data.model.api.UserData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import static com.example.restaurant.utils.Constants.METHOD_LOGIN;

public interface RestaurantApi {

    @FormUrlEncoded
    @POST(METHOD_LOGIN)
    Call<BasicResponse<UserData>> getLoginData(@Field("email") String email,
                                               @Field("password") String password);
}
