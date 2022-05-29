package com.example.app_for_bakery.vm_register;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.app_for_bakery.model.MUserProfile;
import com.example.app_for_bakery.vm_login.LoginPageViewModel;

public class RegisterViewModel extends LoginPageViewModel {
    public MutableLiveData<String> repeated_password = new MutableLiveData<>();

    public void setNewUser(MUserProfile mUserProfile){
        user_profile.setValue(mUserProfile);
        repository.addUser(mUserProfile);
    }
   /* @Override
    public void OnClickEnter(@NonNull View view) {
        MUserProfile new_user = new MUserProfile(login.getValue(), password.getValue(), name.getValue());
        user_profile.setValue(new_user);
        repository.addUser(new_user);
    }*/
}
