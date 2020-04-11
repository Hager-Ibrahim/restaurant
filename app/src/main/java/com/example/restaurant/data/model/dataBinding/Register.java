package com.example.restaurant.data.model.dataBinding;

import androidx.databinding.BaseObservable;
import androidx.databinding.ObservableField;

public class Register extends BaseObservable {

    public final ObservableField<String> name =  new ObservableField<>("");
    public final ObservableField<String> email =  new ObservableField<>("");
    public final ObservableField<String> deliveryTime =  new ObservableField<>("");
    public final ObservableField<String> confirmPassword =  new ObservableField<>("");
    public final ObservableField<String> password =  new ObservableField<>("");
    public final ObservableField<String> minOrder =  new ObservableField<>("");
    public final ObservableField<String> deliveryCharge =  new ObservableField<>("");
    public final ObservableField<String> phone =  new ObservableField<>("");
    public final ObservableField<String> whats =  new ObservableField<>("");

    public Register() {
    }

    public ObservableField<String> getName() {
        return name;
    }

    public ObservableField<String> getEmail() {
        return email;
    }

    public ObservableField<String> getDeliveryTime() {
        return deliveryTime;
    }

    public ObservableField<String> getConfirmPassword() {
        return confirmPassword;
    }

    public ObservableField<String> getPassword() {
        return password;
    }

    public ObservableField<String> getMinOrder() {
        return minOrder;
    }

    public ObservableField<String> getDeliveryCharge() {
        return deliveryCharge;
    }

    public ObservableField<String> getPhone() {
        return phone;
    }

    public ObservableField<String> getWhats() {
        return whats;
    }
}
