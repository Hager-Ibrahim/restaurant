package com.example.restaurant.ui.fragment.splash;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.restaurant.R;
import com.example.restaurant.data.model.dataBinding.Splash;
import com.example.restaurant.databinding.FragmentSplashBinding;
import com.example.restaurant.ui.fragment.BaseFragment;
import com.example.restaurant.ui.fragment.login.LoginFragment;
import com.example.restaurant.utils.HelperMethod;


public class SplashFragment extends BaseFragment {

    FragmentSplashBinding mBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_splash, container, false);
        View view = mBinding.getRoot();

        //view binding
        Splash splash = new Splash(getActivity().getResources().getString(R.string.splash_word),true);
        mBinding.setSplash(splash);

        return view;
    }

    private static int SPLASH_TIME = 4000;

    @BindingAdapter("animation")
    public static void setAnimation(final TextView view, boolean isAnimation) {
        if (isAnimation) {

            // set handler with specific moments
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    HelperMethod.openFragment((AppCompatActivity) view.getContext(),
                            R.id.auth_fragment_container,
                            new LoginFragment());
                }
            }, SPLASH_TIME);
        }

    }
}
