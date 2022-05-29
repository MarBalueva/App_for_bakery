package com.example.app_for_bakery.vm_basket;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.app_for_bakery.model.MPositionData;
import com.example.app_for_bakery.repository.PositionRepository;
import com.example.app_for_bakery.vm_assortment.AssortmentViewModel;

import java.util.ArrayList;
import java.util.List;

public class BasketViewModel extends ViewModel {
    private final MutableLiveData<MPositionData> selected = new MutableLiveData<>();
    private final MutableLiveData<List<MPositionData>> positionLiveData = new MutableLiveData<>();
    private List<MPositionData> all_positions = new ArrayList<>();

    public BasketViewModel(){
        init();
    }

    public void init(){
        positionLiveData.setValue(all_positions);
    }

    public List<MPositionData> getAll_positions() {
        return positionLiveData.getValue();
    }

    public MutableLiveData<List<MPositionData>> getPositionLiveData() {
        return positionLiveData;
    }

    public void setAll_positions(List<MPositionData> all_positions) {
        this.all_positions = all_positions;
        positionLiveData.setValue(all_positions);
    }

    public int countPrice(List<MPositionData> mPositionData){
        int sum = 0;
        for (MPositionData positionData: mPositionData)
            sum += positionData.getPrice() * positionData.getCount();
        return sum;
    }

    public void setSelected(MPositionData positionData){
        selected.setValue(positionData);
    }

    public void deleteAll_positions(){
        all_positions.clear();
    }

    public MutableLiveData<MPositionData> getSelected() {
        return selected;
    }
}
