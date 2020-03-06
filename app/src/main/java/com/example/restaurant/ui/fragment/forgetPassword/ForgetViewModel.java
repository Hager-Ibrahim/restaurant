package com.example.restaurant.ui.fragment.forgetPassword;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import com.example.restaurant.data.model.api.BasicResponse;
import com.example.restaurant.data.model.api.userCycle.ForgetPassword;
import com.example.restaurant.data.model.api.userCycle.UserData;
import com.example.restaurant.data.model.dataBinding.Login;
import com.example.restaurant.ui.fragment.login.LoginRepository;

import javax.inject.Inject;

public class ForgetViewModel extends ViewModel {
    private ForgetPasswordRepository repository;

    @Inject
    public void setRepository(ForgetPasswordRepository repository) {
        this.repository = repository;
    }

    public static ForgetViewModel create(FragmentActivity activity) {
        ForgetViewModel viewModel = ViewModelProviders.of(activity).get(ForgetViewModel.class);
        return viewModel;
    }

    private MutableLiveData<BasicResponse<ForgetPassword>> mutableLiveData;

    public LiveData<BasicResponse<ForgetPassword>> getResult(String email, Login login) {
        mutableLiveData = repository.getData(email,login);
        return mutableLiveData;
    }
}
