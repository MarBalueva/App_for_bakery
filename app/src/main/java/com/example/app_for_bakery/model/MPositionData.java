package com.example.app_for_bakery.model;

import androidx.annotation.Nullable;

public class MPositionData {
    @Nullable
    String name;
    String description;
    Integer price;
    Integer count = 0;
    Integer img = 0;

    public MPositionData(@Nullable String name, Integer count, String description, Integer price, Integer img){
        this.name = name;
        this.count = count;
        this.description = description;
        this.price = price;
        this.img = img;
    }

    @Nullable
    public String getName() {
        return name;
    }

    public void setName(@Nullable String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getImg() {
        return img;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
