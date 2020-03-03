package com.example.restaurant.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import java.util.Locale;


public class HelperMethod {

    public static void transcriptStatusBar(Activity activity){

            activity.getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            );
    }

    public static void openFragment(AppCompatActivity activity, int fragmentContainer, Fragment fragment){
        activity.getSupportFragmentManager().beginTransaction().replace(fragmentContainer,
                fragment).commit();
    }

    public static void changeLang(Context context, String lang) {

        Resources res = context.getResources();
        // Change locale settings in the app.
        DisplayMetrics dm = res.getDisplayMetrics();
        android.content.res.Configuration conf = res.getConfiguration();
        conf.setLocale(new Locale(lang));
        res.updateConfiguration(conf, dm);


    }



}
