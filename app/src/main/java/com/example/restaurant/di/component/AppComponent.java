package com.example.restaurant.di.component;

import android.content.Context;

import com.example.restaurant.di.module.ApiModule;
import com.example.restaurant.di.module.AppModule;
import com.example.restaurant.di.module.DaoModule;
import com.example.restaurant.di.module.ExecutorModule;
import com.example.restaurant.di.module.NetModule;
import com.example.restaurant.di.module.RepositoryModule;
import com.example.restaurant.ui.fragment.forgetPassword.ForgetViewModel;
import com.example.restaurant.ui.fragment.homeCategories.HomeViewModel;
import com.example.restaurant.ui.fragment.login.LoginViewModel;
import com.example.restaurant.ui.fragment.newPassword.ResetViewModel;
import com.example.restaurant.ui.fragment.foodItem.FoodItemViewModel;
import com.example.restaurant.ui.fragment.orderPending.OrderPendingViewModel;
import com.example.restaurant.ui.fragment.registerRestaurant.RegisterRestaurantViewModel;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(
        modules = {AppModule.class, NetModule.class, RepositoryModule.class, ApiModule.class, DaoModule.class, ExecutorModule.class}
)
public interface AppComponent {

    public void inject(LoginViewModel viewModelModule);
    public void inject(ForgetViewModel viewModelModule);
    public void inject(ResetViewModel viewModelModule);
    public void inject(RegisterRestaurantViewModel viewModelModule);
    public void inject(HomeViewModel viewModelModule);
    public void inject(FoodItemViewModel viewModelModule);
    public void inject(OrderPendingViewModel viewModelModule);

    public void inject(Context content);



}
