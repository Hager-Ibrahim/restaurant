package com.example.restaurant.ui.fragment.registerRestaurant;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import com.example.restaurant.data.model.api.userCycle.Region;
import com.example.restaurant.data.model.dataBinding.Progress;
import com.example.restaurant.data.model.dataBinding.Register;

import java.util.List;
import javax.inject.Inject;

public class RegisterRestaurantViewModel extends ViewModel {

    private RegisterRestaurantRepository repository;


    @Inject
    public void setRepository(RegisterRestaurantRepository repository) {
        this.repository = repository;
    }

    public static RegisterRestaurantViewModel create(FragmentActivity activity) {
        RegisterRestaurantViewModel viewModel = ViewModelProviders.of(activity).get(RegisterRestaurantViewModel.class);
        return viewModel;
    }




    public LiveData<List<Region>> getCities(){
        return repository.getCities();
    }

    public LiveData<List<Region>> getRegions(){
        return repository.getRegions();
    }

    public void getRegionsByCityId(String cityId){
        repository.getRegionsByCityId(cityId);
    }

    public void register(Progress progress, Register register , String regionId, String imagePath){
        repository.register(
                progress,
                register,
                regionId,
                imagePath);
    }

}
