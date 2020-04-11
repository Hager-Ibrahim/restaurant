package com.example.restaurant.di.module;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

@Module
public class ExecutorModule {

    private static ExecutorModule instance;

    public static ExecutorModule getInstance(){
        if(instance == null){
            instance = new ExecutorModule();
        }
        return instance;
    }

    @Provides
    @Singleton
    public ScheduledExecutorService providesNetworkIO(){
        final ScheduledExecutorService mNetworkIO = Executors.newScheduledThreadPool(3);
        return mNetworkIO;
    }
}
