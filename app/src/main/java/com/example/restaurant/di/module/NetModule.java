package com.example.restaurant.di.module;

import android.app.Application;
import android.util.Log;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetModule {
    final private String baseUrl;

    public NetModule(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Singleton
    @Provides
    public Cache provideHttpCache(Application application) {
        long cacheSize = 10 * 1024 * 1024L;
        return new Cache(application.getCacheDir(), cacheSize);
    }

    @Singleton
    @Provides
    public OkHttpClient providesOkHttpClient(Cache cache, Interceptor interceptor){
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .cache(cache)
                .build();
    }

    @Singleton
    @Provides
    public Gson providesGson(){
        return new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }

    @Singleton
    @Provides
    public Retrofit providesRetrofit(Gson gson, OkHttpClient okHttpClient){
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    public Interceptor provideInterceptor(){
        return (Interceptor)(new Interceptor() {
            public final Response intercept(Chain chain) throws IOException {
                Request request = chain.request();

                Request.Builder requestBuilder = request.newBuilder();
                HttpUrl url = request.url().newBuilder()
                        .addQueryParameter("format", "json")
                        .build();
                Log.d("URL", url.toString());
                return chain.proceed(request);
            }
        });
    }
}
