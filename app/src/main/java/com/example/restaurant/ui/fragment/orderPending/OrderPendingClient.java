package com.example.restaurant.ui.fragment.orderPending;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.example.restaurant.data.model.api.general.BasicResponse;
import com.example.restaurant.data.model.api.general.Page;
import com.example.restaurant.data.model.api.homeCycle.Category;
import com.example.restaurant.data.model.api.homeCycle.Order;
import com.example.restaurant.data.remote.RestaurantApi;
import com.example.restaurant.ui.fragment.homeCategories.HomeClient;
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

public class OrderPendingClient {

    final private RestaurantApi api;
    final private ScheduledExecutorService executor;
    Application application;
    private MutableLiveData<List<Order>> ordersLiveData = new MutableLiveData<>();

    RetrieveOrdersRunnable mRetrieveOrdersRunnable;

    @Inject
    public OrderPendingClient(RestaurantApi api,
                      ScheduledExecutorService executor,
                      Application application) {
        this.api = api;
        this.executor = executor;
        this.application = application;

    }

    public void getOrdersByPage(String state, String page){

        if(mRetrieveOrdersRunnable != null){
            mRetrieveOrdersRunnable = null;
        }

        mRetrieveOrdersRunnable = new RetrieveOrdersRunnable(state, page);
        Future handler = executor.submit(mRetrieveOrdersRunnable);
        executor.schedule(new Runnable() {
            @Override
            public void run() {
                handler.cancel(true);
            }
        }, NETWORK_TIMEOUT, TimeUnit.MILLISECONDS);

    }


    private class RetrieveOrdersRunnable implements Runnable{

        private String state;
        private String page;

        public RetrieveOrdersRunnable(String state, String page) {
            this.state = state;
            this.page = page;
        }

        @Override
        public void run() {
            try {
                Response<BasicResponse<Page<Order>>> response = api.getOrders(API_TOKEN,state,page).execute();
                    if(response.body().getStatus() == 1){
                        List<Order> orderList = response.body().getData().getData();
                        ordersLiveData.postValue(orderList);
                    }
                    else {
                        HelperMethod.showInfoToast(application.getApplicationContext(), response.body().getMsg());
                        ordersLiveData.postValue(null);
                    }


            } catch (IOException e) {
                e.printStackTrace();
                ordersLiveData.postValue(null);
            }
        }


    }

    public MutableLiveData<List<Order>> getOrdersLiveData(){
        return ordersLiveData;
    }

}
