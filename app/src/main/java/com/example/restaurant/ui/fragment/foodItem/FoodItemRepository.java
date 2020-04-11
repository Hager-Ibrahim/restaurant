package com.example.restaurant.ui.fragment.foodItem;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.example.restaurant.data.model.api.homeCycle.Item;

import java.util.List;

import javax.inject.Inject;

public class FoodItemRepository {

    final private FoodItemClient foodItemClient;
    private MediatorLiveData<List<Item>> mItems = new MediatorLiveData<>();


    @Inject
    public FoodItemRepository(FoodItemClient foodItemClient) {
        this.foodItemClient = foodItemClient;

        initMediator();
    }


    private void initMediator(){
        LiveData<List<Item>> itemsApiSource = foodItemClient.getItemsLiveData();
        mItems.addSource(itemsApiSource, new Observer<List<Item>>() {
            @Override
            public void onChanged(List<Item> items) {
                mItems.setValue(items);
            }
        });


    }

    public LiveData<List<Item>> getItems(){
        return mItems;
    }

    public void getItemsByCategoryId(String categoryId){
        foodItemClient.getItemsByCategoryId(categoryId);
    }

}
