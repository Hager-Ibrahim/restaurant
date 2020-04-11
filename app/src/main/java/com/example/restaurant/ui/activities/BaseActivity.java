package com.example.restaurant.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurant.ui.fragment.BaseFragment;

public class BaseActivity  extends AppCompatActivity {

    public BaseFragment baseFragment = new BaseFragment();

    public void superBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onBackPressed() {
        baseFragment.onBack();
    }
}
