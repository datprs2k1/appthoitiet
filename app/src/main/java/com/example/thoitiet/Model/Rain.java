package com.example.thoitiet.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Rain implements Serializable {
    @SerializedName("3h")
    private String threeh;

    public String getThreeh() {
        return threeh;
    }

    public void setThreeh(String threeh) {
        this.threeh = threeh;
    }
}
