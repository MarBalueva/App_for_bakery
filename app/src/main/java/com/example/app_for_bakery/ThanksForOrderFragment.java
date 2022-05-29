package com.example.app_for_bakery;

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

import com.example.app_for_bakery.databinding.ThanksForFeedbackBinding;
import com.example.app_for_bakery.vm_list_of_orders.ListOfOrdersViewModel;

public class ThanksForOrderFragment extends DialogFragment {
    private ThanksForFeedbackBinding binding;
    private AlertDialog.Builder builder;
    private ListOfOrdersViewModel listOfOrdersViewModel;

    public ThanksForOrderFragment() {
    }

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.thanks_for_feedback, null, false);
        builder = new AlertDialog.Builder(getActivity()).setView(binding.getRoot());

        listOfOrdersViewModel = new ViewModelProvider(requireActivity()).get(ListOfOrdersViewModel.class);

        listOfOrdersViewModel.getSelected().observe(this, mOrderData -> {
            binding.orderNumber.setText(mOrderData.getNumber());
            binding.orderDate.setText(mOrderData.getData() + " успешно завершен!");
        });

        binding.btnFeedback.setOnClickListener(view -> {
            new ReviewWindowDialogFragment().show(getParentFragmentManager(), "ReviewWindowDialogFragment");
            this.dismiss();
        });

        return builder.create();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
