package com.example.app_for_bakery.model;

import android.text.Editable;
import android.util.Patterns;

import androidx.annotation.Nullable;

import javax.xml.transform.sax.SAXResult;

public class MUserProfile {
    @Nullable
    String email, password, name;

    public MUserProfile(@Nullable String email, @Nullable String password, @Nullable String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    @Nullable
    public String getEmail() {
        return email;
    }

    @Nullable
    public String getPassword() {
        return password;
    }

    @Nullable
    public String getName() {
        return name;
    }

    public void setEmail(@Nullable String email) {
        this.email = email;
    }

    public void setPassword(@Nullable String password) {
        this.password = password;
    }

    public void setName(@Nullable String name) {
        this.name = name;
    }


    //сделать статик по приходящей строке
    public boolean isPasswordLengthGreaterThan5(String password) {
        return password.length() > 5;
    }
}
