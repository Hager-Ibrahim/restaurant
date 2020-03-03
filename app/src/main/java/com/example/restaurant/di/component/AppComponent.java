package com.example.restaurant.di.component;

import android.content.Context;

import com.example.restaurant.di.module.ApiModule;
import com.example.restaurant.di.module.AppModule;
import com.example.restaurant.di.module.NetModule;
import com.example.restaurant.di.module.RepositoryModule;
import com.example.restaurant.ui.fragment.login.LoginViewModel;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(
        modules = {AppModule.class, NetModule.class, RepositoryModule.class, ApiModule.class}
)
public interface AppComponent {

    public void inject(LoginViewModel viewModelModule);
    public void inject(Context content);

}
