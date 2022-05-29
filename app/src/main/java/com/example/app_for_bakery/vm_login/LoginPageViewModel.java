package com.example.app_for_bakery.vm_login;

import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.app_for_bakery.repository.UserRepository;
import com.example.app_for_bakery.model.MUserProfile;


public class LoginPageViewModel extends ViewModel {
    public MutableLiveData<MUserProfile> user_profile = new MutableLiveData<>(); //пользователь
    public MutableLiveData<String> login = new MutableLiveData<>(); //логин
    public MutableLiveData<String> password = new MutableLiveData<>(); //пароль
    public MutableLiveData<String> name = new MutableLiveData<>(); //имя
    public final UserRepository repository = new UserRepository();

    public MutableLiveData<MUserProfile> getUser() {
        return user_profile;
    }

    public void setUser_profile(MUserProfile mUserProfile){
        user_profile.setValue(mUserProfile);
    }

    /*public void OnClickEnter(View view){
        if (repository.checkUser(login.getValue(), password.getValue()))
            setUser_profile(repository.getUserByLoginAndPassword(login.getValue(), password.getValue()));
        else
            Toast.makeText(view.getContext(), "Пользователь не зарегестрирован!", Toast.LENGTH_SHORT).show();
    }*/

    public boolean isEmailValid() {
        return Patterns.EMAIL_ADDRESS.matcher(login.getValue() != null ? login.getValue() : null).matches();
    }
}
