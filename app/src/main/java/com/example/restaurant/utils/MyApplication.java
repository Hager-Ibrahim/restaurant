package com.example.restaurant.utils;

import android.app.Application;

import com.example.restaurant.di.component.AppComponent;
import com.example.restaurant.di.component.DaggerAppComponent;
import com.example.restaurant.di.module.ApiModule;
import com.example.restaurant.di.module.AppModule;
import com.example.restaurant.di.module.NetModule;
import com.example.restaurant.di.module.RepositoryModule;

public class MyApplication extends Application {

    private static AppComponent appComponent;

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    public void onCreate(){
        super.onCreate();

        appComponent = DaggerAppComponent.builder().
                appModule(new AppModule(this)).
                apiModule(new ApiModule()).
                netModule(new NetModule("http://ipda3-tech.com/sofra-v2/api/v2/")).
                repositoryModule(new RepositoryModule()).
                build();

    }
}
