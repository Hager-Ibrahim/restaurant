package com.example.restaurant.ui.fragment.homeCategories;


import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.restaurant.R;
import com.example.restaurant.data.model.api.general.Page;
import com.example.restaurant.data.model.api.homeCycle.Category;
import com.example.restaurant.data.model.dataBinding.Home;
import com.example.restaurant.data.model.dataBinding.Progress;
import com.example.restaurant.databinding.DialogAddBinding;
import com.example.restaurant.databinding.FragmentHomeBinding;
import com.example.restaurant.ui.fragment.BaseFragment;
import com.example.restaurant.utils.HelperMethod;
import com.example.restaurant.utils.MyApplication;

import java.util.List;

import static android.app.Activity.RESULT_OK;
import static com.example.restaurant.utils.HelperMethod.getImagePathFromUri;


public class HomeFragment extends BaseFragment {

    FragmentHomeBinding mBinding;
    DialogAddBinding dialogBinding;
    Dialog addDialog;
    HomeViewModel modelView;
    private Uri pickedImgUri = null;
    Home home;
    Progress progress;
    int page = 1;
    private static final int REQUEST_CODE = 2 ;
    private static final int GALLERY_REQUEST = 2 ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_home, container, false);
        View view = mBinding.getRoot();
        mBinding.setHomeFragment(this);


        modelView = HomeViewModel.create(getActivity());
        MyApplication.getAppComponent().inject(modelView);


        modelView.getCategoriesByPage(page);
        getCategories(modelView);

        return view;
    }


    public void getCategories(HomeViewModel mModelView){

        mModelView.getCategories().observe(getActivity(), new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {

                if (!categories.isEmpty()) {
                    mBinding.recycler.setLayoutManager(new LinearLayoutManager(getContext()));
                    mBinding.recycler.setAdapter(new HomeAdapter(getActivity(),categories));

                }

            }
        });
    }

    public void addCategory(View view){
        Toast.makeText(getContext(), "uu", Toast.LENGTH_LONG).show();
        //progress.setHideProgressBar(true);

       modelView.addCategory(dialogBinding.popUpDes.getText().toString(),
               getImagePathFromUri(getContext(),pickedImgUri),
               progress);

    }

    public void openDialog(View view){
        //binding dialog
        dialogBinding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()),
                R.layout.dialog_add,
                null,
                false);
        dialogBinding.setHomeFragment(this);
        progress = new Progress();
        dialogBinding.setProgress(progress);
        home = new Home();

        dialogBinding.setHome(home);

        // show dialog
        addDialog = new Dialog(getActivity());
        addDialog.setContentView(dialogBinding.getRoot());
        addDialog.getWindow().setLayout(Toolbar.LayoutParams.MATCH_PARENT,Toolbar.LayoutParams.WRAP_CONTENT);
        addDialog.getWindow().getAttributes().gravity= Gravity.TOP ;
        addDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        addDialog.show();
    }


    public void imageOnClick(View view){
        checkAndRequestForPermission();
    }

    private void checkAndRequestForPermission() {

        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {

                HelperMethod.showInfoToast(getContext(), "Please accept for required permission");
            }

            else
            {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE);
            }

        }
        else
            // everything goes well : we have permission to access user gallery
            openGallery();

    }

    private void openGallery() {

        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, GALLERY_REQUEST);
    }



    // when user picked an image ...
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == GALLERY_REQUEST && data != null ) {

            // the user has successfully picked an image
            // we need to save its reference to a Uri variable
            pickedImgUri = data.getData() ;
            dialogBinding.popUpImg.setImageURI(pickedImgUri);
            home.setHideText(true);
        }
    }
}
