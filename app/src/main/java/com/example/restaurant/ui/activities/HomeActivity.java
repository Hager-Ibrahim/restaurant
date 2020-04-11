package com.example.restaurant.ui.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import com.example.restaurant.R;
import com.example.restaurant.databinding.ActivityHomeBinding;
import com.example.restaurant.ui.fragment.orders.OrdersFragment;
import com.example.restaurant.ui.fragment.homeCategories.HomeFragment;
import com.example.restaurant.utils.HelperMethod;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    ActivityHomeBinding mBinding;
    BottomNavigationView.OnNavigationItemSelectedListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_home);
        mBinding.setHomeActivity(this);

        // change language
        HelperMethod.changeLang(this,"ar");
    }

    @BindingAdapter("onNavigationItemSelected")
    public static void setOnNavigationItemSelected(
            BottomNavigationView view, BottomNavigationView.OnNavigationItemSelectedListener listener) {
        view.setOnNavigationItemSelectedListener(listener);
    }

    @BindingAdapter("selectedItemPosition")
    public static void setSelectedItemPosition(
            BottomNavigationView view, int position) {
        view.setSelectedItemId(position);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment selectedFragment = null;

        switch (item.getItemId()) {
            case R.id.nav_home:
                selectedFragment = new HomeFragment();
                break;
            case R.id.nav_order:
                selectedFragment = new OrdersFragment();
                break;
            case R.id.nav_user:
                selectedFragment = new OrdersFragment();
                break;
            case R.id.nav_more:
                selectedFragment = new OrdersFragment();


        }
        getSupportFragmentManager().beginTransaction().replace(
                R.id.home_fragment_container, selectedFragment).commit();

        return true;
    }


}
