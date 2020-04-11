package com.example.restaurant.ui.fragment;



import androidx.fragment.app.Fragment;

import com.example.restaurant.ui.activities.BaseActivity;

public class BaseFragment extends Fragment {


    public BaseActivity baseActivity;


    public void setUpActivity() {

        baseActivity = (BaseActivity) getActivity();

        baseActivity.baseFragment = this;

    }

    public void onBack() {

        baseActivity.superBackPressed();

    }

}
