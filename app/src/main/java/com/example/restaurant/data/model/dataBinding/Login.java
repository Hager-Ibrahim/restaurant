package com.example.restaurant.data.model.dataBinding;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableField;
import androidx.databinding.library.baseAdapters.BR;

public class Login extends BaseObservable{
    public final ObservableField<String> email =  new ObservableField<>("");
    public final ObservableField<String> password = new ObservableField<>("");
    public boolean hideProgress;
    public Login() {}


    public ObservableField<String> getEmail() {
        return email;
    }

    public ObservableField<String> getPassword() {
        return password;
    }
    @Bindable
    public boolean isHideProgress() {
        return hideProgress;
    }

    public void setHideProgress(boolean hideProgress) {
        this.hideProgress = hideProgress;
        notifyPropertyChanged(BR.hideProgress);
    }
}
