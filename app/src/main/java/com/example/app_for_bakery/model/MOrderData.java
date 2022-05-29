package com.example.app_for_bakery.model;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MOrderData {
    @Nullable
    String number, data, time;
    List<MPositionData> mPositionDataList = new ArrayList<>();
    boolean is_confirmed = false;

    public MOrderData(Integer number, String data){
        this.number = "Заказ №" + number.toString();
        this.data = "от " + data;
    }

    @Nullable
    public String getNumber() {
        return number;
    }

    public void setNumber(@Nullable String number) {
        this.number = number;
    }

    @Nullable
    public String getTime() {
        return time;
    }

    public void setTime(@Nullable String time) {
        this.time = time;
    }

    public boolean isIs_confirmed() {
        return is_confirmed;
    }

    public void setIs_confirmed(boolean is_confirmed) {
        this.is_confirmed = is_confirmed;
    }

    @Nullable
    public String getData() {
        return data;
    }

    public void setData(@Nullable String data) {
        this.data = data;
    }

    public List<MPositionData> getPositionDataList() {
        return mPositionDataList;
    }

    public void setPositionDataList(List<MPositionData> mPositionDataList) {
        this.mPositionDataList = mPositionDataList;
    }
}
