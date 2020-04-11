package com.example.restaurant.ui.fragment.registerRestaurant;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import com.example.restaurant.data.model.api.general.BasicResponse;
import com.example.restaurant.data.model.api.userCycle.Region;
import com.example.restaurant.data.model.dataBinding.Progress;
import com.example.restaurant.data.model.dataBinding.Register;
import com.example.restaurant.data.remote.RestaurantApi;
import com.example.restaurant.utils.HelperMethod;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.restaurant.utils.Constants.IMAGE_KEY;
import static com.example.restaurant.utils.Constants.NETWORK_TIMEOUT;
import static com.example.restaurant.utils.HelperMethod.convertFileToMultipart;
import static com.example.restaurant.utils.HelperMethod.convertStringToRequestBody;
import static com.example.restaurant.utils.HelperMethod.showInfoToast;

public class RegisterRestaurantClient {

    final private RestaurantApi api;
    final private ScheduledExecutorService executor;
    Application application;
    private MutableLiveData<List<Region>> citiesLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Region>> regionsLiveData = new MutableLiveData<>();

    RetrieveCitiesRunnable mRetrieveCitiesRunnable;
    RetrieveRegionsRunnable mRetrieveRegionsRunnable;

    @Inject
    public RegisterRestaurantClient(RestaurantApi api,
                                    ScheduledExecutorService executor,
                                    Application application) {
        this.api = api;
        this.executor = executor;
        this.application = application;
        getCities();
    }

    public void getCities(){

        if(mRetrieveCitiesRunnable != null){
            mRetrieveCitiesRunnable = null;
        }

        mRetrieveCitiesRunnable = new RetrieveCitiesRunnable();
        Future handler = executor.submit(mRetrieveCitiesRunnable);
        executor.schedule(new Runnable() {
            @Override
            public void run() {
                handler.cancel(true);
            }
        }, NETWORK_TIMEOUT, TimeUnit.MILLISECONDS);

    }


    public void getRegionsByCityId(String cityId){

        if(mRetrieveRegionsRunnable != null){
            mRetrieveRegionsRunnable = null;
        }

        mRetrieveRegionsRunnable = new RetrieveRegionsRunnable(cityId);
        Future handler = executor.submit(mRetrieveRegionsRunnable);
        executor.schedule(new Runnable() {
            @Override
            public void run() {
                handler.cancel(true);
            }
        }, NETWORK_TIMEOUT, TimeUnit.MILLISECONDS);

    }

    private class RetrieveCitiesRunnable implements Runnable{

        @Override
        public void run() {
            try {
                Response<BasicResponse<List<Region>>> response = api.getCities().execute();

                if(response.isSuccessful()){
                    if(response.body().getStatus() == 1){
                        List<Region> cityList = response.body().getData();
                        citiesLiveData.postValue(cityList);
                        Log.v("11111",cityList.get(2).getName());
                    }
                    else {
                        HelperMethod.showInfoToast(application.getApplicationContext(), response.body().getMsg());
                        citiesLiveData.postValue(null);
                        Toast.makeText(application.getBaseContext(), "غغ", Toast.LENGTH_LONG).show();

                    }
                }
                else {
                    HelperMethod.showErrorToast(application.getApplicationContext(), response.errorBody().string());
                    citiesLiveData.postValue(null);
                    Toast.makeText(application.getBaseContext(), "غغ", Toast.LENGTH_LONG).show();


                }
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(application.getBaseContext(), "غغ", Toast.LENGTH_LONG).show();
                citiesLiveData.postValue(null);
            }
        }


    }

    private class RetrieveRegionsRunnable implements Runnable{

        private String cityId;

        public RetrieveRegionsRunnable(String cityId) {
            this.cityId = cityId;
        }

        @Override
        public void run() {

            try {
                Response<BasicResponse<List<Region>>> response = api.getRegion(cityId).execute();
                if(response.isSuccessful()){
                    if(response.body().getStatus() == 1){
                        List<Region> regionList = response.body().getData();
                        regionsLiveData.postValue(regionList);
                    }
                    else {
                        HelperMethod.showInfoToast(application.getApplicationContext(), response.body().getMsg());
                        regionsLiveData.postValue(null);
                    }
                }
                else {
                    HelperMethod.showErrorToast(application.getApplicationContext(), response.errorBody().string());
                    regionsLiveData.postValue(null);

                }
            } catch (IOException e) {
                e.printStackTrace();
                regionsLiveData.postValue(null);
            }
        }


    }

    public MutableLiveData<List<Region>> getCitiesLiveData(){
        return citiesLiveData;
    }

    public MutableLiveData<List<Region>> getRegionsLiveData(){
        return regionsLiveData;
    }

    public void register(Progress progress, Register register , String regionId, String imagePath){

        Call<BasicResponse> call = api.registerRestaurant(
                convertStringToRequestBody(register.getName().get()),
                convertStringToRequestBody(register.getEmail().get()),
                convertStringToRequestBody(register.getPassword().get()),
                convertStringToRequestBody(register.getConfirmPassword().get()),
                convertStringToRequestBody(register.getPhone().get()),
                convertStringToRequestBody(register.getWhats().get()),
                convertStringToRequestBody(regionId),
                convertStringToRequestBody(register.getDeliveryCharge().get()),
                convertStringToRequestBody(register.getMinOrder().get()),
                convertFileToMultipart(imagePath, IMAGE_KEY),
                convertStringToRequestBody(register.getDeliveryTime().get()));
        progress.setHideProgressBar(true);
        call.enqueue(new Callback<BasicResponse>() {
            @Override
            public void onResponse(Call<BasicResponse> call, Response<BasicResponse> response) {
                if(response.body().getStatus()== 1){
                    progress.setHideProgressBar(false);
                    showInfoToast(application.getBaseContext(),response.body().getMsg());
                    Log.v("11111", response.body().getMsg());
                }
                else {
                    progress.setHideProgressBar(false);
                    showInfoToast(application.getBaseContext(),response.body().getMsg());
                    Log.v("11111", response.body().getMsg());

                }
            }
            @Override
            public void onFailure(Call<BasicResponse> call, Throwable t) {
                showInfoToast(application.getBaseContext(),t.getMessage());
                progress.setHideProgressBar(true);
                Log.v("11111", t.getMessage());

            }
        });
    }
}
