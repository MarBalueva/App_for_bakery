package com.example.app_for_bakery.vm_profile;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.app_for_bakery.R;
import com.example.app_for_bakery.databinding.DialogChangePasswordBinding;
import com.example.app_for_bakery.vm_login.LoginPageViewModel;
import com.example.app_for_bakery.vm_register.RegisterViewModel;

public class ChangePasswordDialog extends DialogFragment {
    private LoginPageViewModel loginPageViewModel;
    private RegisterViewModel registerViewModel;
    private DialogChangePasswordBinding binding;
    private AlertDialog.Builder builder;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.dialog_change_password, null, false);

        loginPageViewModel = new ViewModelProvider(requireActivity()).get(LoginPageViewModel.class);
        registerViewModel = new ViewModelProvider(requireActivity()).get(RegisterViewModel.class);

        builder = new AlertDialog.Builder(getActivity()).setView(binding.getRoot());

        loginPageViewModel.getUser().observe(this, mUserProfile -> {
            binding.btnConfirm.setOnClickListener(view1 -> {
                if (TextUtils.isEmpty(binding.fldEnterOldPass.getText())) {
                    binding.fldEnterOldPass.setError("Введите старый пароль");
                    binding.fldEnterOldPass.requestFocus();
                }
                else if (TextUtils.isEmpty(binding.fldEnterNewPass.getText())) {
                    binding.fldEnterNewPass.setError("Введите новый пароль");
                    binding.fldEnterNewPass.requestFocus();
                }
                else if (!mUserProfile.getPassword().equals(binding.fldEnterOldPass.getText().toString())){
                    binding.fldEnterOldPass.setError("Неверный пароль");
                    binding.fldEnterOldPass.requestFocus();
                }
                else if (binding.fldEnterNewPass.getText().length() < 6) {
                    binding.fldEnterNewPass.setError("Пароль должен содержать не менее 6 символов");
                    binding.fldEnterNewPass.requestFocus();
                }
                else if (binding.fldEnterNewPass.getText().toString().equals(binding.fldEnterOldPass.getText().toString())) {
                    binding.fldEnterNewPass.setError("Пароли не могут совпадать");
                    binding.fldEnterNewPass.requestFocus();
                }
                else {
                    mUserProfile.setPassword(binding.fldEnterNewPass.getText().toString());
                    this.dismiss();
                    Toast.makeText(getContext(), "Пароль обновлен!", Toast.LENGTH_SHORT).show();
                }
            });
        });

        registerViewModel.getUser().observe(this, mUserProfile -> {
            binding.btnConfirm.setOnClickListener(view1 -> {
                if (TextUtils.isEmpty(binding.fldEnterOldPass.getText())) {
                    binding.fldEnterOldPass.setError("Введите старый пароль");
                    binding.fldEnterOldPass.requestFocus();
                }
                else if (TextUtils.isEmpty(binding.fldEnterNewPass.getText())) {
                    binding.fldEnterNewPass.setError("Введите новый пароль");
                    binding.fldEnterNewPass.requestFocus();
                }
                else if (!mUserProfile.getPassword().equals(binding.fldEnterOldPass.getText().toString())){
                    binding.fldEnterOldPass.setError("Неверный пароль");
                    binding.fldEnterOldPass.requestFocus();
                }
                else if (binding.fldEnterNewPass.getText().length() < 6) {
                    binding.fldEnterNewPass.setError("Пароль должен содержать не менее 6 символов");
                    binding.fldEnterNewPass.requestFocus();
                }
                else if (binding.fldEnterNewPass.getText().toString().equals(binding.fldEnterOldPass.getText().toString())) {
                    binding.fldEnterNewPass.setError("Пароли не могут совпадать");
                    binding.fldEnterNewPass.requestFocus();
                }
                else {
                    mUserProfile.setPassword(binding.fldEnterNewPass.getText().toString());
                    this.dismiss();
                    Toast.makeText(getContext(), "Пароль обновлен!", Toast.LENGTH_SHORT).show();
                }
            });
        });

        return builder.create();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
