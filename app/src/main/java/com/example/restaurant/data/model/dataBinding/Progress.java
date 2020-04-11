package com.example.restaurant.data.model.dataBinding;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

public class Progress extends BaseObservable {

    public boolean hideProgressBar;

    public Progress(){}

    @Bindable
    public boolean isHideProgressBar() {
        return hideProgressBar;
    }

    public void setHideProgressBar(boolean hideProgressBar) {
        this.hideProgressBar = hideProgressBar;
        notifyPropertyChanged(BR.hideProgressBar);
    }
}
