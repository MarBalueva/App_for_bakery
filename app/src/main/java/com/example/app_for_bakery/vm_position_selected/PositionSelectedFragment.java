package com.example.app_for_bakery.vm_position_selected;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.app_for_bakery.EditPositionFragment;
import com.example.app_for_bakery.MainActivity;
import com.example.app_for_bakery.R;
import com.example.app_for_bakery.databinding.PositionCardBinding;
import com.example.app_for_bakery.vm_assortment.AssortmentViewModel;
import com.example.app_for_bakery.vm_profile.ProfileFragment;

public class PositionSelectedFragment extends DialogFragment { //всплывающее окошко с позицией из ассортимента
    private PositionCardBinding binding;

    private AssortmentViewModel viewModel;

    public String tag = "PositionSelectedFragment";

    public PositionSelectedFragment() {
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.position_card, null, false);
        viewModel = new ViewModelProvider(requireActivity()).get(AssortmentViewModel.class);
        binding.setEdit(view -> {
            ((MainActivity) requireActivity()).replaceFragment(new EditPositionFragment(), "EditPositionFragment");
            this.dismiss();
        });
        viewModel.getSelected().observe(this, mPositionData -> {
            binding.price.setText("от " + mPositionData.getPrice().toString() + "/кг");
            binding.imgPosition.setImageResource(mPositionData.getImg());
            binding.setAddBasket(view1 -> {
                Toast.makeText(view1.getContext(), "Добавлено в корзину", Toast.LENGTH_SHORT).show();
                viewModel.addToBasket(mPositionData);
            });
        });
        return new AlertDialog.Builder(getActivity())
                .setView(binding.getRoot())
                .create();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
