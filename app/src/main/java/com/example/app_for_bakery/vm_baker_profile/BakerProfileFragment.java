package com.example.app_for_bakery.vm_baker_profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.app_for_bakery.R;
import com.example.app_for_bakery.databinding.ProfileBakerBinding;

public class BakerProfileFragment extends Fragment {
    private ProfileBakerBinding binding;
    private BakerProfileViewModel bakerProfileViewModel;

    public BakerProfileFragment(){}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        binding = DataBindingUtil.inflate(inflater, R.layout.profile_baker, container, false);

        bakerProfileViewModel = new ViewModelProvider(requireActivity()).get(BakerProfileViewModel.class);

        bakerProfileViewModel.getBaker_name().observe(getViewLifecycleOwner(), s -> {
            binding.nameBakery.setText(s);
        });

        bakerProfileViewModel.getBaker_post().observe(getViewLifecycleOwner(), s -> {
            binding.postBakery.setText(s);
        });

        return binding.getRoot();
    }
}
