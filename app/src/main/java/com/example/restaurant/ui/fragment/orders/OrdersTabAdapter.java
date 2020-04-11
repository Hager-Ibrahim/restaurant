package com.example.restaurant.ui.fragment.orders;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.restaurant.R;
import com.example.restaurant.ui.fragment.orderCompleted.OrderCompletedFragment;
import com.example.restaurant.ui.fragment.orderCurrent.OrderCurrentFragment;
import com.example.restaurant.ui.fragment.orderPending.OrderPendingFragment;

public class OrdersTabAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES =
            new int[] { R.string.tab_pending_orders ,
                    R.string.tab_current_orders,
                    R.string.tab_compeleted_order };
    private final Context mContext;

    public OrdersTabAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new OrderPendingFragment();
            case 1:
                return new OrderCurrentFragment() ;
            case 2:
                return new OrderCompletedFragment();
            default:
                return  new OrderPendingFragment();
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return 3;
    }
}
