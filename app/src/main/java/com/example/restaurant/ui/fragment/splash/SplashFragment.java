package com.example.restaurant.ui.fragment.splash;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.restaurant.R;
import com.example.restaurant.data.model.dataBinding.Splash;
import com.example.restaurant.databinding.FragmentSplashBinding;
import com.example.restaurant.ui.fragment.login.LoginFragment;


public class SplashFragment extends Fragment {

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

            Animation animation = AnimationUtils.loadAnimation(view.getContext(), R.anim.splash_anim);
            view.startAnimation(animation);
            // set handler with specific moments
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    ((AppCompatActivity) view.getContext()).getSupportFragmentManager().beginTransaction()
                            .replace(R.id.auth_fragment_container,
                                    new LoginFragment()).commit();
                }
            }, SPLASH_TIME);
        }

    }
}
