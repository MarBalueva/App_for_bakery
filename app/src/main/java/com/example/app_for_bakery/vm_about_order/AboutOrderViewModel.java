package com.example.app_for_bakery.vm_about_order;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.app_for_bakery.model.MOrderData;
import com.example.app_for_bakery.model.MPositionData;

import java.util.List;

public class AboutOrderViewModel extends ViewModel {
    private MutableLiveData<List<MPositionData>> positions = new MutableLiveData<>();
    private MutableLiveData<MOrderData> current_order = new MutableLiveData<>();

    public void setCurrent_order(MOrderData order) {
        current_order.setValue(order);
    }

    public MutableLiveData<MOrderData> getCurrent_order() {
        return current_order;
    }
}
