package com.example.restaurant.ui.fragment.login;

import androidx.databinding.Bindable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import com.example.restaurant.data.model.api.BasicResponse;
import com.example.restaurant.data.model.api.UserData;
import com.example.restaurant.data.model.dataBinding.Login;

import javax.inject.Inject;

public class LoginViewModel extends ViewModel {
    private LoginRepository repository;

    @Inject
    public void setRepository(LoginRepository repository) {
        this.repository = repository;
    }

    public static LoginViewModel create(FragmentActivity activity) {
        LoginViewModel viewModel = ViewModelProviders.of(activity).get(LoginViewModel.class);
        return viewModel;
    }

    private MutableLiveData<BasicResponse<UserData>> mutableLiveData;

    public LiveData<BasicResponse<UserData>> getResult(String email, String password, Login login) {
        mutableLiveData = repository.getData(email, password, login );
        return mutableLiveData;
    }





}
