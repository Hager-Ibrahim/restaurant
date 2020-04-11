package com.example.restaurant.ui.fragment.orders;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.restaurant.R;
import com.example.restaurant.databinding.FragmentOrdersBinding;
import com.example.restaurant.ui.fragment.BaseFragment;

public class OrdersFragment extends BaseFragment {

    FragmentOrdersBinding mBinding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_orders, container, false);
        View view = mBinding.getRoot();

        OrdersTabAdapter ordersTabAdapter = new OrdersTabAdapter(getContext(), getFragmentManager());
        mBinding.viewPager.setAdapter(ordersTabAdapter);
        mBinding.tabLayout.setupWithViewPager(mBinding.viewPager);

        return view;
    }

}
