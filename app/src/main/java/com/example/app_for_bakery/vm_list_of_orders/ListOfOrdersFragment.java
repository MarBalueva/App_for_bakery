package com.example.app_for_bakery.vm_list_of_orders;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.app_for_bakery.MainActivity;
import com.example.app_for_bakery.R;
import com.example.app_for_bakery.databinding.ListOfOrdersBinding;
import com.example.app_for_bakery.model.MOrderData;
import com.example.app_for_bakery.vm_about_order.AboutOrderFragment;
import com.example.app_for_bakery.vm_about_order.AboutOrderViewModel;
import com.example.app_for_bakery.vm_assortment.AssortmentFragment;
import com.example.app_for_bakery.vm_neworder.NewOrderViewModel;
import com.example.app_for_bakery.vm_profile.ProfileFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListOfOrdersFragment extends Fragment {
    private ListOfOrdersBinding binding;
    public String tag = "ListOfOrdersFragment";

    private NewOrderViewModel newOrderViewModel;
    private ListOfOrdersViewModel listOfOrdersViewModel;

    private AboutOrderFragment aboutOrderFragment = new AboutOrderFragment();

    private RVAdapterOrderCard adapter;

    public ListOfOrdersFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.list_of_orders, container, false);

        binding.btnProfile.setOnClickListener(view -> {
            ((MainActivity) requireActivity()).replaceFragment(new ProfileFragment(), "ProfileFragment");
            MainActivity.bottomNavigationView.setSelectedItemId(R.id.page_4);
        });

        newOrderViewModel = new ViewModelProvider(requireActivity()).get(NewOrderViewModel.class);
        listOfOrdersViewModel = new ViewModelProvider(requireActivity()).get(ListOfOrdersViewModel.class);

        newOrderViewModel.getAll_ordersLiveData().observe(getViewLifecycleOwner(), mOrderData -> {
            adapter = new RVAdapterOrderCard(mOrderData);
            binding.recyclerViewMyOrders.setAdapter(adapter);

            adapter.setListener((v, position) -> {
                listOfOrdersViewModel.setSelected(adapter.getPositionAt(position));
                ((MainActivity) requireActivity()).replaceFragment(aboutOrderFragment, "AboutOrderFragment");
                //обработка нажатия
            });
        });

        binding.btnSortOrders.setOnClickListener(view -> {
            newOrderViewModel.getAll_ordersLiveData().observe(getViewLifecycleOwner(), mOrderData -> {
                Collections.reverse(mOrderData);
                adapter = new RVAdapterOrderCard(mOrderData);
                binding.recyclerViewMyOrders.setAdapter(adapter);
                adapter.setListener((v, position) -> {
                    listOfOrdersViewModel.setSelected(adapter.getPositionAt(position));
                    ((MainActivity) requireActivity()).replaceFragment(aboutOrderFragment, "AboutOrderFragment");
                    //обработка нажатия
                });
            });
        });

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

    }
}