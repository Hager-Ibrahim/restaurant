package com.example.restaurant.data.model.dataBinding;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableField;
import androidx.databinding.library.baseAdapters.BR;

public class Login extends BaseObservable{
    public final ObservableField<String> email =  new ObservableField<>("");
    public final ObservableField<String> password = new ObservableField<>("");
    public final ObservableField<String> newPassword = new ObservableField<>("");

    public boolean hideProgress;
    public Login() {}

    public ObservableField<String> getEmail() {
        return email;
    }

    public ObservableField<String> getPassword() {
        return password;
    }

    public ObservableField<String> getNewPassword() {
        return newPassword;
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
