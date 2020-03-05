package com.example.restaurant.ui.fragment.login;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import com.example.restaurant.R;
import com.example.restaurant.data.local.prefs.AppPreference;
import com.example.restaurant.data.model.api.BasicResponse;
import com.example.restaurant.data.model.api.UserData;
import com.example.restaurant.data.model.dataBinding.Login;
import com.example.restaurant.databinding.FragmentLoginBinding;
import com.example.restaurant.ui.activities.HomeActivity;
import com.example.restaurant.utils.MyApplication;
import com.example.restaurant.utils.BroadcastReceiverImp;

import static com.example.restaurant.utils.Constants.KEY_API_TOKEN;


public class LoginFragment extends Fragment {

    private static Login login;
    FragmentLoginBinding mBinding;
    LoginViewModel modelView;
    BroadcastReceiverImp broadcastReceiverImp;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_login, container, false);
        View view = mBinding.getRoot();

        login = new Login();

        //data binding
        mBinding.setLogin(login);
        mBinding.setLoginFragment(this);
        //get an instance of our viewmodel and get things injected...
        modelView = LoginViewModel.create(getActivity());
        MyApplication.getAppComponent().inject(modelView);

        // check internet
        broadcastReceiverImp = new BroadcastReceiverImp();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        getContext().registerReceiver(broadcastReceiverImp, filter);

        return view;
    }

    public void onClick(View view ){

        // get mail and password
        String mail = getLogin().email.get();
        String password = getLogin().password.get();

        if (broadcastReceiverImp.isConnected()) {
            //Let's observe the result and update our UI upon changes
            modelView.getResult(mail,password, getLogin() ).observe(getActivity(), new Observer<BasicResponse<UserData>>() {
                @Override
                public void onChanged(BasicResponse<UserData> listResource) {
                    AppPreference.SaveData(getActivity(),KEY_API_TOKEN,listResource.getData().getApiToken());
                    //open Activity
                    Intent openHomeActivity = new Intent(getContext(), HomeActivity.class);
                    getContext().startActivity(openHomeActivity);
                }
            });
        }

        else {
            Toast.makeText(getContext(), "Disconnected", Toast.LENGTH_SHORT).show();
        }

    }

    public static Login getLogin(){
        return login;
    }

    // minimize screen
    // remember me
    // back from fragment to another
    //edit text features coding in flow
    //login from different account will this change the api key
}
