package com.example.app_for_bakery.vm_profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.app_for_bakery.MainActivity;
import com.example.app_for_bakery.R;
import com.example.app_for_bakery.databinding.ProfileBinding;
import com.example.app_for_bakery.vm_login.LoginFragment;
import com.example.app_for_bakery.vm_login.LoginPageViewModel;
import com.example.app_for_bakery.vm_position_selected.PositionSelectedFragment;
import com.example.app_for_bakery.vm_register.RegisterViewModel;

public class ProfileFragment extends Fragment {
    private ProfileBinding binding;
    public String tag = "ProfileFragment";

    public ProfileFragment(){}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        binding = DataBindingUtil.inflate(inflater, R.layout.profile, container, false);

        RegisterViewModel registerViewModel = new ViewModelProvider(requireActivity()).get(RegisterViewModel.class);
        LoginPageViewModel loginPageViewModel = new ViewModelProvider(requireActivity()).get(LoginPageViewModel.class);

        registerViewModel.getUser().observe(getViewLifecycleOwner(), mUserProfile -> { //если зашли со страницы регистрации
            binding.stringNameFioProfile.setText(mUserProfile.getName()); //сохраняем имя в профиле
        });
        loginPageViewModel.getUser().observe(getViewLifecycleOwner(), mUserProfile -> { //если зашли со страницы логина
            binding.stringNameFioProfile.setText(mUserProfile.getName());
        });

        binding.changePassword.setOnClickListener(view -> {
            new ChangePasswordDialog().show(getParentFragmentManager(), "ChangePasswordDialog");
        });

        binding.quitBtn.setOnClickListener(view -> {
            ((MainActivity)requireActivity()).replaceFragment(new LoginFragment(), "LoginFragment");
        });

        binding.editProfile.setOnClickListener(view -> {
            ((MainActivity)requireActivity()).replaceFragment(new ProfileChangeFragment(), "ProfileChangeFragment");
        });

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
