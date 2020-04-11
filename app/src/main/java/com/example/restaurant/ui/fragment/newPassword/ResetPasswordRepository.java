package com.example.restaurant.ui.fragment.newPassword;

import androidx.lifecycle.MutableLiveData;

import com.example.restaurant.data.model.api.general.BasicResponse;
import com.example.restaurant.data.model.dataBinding.Login;
import com.example.restaurant.data.remote.RestaurantApi;
import com.example.restaurant.utils.HelperMethod;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetPasswordRepository {

    final private RestaurantApi api;
    private android.app.Application app;

    @Inject
    public ResetPasswordRepository(RestaurantApi api , android.app.Application app) {
        this.api = api;
        this.app = app ;
    }

    public MutableLiveData<BasicResponse> getData(String code, String password, String newPassword, final Login login){

        final MutableLiveData<BasicResponse> mediator = new MutableLiveData<>();
        Call<BasicResponse> call = api.setNewPassword(code,password,newPassword);
        login.setHideProgress(true);
        call.enqueue(new Callback<BasicResponse>() {
            @Override
            public void onResponse(Call<BasicResponse> call, Response<BasicResponse> response) {
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
            public void onFailure(Call<BasicResponse> call, Throwable t) {
                login.setHideProgress(false);
                HelperMethod.showInfoToast(app.getBaseContext(),t.getMessage());
            }
        });

        return mediator;
    }

}
