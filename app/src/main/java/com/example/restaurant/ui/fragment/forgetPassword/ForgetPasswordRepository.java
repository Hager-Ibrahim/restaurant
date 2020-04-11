package com.example.restaurant.ui.fragment.forgetPassword;

import androidx.lifecycle.MutableLiveData;

import com.example.restaurant.data.model.api.general.BasicResponse;
import com.example.restaurant.data.model.api.userCycle.ForgetPassword;
import com.example.restaurant.data.model.dataBinding.Login;
import com.example.restaurant.data.remote.RestaurantApi;
import com.example.restaurant.utils.HelperMethod;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetPasswordRepository {

    final private RestaurantApi api;
    private android.app.Application app;

    @Inject
    public ForgetPasswordRepository(RestaurantApi api , android.app.Application app) {
        this.api = api;
        this.app = app ;
    }

    public MutableLiveData<BasicResponse<ForgetPassword>> getData(String email, final Login login){

        final MutableLiveData<BasicResponse<ForgetPassword>> mutableLiveData = new MutableLiveData<>();
        Call<BasicResponse<ForgetPassword>> call = api.getCode(email);
        login.setHideProgress(true);
        call.enqueue(new Callback<BasicResponse<ForgetPassword>>() {
            @Override
            public void onResponse(Call<BasicResponse<ForgetPassword>> call, Response<BasicResponse<ForgetPassword>> response) {
                    if(response.body().getStatus() == 1){
                        login.setHideProgress(false);
                        mutableLiveData.setValue(response.body());
                    }
                    else {
                        login.setHideProgress(false);
                        HelperMethod.showInfoToast(app.getBaseContext(), response.body().getMsg());
                    }
            }

            @Override
            public void onFailure(Call<BasicResponse<ForgetPassword>> call, Throwable t) {
                login.setHideProgress(false);
                HelperMethod.showInfoToast(app.getBaseContext(),t.getMessage());
            }
        });
        return  mutableLiveData;
    }


}
