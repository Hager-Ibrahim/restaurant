package com.example.restaurant.data.local.prefs;

import android.app.Activity;
import android.content.SharedPreferences;

public class AppPreference {

    public static SharedPreferences sharedPreferences = null;


    public static void setSharedPreferences(Activity activity) {

        if (sharedPreferences == null) {

            sharedPreferences = activity.getSharedPreferences(

                    "Restaurant", activity.MODE_PRIVATE);

        }
    }

    public static void SaveData(Activity activity, String data_Key, String data_Value) {

        setSharedPreferences(activity);

        if (sharedPreferences != null) {

            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putString(data_Key, data_Value);

            editor.commit();

        } else {

            setSharedPreferences(activity);

        }

    }

    public static String LoadData(Activity activity, String data_Key) {

        setSharedPreferences(activity);
        return sharedPreferences.getString(data_Key, null);
    }

    public static void SaveInt(Activity activity, String data_Key, int data_Value) {

        setSharedPreferences(activity);

        if (sharedPreferences != null) {

            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putInt(data_Key, data_Value);

            editor.commit();

        } else {
            setSharedPreferences(activity);
        }

    }

    public static int LoadInt(Activity activity, String data_Key) {

        setSharedPreferences(activity);
        return sharedPreferences.getInt(data_Key, 0);
    }

    public static void SaveBoolean(Activity activity, String data_Key, Boolean data_Value) {

        setSharedPreferences(activity);

        if (sharedPreferences != null) {

            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putBoolean(data_Key, data_Value);

            editor.commit();

        } else {
            setSharedPreferences(activity);
        }

    }

    public static boolean LoadBoolean(Activity activity, String data_Key) {

        setSharedPreferences(activity);
        return sharedPreferences.getBoolean(data_Key, true);
    }


}
