package com.example.restaurant.ui.fragment.newPassword;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import com.example.restaurant.data.model.api.BasicResponse;
import com.example.restaurant.data.model.api.userCycle.UserData;
import com.example.restaurant.data.model.dataBinding.Login;
import com.example.restaurant.ui.fragment.login.LoginRepository;
import com.example.restaurant.ui.fragment.login.LoginViewModel;

import javax.inject.Inject;

public class ResetViewModel extends ViewModel {

    private ResetPasswordRepository repository;

    @Inject
    public void setRepository(ResetPasswordRepository repository) {
        this.repository = repository;
    }

    public static ResetViewModel create(FragmentActivity activity) {
        ResetViewModel viewModel = ViewModelProviders.of(activity).get(ResetViewModel.class);
        return viewModel;
    }

    private MutableLiveData<BasicResponse> mutableLiveData;

    public LiveData<BasicResponse> getResult(String code, String password,String newPasswprd, Login login) {
        mutableLiveData = repository.getData(code, password, newPasswprd,login );
        return mutableLiveData;
    }
}
