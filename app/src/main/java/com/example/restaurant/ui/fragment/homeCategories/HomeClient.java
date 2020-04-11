package com.example.restaurant.ui.fragment.homeCategories;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.restaurant.data.model.api.general.BasicResponse;
import com.example.restaurant.data.model.api.general.Page;
import com.example.restaurant.data.model.api.homeCycle.Category;
import com.example.restaurant.data.model.dataBinding.Progress;
import com.example.restaurant.data.remote.RestaurantApi;
import com.example.restaurant.utils.HelperMethod;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.restaurant.utils.Constants.API_TOKEN;
import static com.example.restaurant.utils.Constants.IMAGE_KEY;
import static com.example.restaurant.utils.Constants.NETWORK_TIMEOUT;
import static com.example.restaurant.utils.HelperMethod.convertFileToMultipart;
import static com.example.restaurant.utils.HelperMethod.convertStringToRequestBody;
import static com.example.restaurant.utils.HelperMethod.showInfoToast;
import static com.example.restaurant.utils.HelperMethod.showSuccessToast;

public class HomeClient {

    final private RestaurantApi api;
    final private ScheduledExecutorService executor;
    Application application;
    private MutableLiveData<List<Category>> categoriesLiveData = new MutableLiveData<>();
    private MutableLiveData<Category> categoryDialogLiveData = new MutableLiveData<>();

    RetrieveCategoriesRunnable mRetrieveCategoriesRunnable;


    @Inject
    public HomeClient(RestaurantApi api,
                                 ScheduledExecutorService executor,
                                 Application application) {
        this.api = api;
        this.executor = executor;
        this.application = application;

    }


    public void getCategoriesByPage(int page){

        if(mRetrieveCategoriesRunnable != null){
            mRetrieveCategoriesRunnable = null;
        }

        mRetrieveCategoriesRunnable = new RetrieveCategoriesRunnable(page);
        Future handler = executor.submit(mRetrieveCategoriesRunnable);
        executor.schedule(new Runnable() {
            @Override
            public void run() {
                handler.cancel(true);
            }
        }, NETWORK_TIMEOUT, TimeUnit.MILLISECONDS);

    }


    private class RetrieveCategoriesRunnable implements Runnable{

        int page;

        public RetrieveCategoriesRunnable(int page) {
            this.page = page;
        }

        @Override
        public void run() {
            try {
                Response<BasicResponse<Page<Category>>> response = api.getCategories(API_TOKEN, page).execute();
                if(response.isSuccessful()){
                    if(response.body().getStatus() == 1){
                        List<Category> categoryList = response.body().getData().getData();
                        categoriesLiveData.postValue(categoryList);
                    }
                    else {
                        HelperMethod.showInfoToast(application.getApplicationContext(), response.body().getMsg());
                        categoriesLiveData.postValue(null);
                    }
                }
                else {
                    HelperMethod.showErrorToast(application.getApplicationContext(), response.errorBody().string());
                    categoriesLiveData.postValue(null);

                }
            } catch (IOException e) {
                e.printStackTrace();
                categoriesLiveData.postValue(null);
            }
        }


    }

    public MutableLiveData<List<Category>> getCategoriesLiveData(){
        return categoriesLiveData;
    }

    public void addCategory(String categoryName, String imagePath, Progress progress){

       Call<BasicResponse<Category>> call = api.addCategory(
               convertStringToRequestBody(categoryName),
               convertFileToMultipart(imagePath, IMAGE_KEY),
               convertStringToRequestBody(API_TOKEN));
       progress.setHideProgressBar(false);

       call.enqueue(new Callback<BasicResponse<Category>>() {
           @Override
           public void onResponse(Call<BasicResponse<Category>> call, Response<BasicResponse<Category>> response) {
               if(response.isSuccessful()){
                   progress.setHideProgressBar(true);
                   HelperMethod.showSuccessToast(application.getBaseContext(), response.body().getMsg());
               }
               else {
                   progress.setHideProgressBar(true);
                   Log.v("1111",response.body().getMsg() );

                   HelperMethod.showInfoToast(application.getBaseContext(), response.body().getMsg());
               }
           }

           @Override
           public void onFailure(Call<BasicResponse<Category>> call, Throwable t) {
               progress.setHideProgressBar(true);
               Log.v("1111",t.getMessage() );
               HelperMethod.showInfoToast(application.getBaseContext(), t.getMessage());
           }
       });

    }


    public void updateCategory(Category category, String imagePath, String categoryId, Progress progress){

        Call<BasicResponse<Category>> call = api.updateCategory(
                convertStringToRequestBody(category.getName()),
                convertFileToMultipart(imagePath, IMAGE_KEY),
                convertStringToRequestBody(API_TOKEN),
                convertStringToRequestBody(categoryId));
        progress.setHideProgressBar(false);

        call.enqueue(new Callback<BasicResponse<Category>>() {
            @Override
            public void onResponse(Call<BasicResponse<Category>> call, Response<BasicResponse<Category>> response) {
                if(response.isSuccessful()){
                    progress.setHideProgressBar(true);
                    HelperMethod.showSuccessToast(application.getBaseContext(), response.body().getMsg());
                }
                else {
                    progress.setHideProgressBar(true);
                    Log.v("1111",response.body().getMsg() );

                    HelperMethod.showInfoToast(application.getBaseContext(), response.body().getMsg());
                }
            }

            @Override
            public void onFailure(Call<BasicResponse<Category>> call, Throwable t) {
                progress.setHideProgressBar(true);
                Log.v("1111",t.getMessage() );
                HelperMethod.showInfoToast(application.getBaseContext(), t.getMessage());
            }
        });

    }

    public void deleteCategory(String categoryId){
        api.deleteCategory(API_TOKEN, categoryId).enqueue(new Callback<BasicResponse>() {
            @Override
            public void onResponse(Call<BasicResponse> call, Response<BasicResponse> response) {
                if (response.body().getStatus() == 1) {
                    showSuccessToast(application.getBaseContext(),response.body().getMsg());
                }
                else {
                    showInfoToast(application.getBaseContext(),response.body().getMsg());

                }
            }

            @Override
            public void onFailure(Call<BasicResponse> call, Throwable t) {
                showInfoToast(application.getBaseContext(),t.getMessage());
            }
        });
    }



}
