package com.example.thoitiet.Model;

import com.google.gson.annotations.SerializedName;

public class Rain {
    @SerializedName("3h")
    private String threeh;

    public String getThreeh() {
        return threeh;
    }

    public void setThreeh(String threeh) {
        this.threeh = threeh;
    }
}
