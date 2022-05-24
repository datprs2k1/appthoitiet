package com.example.thoitiet.Model;

import java.util.ArrayList;
import java.util.List;

public class DataResponse {
    private City city;
    private String cod;
    private String message;
    private Integer cnt;
    private List<LWeather> list;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public List<LWeather> getList() {
        return list;
    }

    public void setList(List<LWeather> list) {
        this.list = list;
    }
}
