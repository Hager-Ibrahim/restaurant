package com.example.restaurant.ui.fragment.foodItem;

import android.content.Intent;
import android.os.Bundle;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.restaurant.R;
import com.example.restaurant.data.model.api.homeCycle.Item;
import com.example.restaurant.databinding.FragmentFoodItemBinding;
import com.example.restaurant.ui.fragment.BaseFragment;
import com.example.restaurant.utils.MyApplication;

import java.util.List;

public class FoodItemFragment extends BaseFragment {

    public String categoryId;
    FragmentFoodItemBinding mBinding;
    FoodItemViewModel modelView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_food_item, container, false);
        View view = mBinding.getRoot();

        modelView = FoodItemViewModel.create(getActivity());
        MyApplication.getAppComponent().inject(modelView);

        categoryId = this.getArguments().getString("categoryId");
        modelView.getItemsByCategoryId(categoryId);

        modelView.getItems().observe(getActivity(), new Observer<List<Item>>() {
            @Override
            public void onChanged(List<Item> items) {
                mBinding.recycler.setLayoutManager(new LinearLayoutManager(getContext()));
                mBinding.recycler.setAdapter(new FoodItemAdapter(getActivity(),items));
            }
        });


        return view;
    }



}
