package com.example.restaurant.data.model.api.userCycle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ForgetPassword {
    @SerializedName("code")
    @Expose
    private Integer code;

    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
}
