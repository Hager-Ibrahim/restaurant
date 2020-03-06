package com.example.restaurant.data.remote;
import com.example.restaurant.data.model.api.BasicResponse;
import com.example.restaurant.data.model.api.userCycle.ForgetPassword;
import com.example.restaurant.data.model.api.userCycle.UserData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import static com.example.restaurant.utils.Constants.MEDTHOD_CODE;
import static com.example.restaurant.utils.Constants.METHOD_LOGIN;
import static com.example.restaurant.utils.Constants.METHOD_RESET_PASSWORD;

public interface RestaurantApi {

    //login fragment
    @FormUrlEncoded
    @POST(METHOD_LOGIN)
    Call<BasicResponse<UserData>> getLoginData(@Field("email") String email,

                                               @Field("password") String password);
    //forget password Fragment
    @FormUrlEncoded
    @POST(MEDTHOD_CODE)
    Call<BasicResponse<ForgetPassword>> getCode(@Field("email") String email);

    //reset password fragment
    @FormUrlEncoded
    @POST(METHOD_RESET_PASSWORD)
    Call<BasicResponse> setNewPassword(@Field("code") String code,
                                       @Field("password") String password,
                                       @Field("password_confirmation") String newPassword);
}
