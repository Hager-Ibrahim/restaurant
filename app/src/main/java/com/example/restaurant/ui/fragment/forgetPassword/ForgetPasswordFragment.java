package com.example.restaurant.ui.fragment.forgetPassword;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.restaurant.R;
import com.example.restaurant.data.model.api.general.BasicResponse;
import com.example.restaurant.data.model.api.userCycle.ForgetPassword;
import com.example.restaurant.data.model.dataBinding.Login;
import com.example.restaurant.databinding.FragmentForgetPasswordBinding;
import com.example.restaurant.ui.fragment.BaseFragment;
import com.example.restaurant.ui.fragment.newPassword.ResetPasswordFragment;
import com.example.restaurant.utils.BroadcastReceiverImp;
import com.example.restaurant.utils.HelperMethod;
import com.example.restaurant.utils.MyApplication;

import static com.example.restaurant.utils.Constants.KEY_CODE;

public class ForgetPasswordFragment extends BaseFragment {

    BroadcastReceiverImp broadcastReceiverImp;
    FragmentForgetPasswordBinding mBinding;
    ForgetViewModel modelView;
    Login login;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_forget_password, container, false);
        View view = mBinding.getRoot();

        //data binding
        login = new Login();
        mBinding.setLogin(login);
        mBinding.setForgetFragment(this);

        setUpActivity();

        // Show status bar
        HelperMethod.showStatusBar(getActivity());

        broadcastReceiverImp = HelperMethod.getDynamicBroadcastReceiver(getContext());
        //get an instance of our viewmodel and get things injected...
        modelView = ForgetViewModel.create(getActivity());
        MyApplication.getAppComponent().inject(modelView);

        return view;
    }


    @Override
    public void onStop() {
        super.onStop();
        getContext().unregisterReceiver(broadcastReceiverImp);
    }

    public void onClick(View view){

        String mail = login.email.get();
        if(broadcastReceiverImp.isConnected()){
            modelView.getResult(mail,login ).observe(getActivity(), new Observer<BasicResponse<ForgetPassword>>() {
                @Override
                public void onChanged(BasicResponse<ForgetPassword> listResource) {
                    //open Fragment
                    HelperMethod.openFragmentSendData((AppCompatActivity) getActivity(),
                            R.id.auth_fragment_container,
                            new ResetPasswordFragment(),
                            KEY_CODE,String.valueOf(listResource.getData().getCode()));
                }
            });
        }
        else {
            login.setHideProgress(false);
            HelperMethod.showInfoToast(getContext(),getActivity().getResources().getString(R.string.check_internet));
        }
    }
}
