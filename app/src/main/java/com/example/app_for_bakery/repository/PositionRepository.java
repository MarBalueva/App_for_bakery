package com.example.app_for_bakery.repository;

import com.example.app_for_bakery.R;
import com.example.app_for_bakery.model.MPositionData;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PositionRepository {
    private List<MPositionData> all_positions;
    private String about_position = "Незабываемое удовольствие с любимыми ягодами!";
    String[] biscuits = {"Шоколадный", "Ванильный"};
    String[] creams = {"Шоколадный", "Ванильный", "Фисташковый", "Заварной", "Сливочный"};
    String[] toppings = {"Ягоды", "Фрукты", "Орехи"};
    String[] weights = {"1 кг", "2 кг", "3 кг"};

    public PositionRepository() {
        if (all_positions == null)
            all_positions = new ArrayList<>();
        addPosition(new MPositionData("Клубничное безумие", 1, about_position, 1200, R.drawable.img_strawberry));
        addPosition(new MPositionData("Банановый взрыв", 1, about_position, 1100, R.drawable.img_banana));
        addPosition(new MPositionData("Кокосовый рай", 1, about_position, 2700, R.drawable.img_coconut));
        addPosition(new MPositionData("Яблочный штурм", 1, about_position, 1000, R.drawable.img_apple));
        addPosition(new MPositionData("Шоколадное наслаждение", 1, about_position, 1300, R.drawable.img_chocolate));
    }

    public List<MPositionData> getAll_positions() {
        return all_positions;
    }

    public void addPosition(MPositionData mPositionData){
        all_positions.add(mPositionData);
    }

    public void deletePosition(String name){
        Iterator<MPositionData> iterator = all_positions.iterator();
        while (iterator.hasNext()){
            MPositionData next_position = iterator.next();
            if (next_position.getName() != null && next_position.getName().equals(name))
                iterator.remove();
        }
    }

    public boolean checkPosition(String name){
        for (MPositionData next_position : all_positions)
            if (next_position.getName() != null && next_position.getName().equals(name))
                return false;
        return true;
    }

    public String[] getBiscuits() {
        return biscuits;
    }

    public String[] getCreams() {
        return creams;
    }

    public String[] getToppings() {
        return toppings;
    }

    public String[] getWeights() {
        return weights;
    }
}
