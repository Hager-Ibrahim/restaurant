package com.example.restaurant.ui.fragment.registerRestaurant;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.restaurant.R;
import com.example.restaurant.data.model.api.userCycle.Region;
import com.example.restaurant.data.model.dataBinding.Progress;
import com.example.restaurant.data.model.dataBinding.Register;
import com.example.restaurant.databinding.FragmentRegisterStepOneBinding;
import com.example.restaurant.ui.fragment.BaseFragment;
import com.example.restaurant.utils.HelperMethod;
import com.example.restaurant.utils.MyApplication;
import com.example.restaurant.utils.SpinnerCustomAdapter;

import java.util.List;
import static android.app.Activity.RESULT_OK;

import static com.example.restaurant.utils.HelperMethod.getImagePathFromUri;
import static com.example.restaurant.utils.HelperMethod.showInfoToast;


public class RegisterRestaurantFragment extends BaseFragment {

    FragmentRegisterStepOneBinding mBinding;
    RegisterRestaurantViewModel modelView;
    String regionId, imagePath;
    Register register;
    Progress progress;
    private static final int REQUEST_CODE = 2 ;
    private static final int GALLERY_REQUEST = 2 ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_register_step_one, container, false);
        View view = mBinding.getRoot();

        setUpActivity();

        progress = new Progress();
        register = new Register();

        mBinding.setRegister(register);
        mBinding.setProgress(progress);
        mBinding.setRegisterFragment(this);

        //get an instance of our viewmodel and get things injected...
        modelView = RegisterRestaurantViewModel.create(getActivity());
        MyApplication.getAppComponent().inject(modelView);

        HelperMethod.setSpinnerFocused(mBinding.spinner);
        HelperMethod.setSpinnerFocused(mBinding.spinner2);


        citySpinnerSetting(mBinding.spinner, mBinding.spinner2);


        return view;

    }


    public void citySpinnerSetting(Spinner citySpinner , Spinner regionSpinner){

        modelView.getCities().observe(this, new Observer<List<Region>>() {
            @Override
            public void onChanged(List<Region> cities) {

                SpinnerCustomAdapter spinnerCustomAdapter = new SpinnerCustomAdapter(getContext());
                spinnerCustomAdapter.setData(cities,getResources().getString(R.string.choose_city));
                citySpinner.setAdapter(spinnerCustomAdapter);
            }
        });

        modelView.getRegionsByCityId("1");


        citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                ConstraintLayout constraintLayout = (ConstraintLayout) citySpinner.getSelectedView();
                TextView textView = constraintLayout.findViewById(R.id.city_id);
                Toast.makeText(getContext(), textView.getText().toString(), Toast.LENGTH_LONG).show();
                modelView.getRegionsByCityId(textView.getText().toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        modelView.getRegions().observe(RegisterRestaurantFragment.this, new Observer<List<Region>>() {
            @Override
            public void onChanged(List<Region> regions) {

                SpinnerCustomAdapter spinnerCustomAdapter = new SpinnerCustomAdapter(getContext());
                spinnerCustomAdapter.setData(regions, getResources().getString(R.string.choose_region));
                mBinding.spinner2.setAdapter(spinnerCustomAdapter);


            }
        });

        regionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ConstraintLayout constraintLayout = (ConstraintLayout) regionSpinner.getSelectedView();
                TextView textView = constraintLayout.findViewById(R.id.city_id);
                regionId = textView.getText().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }




    public void onImageClick(View view){
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
            Uri pickedImgUri = data.getData();
            imagePath = getImagePathFromUri(getActivity(), pickedImgUri);
            Toast.makeText(getContext(), String.valueOf(imagePath), Toast.LENGTH_LONG).show();

        }
    }

    public  void onNextButtonClick(View view){

        if(imagePath == null){
            showInfoToast(view.getContext(),"Choose an image");
        }
        else {
            Log.v("image path", imagePath);

            modelView.register(progress,register,regionId,imagePath);
        }

    }

    //swift keyboard
    //minimize screen
    //spinner to less size

}
