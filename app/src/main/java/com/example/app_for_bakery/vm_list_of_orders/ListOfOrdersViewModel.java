package com.example.app_for_bakery.vm_list_of_orders;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.app_for_bakery.model.MOrderData;
import com.example.app_for_bakery.model.MPositionData;
import com.example.app_for_bakery.repository.OrderRepository;
import com.example.app_for_bakery.repository.PositionRepository;

import java.util.ArrayList;
import java.util.List;

public class ListOfOrdersViewModel extends ViewModel {
    private final MutableLiveData<MOrderData> selected = new MutableLiveData<>();
    private final MutableLiveData<List<MOrderData>> orderLiveData = new MutableLiveData<>();
    private List<MOrderData> all_orders = new ArrayList<>();

    public ListOfOrdersViewModel(){
        init();
    }

    public void init(){
        orderLiveData.setValue(all_orders);
    }

    public List<MOrderData> getAll_orders() {
        return all_orders;
    }

    public void setSelected(MOrderData orderData){
        selected.setValue(orderData);
    }

    public MutableLiveData<MOrderData> getSelected() {
        return selected;
    }
}
