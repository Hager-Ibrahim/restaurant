package com.example.restaurant.ui.fragment.login;

import androidx.lifecycle.MutableLiveData;

import com.example.restaurant.data.model.api.BasicResponse;
import com.example.restaurant.data.model.api.userCycle.UserData;
import com.example.restaurant.data.model.dataBinding.Login;
import com.example.restaurant.data.remote.RestaurantApi;
import com.example.restaurant.utils.HelperMethod;

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
                    HelperMethod.showSuccessToast(app.getBaseContext(),"Success!");
                }
                else{
                    login.setHideProgress(false);
                    HelperMethod.showInfoToast(app.getBaseContext(),response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<BasicResponse<UserData>> call, Throwable t) {
                login.setHideProgress(false);
                HelperMethod.showInfoToast(app.getBaseContext(),t.getMessage());
            }
        });

        return mediator;
    }


}
