package com.example.restaurant.ui.fragment.homeCategories;

import android.util.Log;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import com.example.restaurant.data.model.api.general.BasicResponse;
import com.example.restaurant.data.model.api.homeCycle.Category;
import com.example.restaurant.data.model.dataBinding.Progress;
import com.example.restaurant.data.remote.RestaurantApi;

import java.util.List;
import javax.inject.Inject;


public class HomeViewModel extends ViewModel {

    private HomeRepository repository;

    @Inject
    public void setRepository(HomeRepository repository) {
        this.repository = repository;
    }

    public static HomeViewModel create(FragmentActivity activity) {
        HomeViewModel viewModel = ViewModelProviders.of(activity).get(HomeViewModel.class);
        return viewModel;
    }


    public LiveData<List<Category>> getCategories(){
        return repository.getCategories();
    }

    public void getCategoriesByPage(int page){
        repository.getCategoriesByPage(page);
    }

    public void addCategory(String categoryName, String photoUrl, Progress progress){
        repository.addCategory(categoryName, photoUrl, progress);
    }
    public void updateCategory(Category category,String imagePath, String categoryId, Progress progress){
        repository.updateCategory(category,imagePath,categoryId,progress);
    }

    public void deleteCategory(String categoryId){
        repository.deleteCategory(categoryId);
    }

}
