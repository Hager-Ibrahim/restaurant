package com.example.restaurant.ui.fragment;

import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.restaurant.data.model.api.BasicResponse;
import com.example.restaurant.data.model.api.UserData;
import com.example.restaurant.data.remote.RestaurantApi;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {

    final private RestaurantApi api;
    private android.app.Application app;

    @Inject
    public LoginRepository(RestaurantApi api , android.app.Application app) {
        this.api = api;
        this.app = app ;
    }


    public MutableLiveData<BasicResponse<UserData>> getData(String email, String passord){

        final MutableLiveData<BasicResponse<UserData>> mediator = new MutableLiveData<>();
        api.getLoginData(email, passord).enqueue(new Callback<BasicResponse<UserData>>() {
            @Override
            public void onResponse(Call<BasicResponse<UserData>> call, Response<BasicResponse<UserData>> response) {
                if(response.body().getStatus() == 1){
                    mediator.setValue(response.body());
                }
                else{
                    Toast.makeText(app.getBaseContext(),response.body().getMsg(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<BasicResponse<UserData>> call, Throwable t) {
                Toast.makeText(app.getBaseContext(),t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        return mediator;
    }
}
