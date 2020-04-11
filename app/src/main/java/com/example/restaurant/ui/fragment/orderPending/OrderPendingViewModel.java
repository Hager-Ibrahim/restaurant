package com.example.restaurant.ui.fragment.orderPending;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import com.example.restaurant.data.model.api.homeCycle.Category;
import com.example.restaurant.data.model.api.homeCycle.Order;


import java.util.List;

import javax.inject.Inject;

public class OrderPendingViewModel extends ViewModel {

    private OrderPendingRepository repository;

    @Inject
    public void setRepository(OrderPendingRepository repository) {
        this.repository = repository;
    }

    public static OrderPendingViewModel create(FragmentActivity activity) {
        OrderPendingViewModel viewModel = ViewModelProviders.of(activity).get(OrderPendingViewModel.class);
        return viewModel;
    }

    public LiveData<List<Order>> getOrders(){
        return repository.getOrders();
    }

    public void getOrdersByPage(String state, String page){
        repository.getOrdersByPage(state , page);
    }
}
