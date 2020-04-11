package com.example.restaurant.data.model.dataBinding;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

public class Home extends BaseObservable {

    private boolean hideText;

    public Home(){}

    @Bindable
    public boolean isHideText() {
        return hideText;
    }

    public void setHideText(boolean hideText) {
        this.hideText = hideText;
        notifyPropertyChanged(BR.hideText);
    }
}
