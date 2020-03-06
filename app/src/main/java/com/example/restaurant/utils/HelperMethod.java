package com.example.restaurant.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import java.util.Locale;

import es.dmoral.toasty.Toasty;


public class HelperMethod {


    public static void openFragment(AppCompatActivity activity,
                                            int fragmentContainer,
                                            Fragment fragment){
        activity.getSupportFragmentManager().beginTransaction().replace(fragmentContainer,
                fragment).commit();
    }
    public static void openFragmentSendData(AppCompatActivity activity,
                                    int fragmentContainer,
                                    Fragment fragment,
                                    String key,
                                    String value){
        Bundle args = new Bundle();
        args.putString(key, value);
        fragment.setArguments(args);
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

    public static void showInfoToast(Context context, String msg){
        Toasty.info(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void showSuccessToast(Context context, String msg){
        Toasty.success(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static BroadcastReceiverImp getBroadcastReceiver(Context context){
        BroadcastReceiverImp broadcastReceiverImp = new BroadcastReceiverImp();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        context.registerReceiver(broadcastReceiverImp, filter);
        return broadcastReceiverImp;
    }

}
