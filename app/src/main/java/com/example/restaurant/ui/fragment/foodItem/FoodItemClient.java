package com.example.restaurant.ui.fragment.foodItem;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.example.restaurant.data.model.api.general.BasicResponse;
import com.example.restaurant.data.model.api.general.Page;
import com.example.restaurant.data.model.api.homeCycle.Item;
import com.example.restaurant.data.remote.RestaurantApi;
import com.example.restaurant.utils.HelperMethod;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import retrofit2.Response;

import static com.example.restaurant.utils.Constants.API_TOKEN;
import static com.example.restaurant.utils.Constants.NETWORK_TIMEOUT;

public class FoodItemClient {

    final private RestaurantApi api;
    final private ScheduledExecutorService executor;
    Application application;
    private MutableLiveData<List<Item>> itemsLiveData = new MutableLiveData<>();
    RetrieveItemsRunnable mRetrieveItemsRunnable;

    @Inject
    public FoodItemClient(RestaurantApi api,
                      ScheduledExecutorService executor,
                      Application application) {
        this.api = api;
        this.executor = executor;
        this.application = application;

    }

    public void getItemsByCategoryId(String categoryId){

        if(mRetrieveItemsRunnable != null){
            mRetrieveItemsRunnable = null;
        }

        mRetrieveItemsRunnable = new RetrieveItemsRunnable(categoryId);
        Future handler = executor.submit(mRetrieveItemsRunnable);
        executor.schedule(new Runnable() {
            @Override
            public void run() {
                handler.cancel(true);
            }
        }, NETWORK_TIMEOUT, TimeUnit.MILLISECONDS);

    }

    private class RetrieveItemsRunnable implements Runnable{

        private String categoryId;

        public RetrieveItemsRunnable(String categoryId) {
            this.categoryId = categoryId;
        }

        @Override
        public void run() {
            try {
                Response<BasicResponse<Page<Item>>> response = api.getItems(API_TOKEN,categoryId).execute();

                    if(response.body().getStatus() == 1){
                        List<Item> itemList = response.body().getData().getData();
                        itemsLiveData.postValue(itemList);
                    }
                    else {
                        HelperMethod.showInfoToast(application.getApplicationContext(), response.body().getMsg());
                        itemsLiveData.postValue(null);
                    }


            } catch (IOException e) {
                e.printStackTrace();
                itemsLiveData.postValue(null);
            }
        }
    }

    public MutableLiveData<List<Item>> getItemsLiveData(){
        return itemsLiveData;
    }

}
