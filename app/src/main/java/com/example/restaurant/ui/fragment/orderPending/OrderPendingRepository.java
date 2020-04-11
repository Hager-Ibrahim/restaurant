package com.example.restaurant.ui.fragment.orderPending;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import com.example.restaurant.data.model.api.homeCycle.Order;
import java.util.List;
import javax.inject.Inject;

public class OrderPendingRepository {

    final private OrderPendingClient orderPendingClient;
    private MediatorLiveData<List<Order>> mOrders = new MediatorLiveData<>();

    @Inject
    public OrderPendingRepository(OrderPendingClient orderPendingClient) {
        this.orderPendingClient = orderPendingClient;

        initMediator();
    }


    private void initMediator(){
        LiveData<List<Order>> citiesApiSource = orderPendingClient.getOrdersLiveData();
        mOrders.addSource(citiesApiSource, new Observer<List<Order>>() {
            @Override
            public void onChanged(List<Order> orders) {
                mOrders.setValue(orders);
            }
        });


    }

    public LiveData<List<Order>> getOrders(){
        return mOrders;
    }

    public void getOrdersByPage(String state, String page){
        orderPendingClient.getOrdersByPage(state , page);
    }


}
