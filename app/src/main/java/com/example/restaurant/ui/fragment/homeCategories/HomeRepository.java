package com.example.restaurant.ui.fragment.homeCategories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import com.example.restaurant.data.model.api.homeCycle.Category;
import com.example.restaurant.data.model.dataBinding.Progress;

import java.util.List;

import javax.inject.Inject;


public class HomeRepository {


    final private HomeClient homeClient;
    private MediatorLiveData<List<Category>> mCategories = new MediatorLiveData<>();


    @Inject
    public HomeRepository(HomeClient homeClient) {
        this.homeClient = homeClient;

        initMediator();
    }


    private void initMediator(){
        LiveData<List<Category>> citiesApiSource = homeClient.getCategoriesLiveData();
        mCategories.addSource(citiesApiSource, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                mCategories.setValue(categories);
            }
        });


    }

    public LiveData<List<Category>> getCategories(){
        return mCategories;
    }


    public void getCategoriesByPage(int page){
        homeClient.getCategoriesByPage(page);
    }

    public void addCategory(String categoryName, String photoUrl, Progress progress){
        homeClient.addCategory(categoryName, photoUrl, progress);
    }


    public void updateCategory(Category category,String imagePath, String categoryId, Progress progress){
        homeClient.updateCategory(category,imagePath,categoryId,progress);
    }

    public void deleteCategory(String categoryId){
        homeClient.deleteCategory(categoryId);
    }


}
