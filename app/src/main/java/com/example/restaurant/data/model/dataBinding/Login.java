package com.example.restaurant.data.model.dataBinding;

import androidx.databinding.BaseObservable;
import androidx.databinding.ObservableField;

public class Login extends BaseObservable{
    public final ObservableField<String> email =  new ObservableField<>("");
    public final ObservableField<String> password = new ObservableField<>("");

    public Login() {}

    public ObservableField<String> getEmail() {
        return email;
    }

    public ObservableField<String> getPassword() {
        return password;
    }

}
