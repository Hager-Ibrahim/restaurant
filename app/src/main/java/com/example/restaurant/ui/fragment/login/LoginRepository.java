package com.example.restaurant.ui.fragment.login;

import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;

import com.example.restaurant.data.model.api.BasicResponse;
import com.example.restaurant.data.model.api.UserData;
import com.example.restaurant.data.model.dataBinding.Login;
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


    public MutableLiveData<BasicResponse<UserData>> getData(String email, String password, final Login login){

        final MutableLiveData<BasicResponse<UserData>> mediator = new MutableLiveData<>();
        Call<BasicResponse<UserData>> call = api.getLoginData(email,password);
        login.setHideProgress(true);
        call.enqueue(new Callback<BasicResponse<UserData>>() {
            @Override
            public void onResponse(Call<BasicResponse<UserData>> call, Response<BasicResponse<UserData>> response) {
                if(response.body().getStatus() == 1){
                    login.setHideProgress(false);
                    mediator.setValue(response.body());
                }
                else{
                    login.setHideProgress(false);
                    Toast.makeText(app.getBaseContext(),response.body().getMsg(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<BasicResponse<UserData>> call, Throwable t) {
                login.setHideProgress(false);
                Toast.makeText(app.getBaseContext(),t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        return mediator;
    }


}
