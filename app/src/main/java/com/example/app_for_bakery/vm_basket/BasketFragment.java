package com.example.app_for_bakery.vm_basket;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.app_for_bakery.MainActivity;
import com.example.app_for_bakery.model.MPositionData;
import com.example.app_for_bakery.vm_neworder.NewOrderFragment;
import com.example.app_for_bakery.R;
import com.example.app_for_bakery.databinding.MyBasketBinding;
import com.example.app_for_bakery.vm_assortment.AssortmentViewModel;
import com.example.app_for_bakery.vm_profile.ProfileFragment;
import com.google.android.material.snackbar.Snackbar;

import java.util.concurrent.atomic.AtomicBoolean;


public class BasketFragment extends Fragment {
    private MyBasketBinding binding;

    private BasketViewModel basketViewModel;
    private AssortmentViewModel assortmentViewModel;

    private RVAdapterBasketCard adapter;
    public String tag = "BasketFragment";

    public BasketFragment() {}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.my_basket, container, false);

        AtomicBoolean is_empty = new AtomicBoolean(true);

        assortmentViewModel = new ViewModelProvider(requireActivity()).get(AssortmentViewModel.class);

        assortmentViewModel.getAdded_to_basket().observe(getViewLifecycleOwner(), mPositionData -> {
            basketViewModel = new ViewModelProvider(requireActivity()).get(BasketViewModel.class);
            basketViewModel.setAll_positions(mPositionData);
            if (mPositionData.size() != 0)
                is_empty.set(false);
            adapter = new RVAdapterBasketCard(basketViewModel.getAll_positions());
            binding.recyclerViewMyBasket.setAdapter(adapter);
            adapter.setListener((v, position) -> {
                basketViewModel.setSelected(adapter.getPositionAt(position));
                //обработка нажатия
            });
            new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
                @Override
                public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                    return false;
                }

                @Override
                public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                    int position = viewHolder.getAdapterPosition();
                    MPositionData position_deleted = mPositionData.get(position);
                    mPositionData.remove(position);
                    adapter.notifyItemRemoved(position);
                    Snackbar.make(binding.recyclerViewMyBasket, position_deleted.getName(), Snackbar.LENGTH_LONG).setAction("Отменить", v -> {
                        mPositionData.add(position, position_deleted);
                        adapter.notifyItemInserted(position);
                    }).show();
                }
            }).attachToRecyclerView(binding.recyclerViewMyBasket);
                });


        binding.btnProfile.setOnClickListener(view -> {
            ((MainActivity) requireActivity()).replaceFragment(new ProfileFragment(), "ProfileFragment");
            MainActivity.bottomNavigationView.setSelectedItemId(R.id.page_4);
        });

        binding.createOrder.setOnClickListener(view -> {
            if (is_empty.get())
                Toast.makeText(view.getContext(), "Корзина пуста!", Toast.LENGTH_SHORT).show();
            else
                ((MainActivity) requireActivity()).replaceFragment(new NewOrderFragment(), "NewOrderFragment");
        });

        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
    }
}