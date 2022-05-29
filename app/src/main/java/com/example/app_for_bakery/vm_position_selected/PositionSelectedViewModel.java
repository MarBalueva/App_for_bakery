package com.example.app_for_bakery.vm_position_selected;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.app_for_bakery.model.MPositionData;

public class PositionSelectedViewModel extends ViewModel {
    MutableLiveData <MPositionData> position = new MutableLiveData<>();

    public MutableLiveData<MPositionData> getPosition() {
        return position;
    }

    public void setPosition(MutableLiveData<MPositionData> position) {
        this.position = position;
    }
}
