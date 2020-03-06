package com.example.restaurant.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.WindowManager;

import com.example.restaurant.R;
import com.example.restaurant.databinding.ActivityAuthBinding;
import com.example.restaurant.ui.fragment.splash.SplashFragment;
import com.example.restaurant.utils.HelperMethod;

public class AuthActivity extends AppCompatActivity {

    ActivityAuthBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_auth);


        // transcript status bar
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // open home fragment as default
        HelperMethod.openFragment(this,R.id.auth_fragment_container,new SplashFragment());
        // change language
        HelperMethod.changeLang(this,"ar");

    }
}
