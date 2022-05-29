package com.example.app_for_bakery.vm_profile;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.app_for_bakery.MainActivity;
import com.example.app_for_bakery.R;
import com.example.app_for_bakery.databinding.ProfileChangeBinding;
import com.example.app_for_bakery.vm_login.LoginPageViewModel;
import com.example.app_for_bakery.vm_register.RegisterViewModel;

public class ProfileChangeFragment extends Fragment {
    private ProfileChangeBinding binding;
    private LoginPageViewModel loginPageViewModel;
    private RegisterViewModel registerViewModel;
    private ProfileFragment profileFragment = new ProfileFragment();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.profile_change, container, false);

        registerViewModel = new ViewModelProvider(requireActivity()).get(RegisterViewModel.class);
        loginPageViewModel = new ViewModelProvider(requireActivity()).get(LoginPageViewModel.class);

        binding.ready.setOnClickListener(view -> {
            loginPageViewModel.getUser().observe(getViewLifecycleOwner(), mUserProfile -> {
                if (TextUtils.isEmpty(binding.fldNameFioProfile.getText())) {
                    binding.fldNameFioProfile.setError("Введите имя");
                    binding.fldNameFioProfile.requestFocus();
                }
                else if (mUserProfile.getName().equals(binding.fldNameFioProfile.getText().toString())){
                    binding.fldNameFioProfile.setError("Новое имя не может совпадать со старым");
                    binding.fldNameFioProfile.requestFocus();
                }
                else {
                    mUserProfile.setName(binding.fldNameFioProfile.getText().toString());
                    Toast.makeText(getContext(), "Данные обновлены!", Toast.LENGTH_SHORT).show();
                    ((MainActivity)requireActivity()).getSupportFragmentManager().beginTransaction().remove(this).commitAllowingStateLoss();
                    ((MainActivity)requireActivity()).replaceFragment(profileFragment, "ProfileFragment");
                }
            });
            registerViewModel.getUser().observe(getViewLifecycleOwner(), mUserProfile -> {
                if (TextUtils.isEmpty(binding.fldNameFioProfile.getText())) {
                    binding.fldNameFioProfile.setError("Введите имя");
                    binding.fldNameFioProfile.requestFocus();
                }
                else if (mUserProfile.getName().equals(binding.fldNameFioProfile.getText().toString())){
                    binding.fldNameFioProfile.setError("Новое имя не может совпадать со старым");
                    binding.fldNameFioProfile.requestFocus();
                }
                else {
                    Toast.makeText(getContext(), "Данные обновлены!", Toast.LENGTH_SHORT).show();
                    mUserProfile.setName(binding.fldNameFioProfile.getText().toString());
                    ((MainActivity)requireActivity()).getSupportFragmentManager().beginTransaction().remove(this).commitAllowingStateLoss();
                    ((MainActivity)requireActivity()).replaceFragment(profileFragment, "ProfileFragment");
                }
            });
        });

        return binding.getRoot();
    }
}
