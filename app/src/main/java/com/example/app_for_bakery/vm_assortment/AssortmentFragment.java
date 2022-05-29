package com.example.app_for_bakery.vm_assortment;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;

import com.example.app_for_bakery.MainActivity;
import com.example.app_for_bakery.R;
import com.example.app_for_bakery.databinding.AssortmentBinding;
import com.example.app_for_bakery.vm_position_selected.PositionSelectedFragment;
import com.example.app_for_bakery.vm_profile.ProfileFragment;


public class AssortmentFragment extends Fragment {

    private AssortmentBinding binding;
    private AssortmentViewModel assortmentViewModel;

    public AssortmentFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.assortment, container, false);
        binding.recyclerViewAssortmentPopularity.setVisibility(View.GONE);
        binding.recyclerViewAssortmentBirthday.setVisibility(View.GONE);
        binding.recyclerViewAssortmentWedding.setVisibility(View.GONE);
        binding.recyclerViewAssortmentKids.setVisibility(View.GONE);

        binding.setSaleClicker(view -> {
            boolean is_shown = binding.recyclerViewAssortmentSale.getVisibility() == View.VISIBLE;
            binding.recyclerViewAssortmentSale.setVisibility(is_shown ? View.GONE : View.VISIBLE);
        });
        binding.setPopularClicker(view -> {
            boolean is_shown = binding.recyclerViewAssortmentPopularity.getVisibility() == View.VISIBLE;
            binding.recyclerViewAssortmentPopularity.setVisibility(is_shown ? View.GONE : View.VISIBLE);
        });
        binding.setBirthdayClicker(view -> {
            boolean is_shown = binding.recyclerViewAssortmentBirthday.getVisibility() == View.VISIBLE;
            binding.recyclerViewAssortmentBirthday.setVisibility(is_shown ? View.GONE : View.VISIBLE);
        });
        binding.setWeddingClicker(view -> {
            boolean is_shown = binding.recyclerViewAssortmentWedding.getVisibility() == View.VISIBLE;
            binding.recyclerViewAssortmentWedding.setVisibility(is_shown ? View.GONE : View.VISIBLE);
        });
        binding.setKidsClicker(view -> {
            boolean is_shown = binding.recyclerViewAssortmentKids.getVisibility() == View.VISIBLE;
            binding.recyclerViewAssortmentKids.setVisibility(is_shown ? View.GONE : View.VISIBLE);
        });

        assortmentViewModel = new ViewModelProvider(requireActivity()).get(AssortmentViewModel.class);

        binding.btnProfile.setOnClickListener(view -> {
            ((MainActivity) requireActivity()).replaceFragment(new ProfileFragment(), "ProfileFragment");
            MainActivity.bottomNavigationView.setSelectedItemId(R.id.page_4);
        });

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        RVAdapterAssortmentCard adapter = new RVAdapterAssortmentCard(assortmentViewModel.getAll_positions(), assortmentViewModel);

        binding.recyclerViewAssortmentSale.setAdapter(adapter);
        binding.recyclerViewAssortmentBirthday.setAdapter(adapter);
        binding.recyclerViewAssortmentPopularity.setAdapter(adapter);
        binding.recyclerViewAssortmentKids.setAdapter(adapter);
        binding.recyclerViewAssortmentWedding.setAdapter(adapter);

        adapter.setListener((v, position) -> {
            assortmentViewModel.setSelected(adapter.getPositionAt(position));
            new PositionSelectedFragment().show(getParentFragmentManager(), "PositionSelected");
        });

        binding.search.setImeOptions(EditorInfo.IME_ACTION_DONE);
        binding.search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });
    }
}