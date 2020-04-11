package com.example.restaurant.data.model.dataBinding;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

public class OrderBinding extends BaseObservable {


    public boolean isPending;
    public boolean isCurrent;
    public boolean isCompleted;

    public OrderBinding(){}

    @Bindable
    public boolean isPending() {
        return isPending;
    }

    @Bindable
    public boolean isCurrent() {
        return isCurrent;
    }

    @Bindable
    public boolean isCompleted() {
        return isCompleted;
    }

    public void setPending(boolean pending) {
        this.isPending = pending;
        notifyPropertyChanged(BR.pending);
    }

    public void setCurrent(boolean current) {
        this.isCurrent = current;
        notifyPropertyChanged(BR.current);
    }

    public void setCompleted(boolean completed) {
        this.isCompleted = completed;
        notifyPropertyChanged(BR.completed);
    }
}
