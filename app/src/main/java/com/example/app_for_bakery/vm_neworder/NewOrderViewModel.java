package com.example.app_for_bakery.vm_neworder;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.app_for_bakery.model.MOrderData;
import com.example.app_for_bakery.model.MPositionData;

import java.util.ArrayList;
import java.util.List;


public class NewOrderViewModel extends ViewModel {
    private MutableLiveData<MOrderData> current_order = new MutableLiveData<>();
    private MutableLiveData<List<MOrderData>> all_ordersLiveData = new MutableLiveData<>();

    private List<MOrderData> all_orders = new ArrayList<>();
    public Integer count_orders = 0;

    public void setCurrent_order(MOrderData mOrderData, List<MPositionData> mPositionData){
        mOrderData.setPositionDataList(mPositionData);

        current_order.setValue(mOrderData);

        all_orders.add(mOrderData);
        all_ordersLiveData.setValue(all_orders);

        count_orders = all_orders.size();
    }

    public MutableLiveData<MOrderData> getCurrent_order() {
        return current_order;
    }

    public MutableLiveData<List<MOrderData>> getAll_ordersLiveData() {
        return all_ordersLiveData;
    }
}
