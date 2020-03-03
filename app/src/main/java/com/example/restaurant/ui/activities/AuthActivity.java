package com.example.restaurant.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.WindowManager;

import com.example.restaurant.R;
import com.example.restaurant.databinding.ActivityAuthBinding;
import com.example.restaurant.ui.fragment.LoginFragment;
import com.example.restaurant.utils.HelperMethod;

public class AuthActivity extends AppCompatActivity {

    ActivityAuthBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_auth);
        // transcript status bar
        HelperMethod.transcriptStatusBar(this);
        // open home fragment as default
        HelperMethod.openFragment(this,R.id.home_fragment_container,new LoginFragment());
        // change language
        HelperMethod.changeLang(this,"ar");

    }
}
