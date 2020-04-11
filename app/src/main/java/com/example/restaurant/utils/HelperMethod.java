package com.example.restaurant.utils;

import android.app.Activity;
import android.content.Context;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.loader.content.CursorLoader;

import com.example.restaurant.ui.fragment.BaseFragment;

import java.io.File;
import java.util.Locale;

import es.dmoral.toasty.Toasty;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


public class HelperMethod {


    public static void openFragment(AppCompatActivity activity,
                                            int fragmentContainer,
                                            BaseFragment fragment){
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

    public static void showErrorToast(Context context, String msg){
        Toasty.error(context, msg, Toast.LENGTH_SHORT).show();
    }
    public static BroadcastReceiverImp getDynamicBroadcastReceiver(Context context){
        BroadcastReceiverImp broadcastReceiverImp = new BroadcastReceiverImp();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        context.registerReceiver(broadcastReceiverImp, filter);
        return broadcastReceiverImp;
    }

    public static void transparentStatusBar(Activity activity){
        // transparent status bar
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public static void showStatusBar(Activity activity){
        activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public static BroadcastReceiverImp getBroadcastReceiver(Context context){
         return HelperMethod.getDynamicBroadcastReceiver(context);
    }

    public static void setSpinnerFocused(Spinner spinner){
        spinner.setFocusable(true);
        spinner.setFocusableInTouchMode(true);
        spinner.requestFocus();
    }

    public static RequestBody convertStringToRequestBody(String text){
        try {
            if (!text.equals("")) {
                RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), text);
                return requestBody;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public static MultipartBody.Part convertFileToMultipart(String pathImageFile, String Key) {
        if (pathImageFile != null) {
            File file = new File(pathImageFile);
            RequestBody reqFileselect = RequestBody.create(MediaType.parse("image/*"), file);
            MultipartBody.Part Imagebody = MultipartBody.Part.createFormData(Key, file.getName(), reqFileselect);
            return Imagebody;
        } else {
            return null;
        }
    }

    public static String getImagePathFromUri(Context context, Uri uri){

        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        CursorLoader cursorLoader = new CursorLoader(context,uri,filePathColumn,null,null,null);
        Cursor cursor = cursorLoader.loadInBackground();
        int colum_index= cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(colum_index);
        cursor.close();

        return result;
    }

    /*

    public static String getImagePathFromUri(Context context, Uri uri){

        String filePath = "";
        String wholeID = DocumentsContract.getDocumentId(uri);

        // Split at colon, use second item in the array
        String id = wholeID.split(":")[1];

        String[] column = { MediaStore.Images.Media.DATA };

        // where id is equal to
        String sel = MediaStore.Images.Media._ID + "=?";

        Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                column, sel, new String[]{ id }, null);

        int columnIndex = cursor.getColumnIndex(column[0]);

        if (cursor.moveToFirst()) {
            filePath = cursor.getString(columnIndex);
        }
        cursor.close();
        return filePath;
    }

     */

}


