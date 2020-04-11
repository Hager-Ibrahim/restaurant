package com.example.restaurant.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.restaurant.R;
import com.example.restaurant.data.model.api.userCycle.Region;

import java.util.ArrayList;
import java.util.List;

public class SpinnerCustomAdapter extends BaseAdapter {

    private Context context;
    private List<Region> regionList = new ArrayList<>();
    private LayoutInflater inflater;

    public SpinnerCustomAdapter(Context applicationContext) {
        this.context = applicationContext;
        inflater = (LayoutInflater.from(applicationContext));
    }

    public void setData(List<Region> regionList, String hint) {
        regionList.add(0,new Region( hint));
        this.regionList = regionList;
    }



    @Override
    public int getCount() {
        if(regionList.size() < 7)
        return regionList.size();
        else return 7;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.item_spinner, null);

        TextView names =  view.findViewById(R.id.item_spinner_text);
        TextView cityId =  view.findViewById(R.id.city_id);

        names.setText(regionList.get(i).getName());
        cityId.setText(String.valueOf(regionList.get(i).getId()));
        return view;
    }
}
