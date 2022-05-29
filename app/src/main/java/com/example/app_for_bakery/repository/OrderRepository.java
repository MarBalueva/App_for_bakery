package com.example.app_for_bakery.repository;

import com.example.app_for_bakery.model.MOrderData;
import com.example.app_for_bakery.model.MPositionData;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OrderRepository {
    private List<MOrderData> all_orders;

    public OrderRepository() {
        if (all_orders == null)
            all_orders = new ArrayList<>();
        addOrder(new MOrderData(1, "20.04.2022"));
        addOrder(new MOrderData(2,"03.04.2022"));
        addOrder(new MOrderData(3,"05.04.2022"));
        addOrder(new MOrderData(4,"05.04.2022"));
        addOrder(new MOrderData(5,"05.04.2022"));
        addOrder(new MOrderData(6,"05.04.2022"));
        addOrder(new MOrderData(7,"05.04.2022"));
    }

    public List<MOrderData> getAll_orders() {
        return all_orders;
    }

    public void addOrder(MOrderData mOrderData){
        all_orders.add(mOrderData);
    }

    public void deleteOrder(String number){
        Iterator<MOrderData> iterator = all_orders.iterator();
        while (iterator.hasNext()){
            MOrderData next_order = iterator.next();
            if (next_order.getNumber() != null && next_order.getNumber().equals(number))
                iterator.remove();
        }
    }

    public boolean checkOrder(String number){
        for (MOrderData next_order : all_orders)
            if (next_order.getNumber() != null && next_order.getNumber().equals(number))
                return false;
        return true;
    }
}
