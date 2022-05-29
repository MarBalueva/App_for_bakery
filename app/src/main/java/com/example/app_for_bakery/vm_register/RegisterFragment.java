package com.example.app_for_bakery.vm_register;

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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.app_for_bakery.AboutUsFragment;
import com.example.app_for_bakery.MainActivity;
import com.example.app_for_bakery.R;
import com.example.app_for_bakery.vm_assortment.AssortmentFragment;
import com.example.app_for_bakery.vm_login.LoginPageViewModel;
import com.example.app_for_bakery.databinding.CreateAccountBinding;
import com.example.app_for_bakery.model.MUserProfile;

import java.util.Objects;

public class RegisterFragment extends Fragment {
    private CreateAccountBinding binding;
    public String tag = "RegisterFragment";

    public RegisterFragment(){}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        binding = DataBindingUtil.inflate(inflater, R.layout.create_account, container, false);
        binding.setClick(view ->
                ((MainActivity) requireActivity()).replaceFragment(new AboutUsFragment(), "AboutUsFragment"));
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RegisterViewModel viewModel = new ViewModelProvider(requireActivity()).get(RegisterViewModel.class);
        binding.setRegisterViewModel(viewModel);
        binding.btnRegister.setOnClickListener(view1 -> {
            if (TextUtils.isEmpty(binding.fldRegEmail.getText())) {
                binding.fldRegEmail.setError("Введите email");
                binding.fldRegEmail.requestFocus();
            }
            else if (!viewModel.isEmailValid()) {
                binding.fldRegEmail.setError("Введите корректный email");
                binding.fldRegEmail.requestFocus();
            }
            else if (TextUtils.isEmpty(binding.fldRegName.getText())) {
                binding.fldRegName.setError("Введите имя");
                binding.fldRegName.requestFocus();
            }
            else if (TextUtils.isEmpty(binding.fldRegPassword.getText())) {
                binding.fldRegPassword.setError("Введите пароль");
                binding.fldRegPassword.requestFocus();
            }
            else if (binding.fldRegPassword.getText().length() < 6) {
                binding.fldRegPassword.setError("Пароль должен содержать не менее 6 символов");
                binding.fldRegPassword.requestFocus();
            }
            else if (!viewModel.password.getValue().equals(viewModel.repeated_password.getValue())){
                binding.fldRegConfPassword.setError("Пароли должны совпадать!");
                binding.fldRegConfPassword.requestFocus();
            }
            else if (viewModel.repository.checkUser(viewModel.login.getValue(), viewModel.password.getValue()))
                Toast.makeText(view.getContext(), "Пользователь уже зарегестрирован!", Toast.LENGTH_SHORT).show();
            else {
                MUserProfile new_user = new MUserProfile(viewModel.login.getValue(), viewModel.password.getValue(), viewModel.name.getValue());
                viewModel.setNewUser(new_user);
                Toast.makeText(view.getContext(), "Пользователь успешно зарегестрирован!", Toast.LENGTH_SHORT).show();
                ((MainActivity) requireActivity()).getSupportFragmentManager().beginTransaction().remove(this).commitAllowingStateLoss();
                ((MainActivity) requireActivity()).replaceFragment(new AssortmentFragment(), "AssortmentFragment");
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
