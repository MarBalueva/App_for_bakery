package com.example.app_for_bakery;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.app_for_bakery.vm_baker_profile.BakerProfileFragment;
import com.example.app_for_bakery.vm_baker_profile.BakerProfileViewModel;
import com.example.app_for_bakery.vm_register.RegisterFragment;
import com.example.app_for_bakery.databinding.AboutUsBinding;
import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.mapview.MapView;

public class AboutUsFragment extends Fragment {
    private MapView mapView;
    private final String MAPKIT_API_KEY = "309d9b81-cf3d-4c7c-8f50-861c18719a00";
    private final Point TARGET_LOCATION = new Point(55.75370903771494, 37.61981338262558);
    private AboutUsBinding binding;
    public String tag = "AboutUsFragment";

    private BakerProfileViewModel bakerProfileViewModel;

    public AboutUsFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ((MainActivity) requireActivity()).initialize_map(MAPKIT_API_KEY, requireActivity());
        binding = DataBindingUtil.inflate(inflater, R.layout.about_us, container, false);
        mapView = binding.mapview;
        binding.setClicker(view ->
                ((MainActivity) requireActivity()).replaceFragment(new RegisterFragment(), "RegisterFragment"));

        bakerProfileViewModel = new ViewModelProvider(requireActivity()).get(BakerProfileViewModel.class);

        binding.btnBakery1.setOnClickListener(view -> {
            bakerProfileViewModel.setBaker_name(binding.nameBakery1.getText().toString());
            bakerProfileViewModel.setBaker_post(binding.postBakery1.getText().toString());
            ((MainActivity) requireActivity()).replaceFragment(new BakerProfileFragment(), "BakerProfileFragment");
        });

        binding.btnBakery2.setOnClickListener(view -> {
            bakerProfileViewModel.setBaker_name(binding.nameBakery2.getText().toString());
            bakerProfileViewModel.setBaker_post(binding.postBakery2.getText().toString());
            ((MainActivity) requireActivity()).replaceFragment(new BakerProfileFragment(), "BakerProfileFragment");
        });

        binding.btnBakery3.setOnClickListener(view -> {
            bakerProfileViewModel.setBaker_name(binding.nameBakery3.getText().toString());
            bakerProfileViewModel.setBaker_post(binding.postBakery3.getText().toString());
            ((MainActivity) requireActivity()).replaceFragment(new BakerProfileFragment(), "BakerProfileFragment");
        });

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapView.getMap().move(
                new CameraPosition(TARGET_LOCATION, 14.0f, 0.0f, 0.0f),
                new Animation(Animation.Type.SMOOTH, 5),
                null);
    }

    @Override
    public void onStop() {
        mapView.onStop();
        MapKitFactory.getInstance().onStop();
        super.onStop();
    }

    @Override
    public void onStart() {
        super.onStart();
        MapKitFactory.getInstance().onStart();
        mapView.onStart();
    }
}