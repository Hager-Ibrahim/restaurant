package com.example.restaurant.ui.fragment.foodItem;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.example.restaurant.data.model.api.homeCycle.Item;
import java.util.List;
import javax.inject.Inject;

public class FoodItemViewModel extends ViewModel {

    private FoodItemRepository repository;

    @Inject
    public void setRepository(FoodItemRepository repository) {
        this.repository = repository;
    }

    public static FoodItemViewModel create(FragmentActivity activity) {
        FoodItemViewModel viewModel = ViewModelProviders.of(activity).get(FoodItemViewModel.class);
        return viewModel;
    }


    public LiveData<List<Item>> getItems(){
        return repository.getItems();
    }


    public void getItemsByCategoryId(String categoryId){
        repository.getItemsByCategoryId(categoryId);
    }
}
