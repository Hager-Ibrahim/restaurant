package com.example.restaurant.ui.fragment.orderPending;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.restaurant.R;
import com.example.restaurant.data.model.api.homeCycle.Order;
import com.example.restaurant.databinding.FragmentOrderStateBinding;
import com.example.restaurant.ui.fragment.BaseFragment;
import com.example.restaurant.utils.MyApplication;

import java.util.List;

public class OrderPendingFragment extends BaseFragment {

    FragmentOrderStateBinding mBinding;
    OrderPendingViewModel modelView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_order_state, container, false);
        View view = mBinding.getRoot();

        modelView = OrderPendingViewModel.create(getActivity());
        MyApplication.getAppComponent().inject(modelView);

        modelView.getOrdersByPage("pending", "1");

        modelView.getOrders().observe(getActivity(), new Observer<List<Order>>() {
            @Override
            public void onChanged(List<Order> orders) {
                OrderAdapter orderAdapter = new OrderAdapter(getActivity(),orders, "pending");
                mBinding.recycler.setLayoutManager(new LinearLayoutManager(getContext()));
                mBinding.recycler.setAdapter(orderAdapter);
            }
        });

        return view;
    }

}
