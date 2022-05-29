package com.example.app_for_bakery.vm_login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.app_for_bakery.AboutUsFragment;
import com.example.app_for_bakery.databinding.ActivityMainBinding;
import com.example.app_for_bakery.vm_assortment.AssortmentFragment;
import com.example.app_for_bakery.MainActivity;
import com.example.app_for_bakery.R;
import com.example.app_for_bakery.vm_register.RegisterFragment;
import com.example.app_for_bakery.databinding.LoginPageBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class LoginFragment extends Fragment {
    private LoginPageBinding binding;
    private final AssortmentFragment assortmentFragment = new AssortmentFragment();
    private final AboutUsFragment aboutUsFragment = new AboutUsFragment();
    private final RegisterFragment registerFragment = new RegisterFragment();
    public String tag = "LoginFragment";

    public LoginFragment(){}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        binding = DataBindingUtil.inflate(inflater, R.layout.login_page, container, false);
        binding.setClicker(view ->
                ((MainActivity) requireActivity()).replaceFragment(aboutUsFragment, "AboutUsFragment"));
        binding.setTextclicker(view ->
                ((MainActivity) requireActivity()).replaceFragment(registerFragment, "RegisterFragment"));
        return binding.getRoot();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LoginPageViewModel viewModel = new ViewModelProvider(requireActivity()).get(LoginPageViewModel.class);
        binding.setLoginPageViewModel(viewModel);
//        binding.fldLogin.setOnClickListener(view1 -> {
//            binding.titleRegister.setVisibility(View.GONE);
//            binding.linkToRegister.setVisibility(View.GONE);
//        });
//        binding.titleRegister.setVisibility(View.VISIBLE);
//        binding.linkToRegister.setVisibility(View.VISIBLE);
        binding.btnLogin.setOnClickListener(view1 -> {
            if (viewModel.repository.checkUser(viewModel.login.getValue(), viewModel.password.getValue())) {
                viewModel.setUser_profile(viewModel.repository.getUserByLoginAndPassword(viewModel.login.getValue(), viewModel.password.getValue()));
                ((MainActivity)requireActivity()).getSupportFragmentManager().beginTransaction().remove(this).commitAllowingStateLoss();
                ((MainActivity) requireActivity()).replaceFragment(assortmentFragment, "AssortmentFragment");
            }
            else
                Toast.makeText(view.getContext(), "Пользователь не зарегестрирован!", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
