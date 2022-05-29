package com.example.app_for_bakery.vm_assortment;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.app_for_bakery.model.MPositionData;
import com.example.app_for_bakery.repository.PositionRepository;

import java.util.ArrayList;
import java.util.List;

public class AssortmentViewModel extends ViewModel {
    private final MutableLiveData<MPositionData> selected = new MutableLiveData<>(); //выбранная карточка
    private final MutableLiveData<List<MPositionData>> positionLiveData = new MutableLiveData<>(); //ассортимент
    private MutableLiveData<List<MPositionData>> addedLiveData = new MutableLiveData<>(); //добавленное в корзину
    private List<MPositionData> all_added = new ArrayList<>(); //добавленные в корзину
    private List<MPositionData> all_positions = new ArrayList<>(); //карточки ассортимента

    public AssortmentViewModel(){
        init();
    }

    public void init(){
        populateList();
        positionLiveData.setValue(all_positions);
    }

    public MutableLiveData<List<MPositionData>> getAdded_to_basket() {
        return addedLiveData;
    }

    public void setAdded_to_basket(List<MPositionData> mPositionDataList) {
        addedLiveData.setValue(mPositionDataList);
    }

    public void addToBasket(MPositionData mPositionData){
        for (MPositionData position : all_added) {//проверяем, добавлена ли эта позиция
            if (position != null && position.getName() != null
                    && position.getName().equals(mPositionData.getName())) {
                position.setCount(position.getCount() + 1); //если да, то увеличиваем кол-во
                setAdded_to_basket(all_added);
                return;
            }
        }
        all_added.add(mPositionData);
        setAdded_to_basket(all_added);
    }

    public List<MPositionData> getAll_positions() {
        return all_positions;
    }

    private void populateList() {
        all_positions = new PositionRepository().getAll_positions();
    }

    public void setSelected(MPositionData positionData){
        selected.setValue(positionData);
    }

    public MutableLiveData<MPositionData> getSelected() {
        return selected;
    }
}