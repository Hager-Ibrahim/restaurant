package com.example.restaurant.ui.fragment.registerRestaurant;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.example.restaurant.data.model.api.userCycle.Region;
import com.example.restaurant.data.model.dataBinding.Progress;
import com.example.restaurant.data.model.dataBinding.Register;

import java.util.List;
import javax.inject.Inject;


public class RegisterRestaurantRepository {

    final private RegisterRestaurantClient registerClient;
    private MediatorLiveData<List<Region>> mCities = new MediatorLiveData<>();
    private MediatorLiveData<List<Region>> mRegions = new MediatorLiveData<>();


    @Inject
    public RegisterRestaurantRepository(RegisterRestaurantClient registerClient) {
        this.registerClient = registerClient;

        initMediator();
    }


    private void initMediator(){
        LiveData<List<Region>> citiesApiSource = registerClient.getCitiesLiveData();
        mCities.addSource(citiesApiSource, new Observer<List<Region>>() {
            @Override
            public void onChanged(List<Region> cities) {
                mCities.setValue(cities);
            }
        });


        LiveData<List<Region>> regionsApiSource = registerClient.getRegionsLiveData();
        mRegions.addSource(regionsApiSource, new Observer<List<Region>>() {
            @Override
            public void onChanged(List<Region> regions) {
                mRegions.setValue(regions);
            }
        });

    }

    public LiveData<List<Region>> getCities(){
        return mCities;
    }

    public LiveData<List<Region>> getRegions(){
        return mRegions;
    }

    public void getRegionsByCityId(String cityId){
        registerClient.getRegionsByCityId(cityId);
    }


    public void register(Progress progress, Register register , String regionId, String imagePath){
        registerClient.register(
                progress,
                register,
                regionId,
                imagePath);
    }
}
