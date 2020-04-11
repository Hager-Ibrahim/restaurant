package com.example.restaurant.ui.fragment.newPassword;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.restaurant.R;
import com.example.restaurant.data.model.api.general.BasicResponse;
import com.example.restaurant.data.model.dataBinding.Login;
import com.example.restaurant.databinding.FragmentResetPasswordBinding;
import com.example.restaurant.ui.activities.HomeActivity;
import com.example.restaurant.ui.fragment.BaseFragment;
import com.example.restaurant.utils.BroadcastReceiverImp;
import com.example.restaurant.utils.HelperMethod;
import com.example.restaurant.utils.MyApplication;

import static com.example.restaurant.utils.Constants.KEY_CODE;


public class ResetPasswordFragment extends BaseFragment {


    FragmentResetPasswordBinding mBinding;
    private Login login;
    ResetViewModel modelView;
    BroadcastReceiverImp broadcastReceiverImp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_reset_password, container, false);
        View view = mBinding.getRoot();
        setUpActivity();

        login = new Login();
        //data binding
        mBinding.setLogin(login);
        mBinding.setResetFragment(this);
        //get an instance of our viewmodel and get things injected...
        modelView = modelView.create(getActivity());
        MyApplication.getAppComponent().inject(modelView);

        // check internet
        broadcastReceiverImp = HelperMethod.getDynamicBroadcastReceiver(getContext());

        String code = getArguments().getString(KEY_CODE);

        return view;
    }

    public void onClick(View view ){

        // get mail and password
        String code = login.email.get();
        String password = login.password.get();
        String newPassword = login.newPassword.get();

        if (broadcastReceiverImp.isConnected()) {
            //Let's observe the result and update our UI upon changes
            modelView.getResult(code,password, newPassword, login ).observe(getActivity(), new Observer<BasicResponse>() {
                @Override
                public void onChanged(BasicResponse listResource) {
                    //open Activity
                    Intent openHomeActivity = new Intent(getContext(), HomeActivity.class);
                    getContext().startActivity(openHomeActivity);
                }
            });
        }

        else {
            login.setHideProgress(false);
            HelperMethod.showInfoToast(getContext(),getActivity().getResources().getString(R.string.check_internet));
        }

    }

}
