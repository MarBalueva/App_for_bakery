package com.example.app_for_bakery.vm_baker_profile;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BakerProfileViewModel extends ViewModel {
    private MutableLiveData<String> baker_name = new MutableLiveData<>();
    private MutableLiveData<String> baker_post = new MutableLiveData<>();

    public MutableLiveData<String> getBaker_name() {
        return baker_name;
    }

    public void setBaker_name(String baker_name) {
        this.baker_name.setValue(baker_name);
    }

    public MutableLiveData<String> getBaker_post() {
        return baker_post;
    }

    public void setBaker_post(String baker_post) {
        this.baker_post.setValue(baker_post);
    }
}
